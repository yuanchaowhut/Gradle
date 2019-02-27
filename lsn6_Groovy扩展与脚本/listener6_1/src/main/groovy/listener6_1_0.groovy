/**
 * Created by Administrator on 2017/4/26 0026.
 */

//本地作用域
def name = '123'
//绑定作用域
pwd = '456'

//println binding.variables
//void func(){
//    println pwd
//    println name
//}
//
//func()
//args = []
//args[0] = 'emial.groovy'
//
//evaluate(new File('script.groovy'))

class Test {
    static void main(args) {
        def binding = new Binding()
        binding.setVariable('args', ['src/main/groovy/emial.groovy'])
        GroovyShell shell = new GroovyShell(getClass().getClassLoader(), binding);
        shell.evaluate(new File('src/main/groovy/script.groovy'));
    }
}




