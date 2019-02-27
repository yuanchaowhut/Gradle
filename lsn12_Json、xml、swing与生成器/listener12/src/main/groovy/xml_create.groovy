import groovy.xml.MarkupBuilder
import groovy.xml.StreamingMarkupBuilder

/**
 * Created by Administrator on 2017/6/23 0023.
 */



"""<html><head m='a'>hello</head></html>"""

//new File("baidu.html").write("https://www.baidu.com/".toURL().text)

def fw = new FileWriter(new File("normal.xml"))
def builder = new MarkupBuilder(fw)
builder.html{
    mkp.comment("测试")
    head("hello",m:'a'){
        title("动脑学院")
    }
    body{

    }
}

def sb = new StreamingMarkupBuilder()
sb.encoding = 'UTF-8'
def closure  = {
    mkp.xmlDeclaration()
    html{
        head(id:1){

        }
    }
}
def sw = sb.bind(closure)
println  sw.toString()

