/**
 * Created by Administrator on 2017/6/23 0023.
 */


/**
 * 动脑学院 版权所有
 *  动态类
 */

def expando = new Expando(name:'hello',fun1:{"fun1"})
expando.height = 100
expando.fun2 = {
    "fun2"
}

println expando.name
println expando.height
println expando.fun1()
println expando.fun2()