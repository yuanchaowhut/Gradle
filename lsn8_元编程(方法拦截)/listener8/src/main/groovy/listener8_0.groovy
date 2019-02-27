/**
 * Created by Administrator on 2017/4/26 0026.
 */


class Manager implements GroovyInterceptable {
    static Manager instance;
    def isInit;

    static Manager getInstance() {
        if (null == instance) {
            synchronized (Manager.class) {
                if (null == instance) {
                    instance = new Manager()
                }
            }
        }
        return instance
    }

    def init() {
        isInit = true
    }

    def dream() {
        System.out.println 'i have a dream'
    }

    Object invokeMethod(String name, Object args) {
        System.out.println 'invokeMethod'
        if (name != 'init'){
            if (!isInit){
                System.out.println 'please invoke init first'
                return
            }
        }
        if (metaClass.invokeMethod(this, 'respondsTo', name, args)) {
            metaClass.invokeMethod(this, name, null)
        } else {
            // do some
            System.out.println 'missing method'
        }
    }
}

Manager.instance.init()
Manager.instance.dream()

//p.dream1()
