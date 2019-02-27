/**
 * Created by Administrator on 2017/6/23 0023.
 */
/**
 * 动脑学院 版权所有
 *  方法委托
 */

class Work1{
    def execute1(){
        println "execute1"
    }
}

class Work2{
    def execute2(){
        println "execute2"
    }
}

class WorkManager{
    Work1 work1 = new Work1()
    Work2 work2 = new Work2()
    def methodMissing(String name, def args) {
        WorkManager wm = this
        if (work1.respondsTo(name,args)){
            wm.metaClass."$name" = {
                work1.invokeMethod(name,it)
            }
            "$name"(args)
        } else if(work2.respondsTo(name,args)){
            wm.metaClass."$name" = {
                work2.invokeMethod(name,it)
            }
            "$name"(args)
        }
        return null
    }
}

def wm = new WorkManager()
wm.work1.execute1()
wm.execute1()

class WorkManager1{

    {
        delegate(Work1,Work2)
    }

    def delegate(Class... classes){
        //创建对应的对象
        def objects = classes.collect {it.newInstance()}
        WorkManager1 wm = this
        //注入methodMissing方法
        wm.metaClass.methodMissing = {
            String name, def args ->
                //查找调用的方法的实现对象
                def object = objects.find {it.respondsTo(name,args)}
                if (object){
                    //动态注入方法
                    wm.metaClass."$name" = {
                        object.invokeMethod(name,it)
                    }
                    invokeMethod(name,args)
                }
        }
    }

}

def wm1 = new WorkManager1()
wm1.execute1()
wm1.execute2()


class WorkManager2{
    @Delegate Work1 work1 = new Work1()
}

new WorkManager2().execute1()
