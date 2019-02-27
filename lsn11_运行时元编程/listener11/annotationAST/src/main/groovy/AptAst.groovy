import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.codehaus.groovy.ast.stmt.ReturnStatement
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

/**
 * Created by Administrator on 2017/6/23 0023.
 */
@GroovyASTTransformation
class AptAst  implements ASTTransformation{
    @Override
    void visit(ASTNode[] nodes, SourceUnit source) {
//        source.AST.find {it.name == 'Content'}
        def methodNodes = nodes.findAll { it instanceof MethodNode }
        methodNodes.each {
            MethodNode node ->
                def startStatement = new AstBuilder().buildFromCode {
                def start = System.nanoTime()
            }
            def endStatement = new AstBuilder().buildFromCode {
                def use = System.nanoTime()-start
                println("use:${use/1.0e9}")
            }
//            println endStatement
            BlockStatement blockStatement = node.code
            ReturnStatement returnStatement = startStatement[0].statements[0]
            blockStatement.statements.add(0,new ExpressionStatement(returnStatement.expression))
            blockStatement.statements.addAll(endStatement[0].statements)
        }
    }
}
