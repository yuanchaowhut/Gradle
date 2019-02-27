import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.ConstructorNode
import org.codehaus.groovy.ast.FieldNode
import org.codehaus.groovy.ast.GroovyClassVisitor
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.PropertyNode
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

/**
 * Created by Administrator on 2017/6/23 0023.
 */
@GroovyASTTransformation
class MyASTTransformation implements ASTTransformation {
    /**
     * 动脑学院 版权所有
     *  nodes:  节点 ast抽象语法树节点
     *  source: 源单元
     *
     *  编译 groovyc -d classes MyASTTransformation.groovy
     *  打包 jar -cf test.jar -C classes . -C resources .
     *  执行 groovy -classpath test.jar lsn11_0.groovy
     */
    @Override
    void visit(ASTNode[] nodes, SourceUnit source) {
//        println nodes
//        println source.AST
//        println source.source.reader.text
        source.AST.classes.each {
            it.visitContents(new GroovyClassVisitor() {
                @Override
                void visitClass(ClassNode node) {

                }

                @Override
                void visitConstructor(ConstructorNode node) {

                }

                @Override
                void visitMethod(MethodNode node) {
                    if (node.name.length() == 1){
                        println "$node.name too short"
                    }
                }

                @Override
                void visitField(FieldNode node) {
                    if (node.name.length() == 1){
                        println "$node.name too short"
                    }
                }

                @Override
                void visitProperty(PropertyNode node) {

                }
            })
        }
    }
}
