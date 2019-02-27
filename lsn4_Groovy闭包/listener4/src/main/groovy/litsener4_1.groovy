/**
 * Created by Administrator on 2017/4/16 0016.
 */

//def closure = {
//    println "this is " + this
//    println "owner is " + owner
//    println "delegate is " + delegate
//}
//
//closure()

class TestClosure {
//    def static closure = {
//        println "this is " + this
//        println "owner is " + owner
//        println "delegate is " + delegate
//    }

    def closure1 = {
        def closure2 = {
            println "this is " + this
            println "owner is " + owner
            println "delegate is " + delegate
        }
        closure2()
    }
}

//new TestClosure().closure1()

//this 定义它的时候 所在的类的this 静态闭包当中为 class
//owner 定义它的时候 所在类的对象
//delegate 默认就是owner


class TestFunc {
    def func() {
        println 'TestFunc:func'
    }
}

def func() {
    println 'listener4_1:func'
}

def closure = {
    def func = {
        println 'closure:func'
    }
    func()
}

//closure.delegate = new TestFunc()
//closure.resolveStrategy = Closure.TO_SELF
closure()

