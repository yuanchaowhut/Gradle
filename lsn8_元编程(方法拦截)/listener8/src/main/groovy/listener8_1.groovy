/**
 * Created by Administrator on 2017/4/26 0026.
 */


class Lance {
    def dream() {
        println "lance's dream"
    }
}

//在单个对象上进行方法拦截
//在类上进行方法拦截
//Lance.metaClass
def lance = new Lance()
//lance.dream()
lance.metaClass.dream = {
    println 'replace dream'
}
//lance.dream()

lance.metaClass.invokeMethod = {
    String name, Object args ->
        System.out.println('invoke')
        def method = delegate.metaClass.getMetaMethod(name, args)
        if (method) {
            method.invoke(delegate, args)
        }
}
lance.dream()

String.metaClass.plus = {
    CharSequence i ->
        i
}
println("123" + 'abc')


