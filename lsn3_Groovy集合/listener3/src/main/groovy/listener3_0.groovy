/**
 * Created by Administrator on 2017/4/8 0008.
 */

//list
def lst = ['java1','groovy1','cpp1','oc1']

//println lst[4]
//
//println lst[0..1]
//
//def sublst =  lst[0..1]
//sublst[0] = 100
//println sublst
//println lst

//for (i in lst){
//    print i + ' '
//}

//def sublst = lst.each {
//    print it + ' '
//}
//
//sublst[0] = 100
//println sublst
//println lst

//def sublist = lst.collect{
//     it * 2
//}
//println sublist

//lst.eachWithIndex{ String entry, int i ->
//
//}
//lst.reverseEach {}
//is   java:==
//==   java:equals
//println lst.findAll {
//    it == '2'
//}

//println lst + 100
//println lst - '2'
//lst << ['99','100']
//['1','2','3','2','99','100']
//拉平
//println lst.flatten()

//展开操作符
//println lst*.toUpperCase()

//println lst.any{
//    it == '11'
//}

println lst.every{
    it =~ '1'
}