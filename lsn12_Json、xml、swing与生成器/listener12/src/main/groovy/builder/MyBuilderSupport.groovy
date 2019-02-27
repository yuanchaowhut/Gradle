package builder

/**
 * Created by Administrator on 2017/6/25 0025.
 */
class MyBuilderSupport extends BuilderSupport {


    class Node{
        String name
        String value
        Map attr
        def children
        Node(String name, String value, Map attr) {
            this.name = name
            this.value = value
            this.attr = attr
            children = []
        }

        //<xml key='value'/>
        //<xml >xxx</xml>
        def build(Writer writer){
            writer.write("<$name")
            if (attr){
                attr.each {
                    key,value->
                        writer.write(" $key='$value'")
                }
            }
            if (value || children){
                writer.write(">")
                if (value){
                    writer.write(value)
                }
                children.each{
                    it.build(writer)
                }
                writer.write("</$name>")
            }else{
                writer.write("/>")
            }
        }
    }

    def nodes
    Writer writer
    MyBuilderSupport(Writer writer) {
        nodes = [:]
        this.writer = writer
    }
/**
     * 节点与节点的关系
     * @param parent 父节点
     * @param child 子节点
     */
    @Override
    protected void setParent(Object parent, Object child) {
        println "setParent $parent $child"
    }

    /**
     * 完成节点回调
     * @param parent
     * @param node
     */
    @Override
    protected void nodeCompleted(Object parent, Object node) {
        super.nodeCompleted(parent, node)
        println "nodeCompleted $parent $node"
        def currentNode = nodes[node]
        if (parent){
            nodes[parent].children << currentNode
        }else{
            //构建 需要的格式
            currentNode.build(writer)
        }
    }

    @Override
    protected Object createNode(Object name) {
        return createNode(name,null,null)
    }

    @Override
    protected Object createNode(Object name, Object value) {
        return createNode(name,null,value)
    }

    @Override
    protected Object createNode(Object name, Map attributes) {
        return createNode(name,attributes,null)
    }

    /**
     * 动脑学院 版权所有
     *  创建节点时候回调
     */
    @Override
    protected Object createNode(Object name, Map attributes, Object value) {
        println("create Node :$name $value $attributes")
        nodes.put(name,new Node(name,value,attributes))
        return name
    }
}



