

class Android2Extensions{
	//groovy定义变量编译后会自动为我们生成对应的get/set方法。在gradle文件中配置变量值时，实际上调用的是对应类中的set方法。
    def compileSdkVersion
    def buildToolsVersion


    def test(){
        println 'hahahaha'
    }

    def test(String a ){
        println a
    }
}
