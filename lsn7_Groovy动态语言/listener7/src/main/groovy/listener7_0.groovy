import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode

/**
 * Created by Administrator on 2017/4/26 0026.
 */


class Person {
    def dream() {

    }
}

class Lance {
    def dream() {

    }
}

void func(person) {
    if (person.respondsTo('dream'))
        person.dream()
}

//func(new Lance())
//func(new Person())
//func('')
//if (new Lance().hasProperty('name')){
//
//}