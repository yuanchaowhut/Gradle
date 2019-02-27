import com.android.build.gradle.api.BaseVariant
import org.gradle.api.Plugin
import org.gradle.api.Project

class Plugin1 implements Plugin<Project>{

    @Override
    void apply(Project project) {
        //
        def android = project.extensions.create('android2',Android2Extensions)
//        android.extensions.create('android2',DefaultExtensions)
        project.android2.extensions.create('defaultConfig',DefaultExtensions)
        //gradle分析完成之后
        project.afterEvaluate {
            //libraryVariants
//            project.android.applicationVariants.all{
//                BaseVariant variant->
//                    variant.
//            }
            println project.android2.compileSdkVersion
            println project.android2.defaultConfig.applicationId

            MyTask task = project.tasks.create('hello',MyTask)
            task.dependsOn project.tasks.getByName('checkDebugManifest')
            project.tasks.getByName('preReleaseBuild').dependsOn task
        }
    }
}