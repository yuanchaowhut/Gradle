/**
 * Created by Administrator on 2017/4/16 0016.
 */

//groovy方法调用
class Person {
    def name
    def age

    def getName() {
        return name + '_'
    }

    void setName(name) {
        this.name = name
    }

    def execute(x, y, z) {
        println "$x $y $z"
    }
}

def person = new Person(name: 'Jack')
//println person.name
//person.name = 'Jack'
//println person.name
//println person.'name'
//def str = 'name'
//println person."$str"
//println person['name']
//println person.@name

//person.execute(4,x: 1, y: 2, z: 3,5)

//def str = 'org.codehaus.groovy:groovy-all:2.4.9'
//
//def (group, name, version,classifier) = str.split(":")
//println group
//println name
//println version


interface OnClickListener {
    void after()

    void doing()

    void before()
}

//void func(OnClickListener listener) {
//    listener.after()
//}

OnClickListener listener = {
        println "hello"
} as OnClickListener

listener.doing()
listener.after()
listener.before()
//func listener

