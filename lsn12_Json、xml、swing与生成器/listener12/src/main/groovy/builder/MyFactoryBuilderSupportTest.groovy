package builder

/**
 * Created by Administrator on 2017/6/25 0025.
 */
StringWriter sw = new StringWriter()
new MyFactoryBuilderSupport().html{
    head('动脑学院',key:'value'){

    }
}.build(sw)
println sw