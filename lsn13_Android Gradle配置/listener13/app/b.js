var EpWaterConfig = {
	mainStationID:null,
	mainStationName:null,
	regionId:null,
	stationType:null,
	pollutantJson:null,
};
/**
 * 环保大屏主面板
 * 需配置tc_human_nav_item,tc_nav_item,tc_sys_info三个表
 */
define(["jquery", "knockout", "durandal/app",  "durandal/composition", "http", "durandal/system", "tabs"
        , "i18nCommon", "view/ep/waterquality/i18n/i18n",  "panal", "template", "library/ep/common/URLParamUtils", "css!style/ep/waterquality/common/page-style",
        "css!style/ep/waterquality/whiteblue/page-style"],
    function ($, ko, app, composition, http, system, tabs, i18nCommon, i18n, panal, template,URLParamUtils) {

        function model() {
            var self = this;
            var _container, menuContainer;
            self._nav;
            //menu菜单设置的为font-size:15px;
            var menuWordSize = 14;
            var topMenuHeight = 50;
            var menuSelectedClass = "-selected";
            var menuList;
            var _serviceGetMenus = http.getInstance("home/nav/gethumannavbaritemswithsenior");
            var _serviceGetScreenConfig = http.getInstance("home/ep/waterquality/main/getinitconfiglist");

            var _SYS_NAME = "ep-waterquality";

            /**
             * 导航菜单模版
             * @type {string}
             */
            var menuTemplate = [
                "<div class='app-ep-aq-nav-submenu-container' style='z-index:30000;position:relative;'><div  class='ep-top-menu' style='width:600px;height:" + topMenuHeight + "px'><div  class='top-menu' style='font-size:" + menuWordSize + "px;line-height:" + (menuWordSize - 2) + "px'>",
                "{{each menuList as menu menuIndex}}",
                "<div style='width:100px;padding-top:" + (topMenuHeight - menuWordSize) / 2 + "px' class='app-sys-nav-item-spe top-menu-item {{if menu.subMenu}}{{else}}menu-self{{/if}}' ",
                "attr-value='{{menu.navItemID}}' firstLevelIndex={{menuIndex}}>",
                "{{if menu.navItemIcon}}<span class='icon icon-{{menu.navItemIcon}}{{if menuIndex == 0}}-selected{{/if}}'></span>&nbsp;{{/if}}",
                "{{menu.displayName}}",
                "{{if menu.subMenu}}",
                "<span class='caret'></span>",
                "<ul class='app-sys-nav-item-spe ep-menu-item-sub'>",
                "{{each menu.subMenu as sMenu sMenuIndex}}",
                "<li  class='app-sys-nav-item-spe ep-menu-item-sub-item menu-self' attr-value='{{sMenu.navItemID}}' firstLevelIndex={{menuIndex}} secondLevelIndex={{sMenuIndex}}>",
                "{{sMenu.displayName}}",
                "</li>",
                "{{/each}}",
                "</ul>",
                "{{/if}}",
                "</div>",
                "{{/each}}",
                "</div></div></div>"].join("");

            composition.addBindingHandler("mainWaterQualityInit", {
                init: function (dom) {
                    _container = $(dom);
                    initMenus();
                    initConfigList();
                }
            });

            /**
             * 初始化导航
             */
            function initMenus() {

                _serviceGetMenus.ajax({sysName: _SYS_NAME}).then(function (data) {
                    console.log(data);
                    menuList = initNavBarItem(data.humanNavBarItems)
                    console.log(menuList);
                    var html = template.compile(menuTemplate)({menuList: menuList});

                    if ($(".app-ep-aq-nav-submenu-container").length > 0) {
                        $(".app-ep-aq-nav-submenu-container").show();
                    } else {
                        $(html).insertAfter("#application-main");
                    }
                    menuContainer = $(".app-ep-aq-nav-submenu-container");

                    menuContainer.find(".menu-self").click(function () {
                        $(".app-ep-aq-nav-submenu-container").find(".top-menu").find(".top-menu-item").removeClass("ep-menu-item-sub-item-selected");
                        //图标替换
                        menuContainer.find("span[class$=selected]").each(function (item) {
                            var clsName = $(this)[0].className;
                            $(this).removeClass(clsName);
                            $(this).addClass(clsName.replace(menuSelectedClass, ""));
                        });
                        //图标替换
                        $(this).find("span").each(function () {
                            var clsName = $(this)[0].className;
                            $(this).removeClass(clsName);
                            $(this).addClass(clsName + menuSelectedClass);
                        });

                        if ($(this).hasClass("ep-menu-item-sub-item")) {
                            $(this).parent().parent().addClass("ep-menu-item-sub-item-selected");
                        } else {
                            $(this).addClass("ep-menu-item-sub-item-selected");
                        }

                        var firstLevelIndex = parseInt($(this).attr("firstLevelIndex"));
                        var secondLevelIndex = parseInt($(this).attr("secondLevelIndex"));
                        var navItem = menuList[firstLevelIndex];
                        if (!isNaN(secondLevelIndex)) {
                            navItem = navItem.subMenu[secondLevelIndex];
                        }

                        var navView = navItem.view;
                        if (navView) {
                            var param = URLParamUtils.getAllParamFromUrl(navView);
                            if (navView.indexOf("?") > -1) {
                                navView = navView.substring(0, navView.indexOf("?"));
                            }
                            app.setRoot(navView, "entrance", _container.find(".js-ep-body")[0], param);
                        }
                    });
                    menuContainer.find('.top-menu-item').each(function (i) {
                        $(this).hover(function (event) {
                            $(this).children("ul").stop(true, true).slideDown(200);
                            event.stopPropagation();
                        }, function () {
                            $(this).children("ul").stop(true, true).slideUp(200);
                        });
                    });
                    menuContainer.find(".menu-self")[0].click();
                });
            }
            /**
             * 初始菜单层次结构
             * @param navbaritem
             * @returns {Array}
             */
            function initNavBarItem(navbaritem) {
                var baseArray = [], childrenArray = [];
                var navItemMap = {};
                //遍历所有菜单项
                for (var i = 0; i < navbaritem.length; i++) {
                    var navItem = navbaritem[i].navItem;
                    navItemMap["" + navItem.navItemID] = navItem;
                    if (navItem.seniorID != undefined && navItem.seniorID > 0) {
                        navItem.isChild = true;
                        childrenArray.push(navItem);
                    } else {
                        navItem.isChild = false;
                    }
                }
                //寻找子菜单的父节点
                for (var i = 0; i < childrenArray.length; i++) {
                    var navItem = childrenArray[i];
                    if (navItemMap["" + navItem.seniorID].subMenu) {
                        navItemMap["" + navItem.seniorID].subMenu.push(navItem);
                    } else {
                        navItemMap["" + navItem.seniorID].subMenu = [navItem];
                    }
                }
                //从map中寻找第一级的菜单并返回
                $.each(navItemMap, function (i, item) {
                    if (!item.isChild) {
                        baseArray.push(item);
                    }
                });
                return baseArray;
            };
            /**
             * 获取空气质量初始配置项
             */
            function initConfigList() {
                _serviceGetScreenConfig.ajax().then(function (data) {
                	_container.find(".js-system-title").html(data.systemName);
                	_container.find(".js-system-title-us").html(data.systemUSName);
                	
                	EpWaterConfig.mainStationID = data.mainStationId;
                	EpWaterConfig.mainStationName = data.mainStationName;
                	EpWaterConfig.mainStationType = data.mainStationType;
                	
                	var _pollutantJson = {};
                    var pollutantList = data.pollutantList;
                    for (var i = 0; i < pollutantList.length; i++) {
                        var obj = {
                            id: pollutantList[i].pollutantId,
                            name: pollutantList[i].pollutantName
                        };
                        if (pollutantList[i].unit) {
                            obj.unit = pollutantList[i].unit.itemName;
                        }
                        _pollutantJson[pollutantList[i].pollutantCode] = obj;
                    }
                    EpWaterConfig.pollutantJson =  _pollutantJson;                    
                    _container.find(".ep-waterquality-close").bind("click", function () {
                       // alert("功能待完善");
                    });
                });
            }
        }

        return model;
    });