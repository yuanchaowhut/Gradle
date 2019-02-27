package builder

/**
 * Created by Administrator on 2017/6/25 0025.
 */

class Node{
    String name
    String value
    Map attr
    def children

    Node(String name, String value, Map attr) {
        this.name = name
        this.value = value
        this.attr = attr
        children = []
    }

    //<xml key='value'/>
    //<xml >xxx</xml>
    def build(Writer writer){
        writer.write("<$name")
        if (attr){
            attr.each {
                key,value->
                    writer.write(" $key='$value'")
            }
        }
        if (value || children){
            writer.write(">")
            if (value){
                writer.write(value)
            }
            children.each{
                it.build(writer)
            }
            writer.write("</$name>")
        }else{
            writer.write("/>")
        }
    }
}

//工厂 buildersupport工厂
class MyFactoryBuilderSupport extends FactoryBuilderSupport {
    {
        //注册html的工厂
        registerFactory('html',new HtmlFactory())
        registerFactory('head',new HeadFactory())
    }
}

class HtmlFactory extends AbstractFactory{

    def name
    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
        println "newInstance $name $value $attributes"
        this.name = name
        return new Node(name,value,attributes)
    }

    /**
     * 动脑学院 版权所有
     * 处理属性的方法
     */
    @Override
    boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
        //如果返回true会从newInstance返回中寻找attributes对应key的属性
        return false
    }

    // false表示可以接收闭包
    //true 表示不能接收
    @Override
    boolean isLeaf() {
        return super.isLeaf()
    }

    /**
     * 设置当前节点的父节点
     * @param builder
     * @param parent
     * @param child
     */
    @Override
    void setParent(FactoryBuilderSupport builder, Object parent, Object child) {
        super.setParent(builder, parent, child)
        println "$name  setParent ${parent.name} ${child.name}"
    }

    /**
     * 设置当前节点的子节点
     * @param builder
     * @param parent
     * @param child
     */
    @Override
    void setChild(FactoryBuilderSupport builder, Object parent, Object child) {
        super.setChild(builder, parent, child)
        println "$name setChild ${parent.name} ${child.name}"
        parent.children << child
    }
}

class HeadFactory extends AbstractFactory{

    def name
    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
        println "newInstance $name $value $attributes"
        this.name = name
        return new Node(name,value,attributes)
    }

    /**
     * 动脑学院 版权所有
     * 处理属性的方法
     */
    @Override
    boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
        //如果返回true会从newInstance返回中寻找attributes对应key的属性
        return false
    }

    // false表示可以接收闭包
    //true 表示不能接收
    @Override
    boolean isLeaf() {
        return super.isLeaf()
    }


    @Override
    void setParent(FactoryBuilderSupport builder, Object parent, Object child) {
        super.setParent(builder, parent, child)
        println "$name  setParent ${parent.name} ${child.name}"
    }

    @Override
    void setChild(FactoryBuilderSupport builder, Object parent, Object child) {
        super.setChild(builder, parent, child)
        println "$name setChild ${parent.name} ${child.name}"
    }
}