import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

/**
 * Created by Administrator on 2017/6/23 0023.
 */
@GroovyASTTransformation
class InjectAST implements ASTTransformation {
    @Override
    void visit(ASTNode[] nodes, SourceUnit source) {
        source.AST.classes.find {
            it.name == 'Content'
        }?.methods?.find {
            it.name == 'soutMsg'
        }?.with {
            //获得methodnode中的代码块实现
            BlockStatement block = code
            //清空原始的实现
//            block.statements.clear()
            //创建自定义实现
            def methods = new AstBuilder().buildFromSpec {
                //dsl 配合 groovyConsole使用
                expression {
                    methodCall {
                        variable('this')
                        constant('println')
                        argumentList {
                            constant('replace')
                        }
                    }
                }
            }
            println methods
//            block.statements.addAll(methods)
             methods =  new AstBuilder().buildFromCode {
                println('1')
                println('2')
            }
            println methods
            //将自定义的实现添加到方法中
            block.statements.addAll(methods[0].statements)
//            block.statements.add(0,methods[0].statements[0])
        }
    }
}
