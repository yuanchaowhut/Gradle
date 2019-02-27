/**
 * Created by Administrator on 2017/4/17 0017.
 */
class FileExtension {
//org.codehaus.groovy.runtime.ExtensionModule
    static File start(File self, Closure closure) {
        closure()
        return self
    }

}
