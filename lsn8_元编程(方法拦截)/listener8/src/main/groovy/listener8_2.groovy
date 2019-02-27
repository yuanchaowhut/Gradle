/**
 * Created by Administrator on 2017/4/26 0026.
 */


class Man {

    def dream() {
        println 'dream'
    }

    def propertyMissing(String name) {
        return null
    }

    def propertyMissing(String name, def arg) {

    }

    def methodMissing(String name, def args) {
        println 'methodMissing'
        return 'dream'
    }

}

def man = new Man()
man.dream()



def scanner = new Scanner(System.in)
Thread.start {
    while (true) {
        def msg = scanner.nextLine()
        if (msg == 'exit') {
            break
        }
        if (man.respondsTo(msg)) {
            man."$msg"()
        } else {
            def (name, body) = msg.split(':')
            man.metaClass."$name" = {
                evaluate(body)
            }
        }
    }
}


