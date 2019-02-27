/**
 * Created by Administrator on 2017/4/26 0026.
 */


//3中方式实现方法注入
//1、metaclass
//2、分类 category
//3、混合 mixin
println String.metaClass.class.name
String.metaClass.printlnSelf << {
    println delegate
}
"123".printlnSelf()
println String.metaClass.class.name

String.metaClass.toLowerCase = {

}




