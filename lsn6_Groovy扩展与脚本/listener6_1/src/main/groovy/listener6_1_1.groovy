/**
 * Created by Administrator on 2017/4/26 0026.
 */


class Person {
    def dream(Object object) {
        println "person object:" + object
    }
}

class Lance extends Person{
    def dream(String str){
        println "lance str:" + str
    }
}

Person person = new Lance()
person.dream('111')