import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

/**
 * Created by Administrator on 2017/6/23 0023.
 */


def builder = new JsonBuilder()
builder{
    first 'lance'
    last 'haha'
}

println builder

class Person{
    def first
    def last
}

def p = new Person(first: 'lance',last: 'haha')
//转换对象为json数据
def b = new JsonBuilder(p)
println b.toString()

println JsonOutput.toJson(p)
//格式化json数据
println JsonOutput.prettyPrint(JsonOutput.toJson(p))


def slurper = new JsonSlurper()
Person person = slurper.parseText(JsonOutput.toJson(p))
println person.first
println person.last

