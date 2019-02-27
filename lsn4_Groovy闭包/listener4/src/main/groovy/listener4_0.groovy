/**
 * Created by Administrator on 2017/4/16 0016.
 */

//闭包
def closure = {
    i, j ->
        println i + ' ' + j
}
//闭包默认能够接收一个参数
//closure('Java')

//柯里化闭包
//def curriedClosure = closure.ncurry(1,'Groovy')
//curriedClosure('java')


def func(closure) {
    closure()
}

interface Action {
    void call()
}

//func(new Action() {
//
//    @Override
//    void call() {
//        println 'call'
//    }
//})

//对一个对象上调用() 表示调用这个对象上的call方法
class Action1{
    def call(){
        println 'call'
    }
}

new Action1()()
