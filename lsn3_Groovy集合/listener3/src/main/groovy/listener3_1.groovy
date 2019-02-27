/**
 * Created by Administrator on 2017/4/8 0008.
 */


def map = [B: 'Baidu', 'A': 'Alibaba', T: 'Tencent']

//println map.getClass()
//println map.A
//println map['A']
//println map.'A'

//map.each {
//    key, value ->
//        println "${key}=${value}"
//}

//println map.find {
//    key,value->
//        value =~ 'a'
//}

//println map + ['a':'b']
map << ['a':'b']
println map*.getKey()