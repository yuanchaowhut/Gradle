import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Target

/**
 * Created by Administrator on 2017/6/23 0023.
 */
@Target(ElementType.METHOD)
@GroovyASTTransformationClass('AptAst')
@interface Check {
}
