import groovy.transform.CompileStatic

/**
 * Created by Administrator on 2017/4/26 0026.
 */


@CompileStatic
class Test {
    static void main(arg) {
        def start = System.nanoTime()
        for (int i = 0; i < 10000000; i++) {

        }
        def end = System.nanoTime()
        println((end - start) / 1.0e9)
    }
}