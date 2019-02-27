package builder

/**
 * Created by Administrator on 2017/6/25 0025.
 */


StringWriter sw = new StringWriter()
def builder = new MyBuilderSupport(sw)
builder.html{
    head('动脑学院',key:'value'){
        title("动脑")
    }
    body{

    }
    x{
        y{
            z{

            }
        }
    }
}

println(sw)

