import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/26 0026.
 */
public class listener6_java {

    public static void main(String[] args) throws IOException {
//        Binding binding = new Binding();
//        binding.setVariable("args", new String[]{"src/main/groovy/emial.groovy"});
//        GroovyShell shell = new GroovyShell(binding);
//        shell.evaluate(new File("src/main/groovy/script.groovy"));
        Person person = new Lance();
        person.dream("1111");
    }
}
