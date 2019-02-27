/**
 * Created by Administrator on 2017/6/23 0023.
 */

/**
 * 动脑学院 版权所有
 *  方法合成
 */
class Person {

    def methodMissing(String name, def args) {
        println 'missing'
        if (name.startsWith('play')){
            //生成的class文件 调用方式不一样
//            println metaClass
            Person p = this
//            println p.metaClass
            p.metaClass."$name" = {
                println "invoke $name"
            }
            "$name"(args)
        }
        return null
    }
}

def p = new Person()
//println p.metaClass
p.playGame()
p.playGame()
p.playGame()
