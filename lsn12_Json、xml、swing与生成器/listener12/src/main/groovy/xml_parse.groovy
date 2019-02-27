import groovy.xml.MarkupBuilder
import groovy.xml.Namespace
import groovy.xml.StreamingMarkupBuilder
import org.w3c.dom.NodeList

/**
 * Created by Administrator on 2017/6/23 0023.
 */


def parser = new XmlParser().parse(new File("AndroidManifest.xml"))
//命名空间
def ns = new Namespace('http://schemas.android.com/apk/res/android','android')

//Node node = parser.application[0].activity[0]
//println node.attributes()[ns.name]
//获得application节点
Node node = parser.application[0]
//NodeList
Node meta = node.'meta-data'[0]
node.remove(meta)
node.appendNode('meta-data',[(ns.name):'a',(ns.value):'b',(ns.hh):'hh'])
new XmlNodePrinter(new PrintWriter(new File("replace.xml"))).print(parser)