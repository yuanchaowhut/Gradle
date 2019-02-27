/**
 * Created by Administrator on 2017/4/16 0016.
 */
class Rmb {
    def number

    boolean equals(o) {
        println('equals')
        if (this.is(o)) return true
        if (!(o instanceof Rmb)) return false

        Rmb rmb = (Rmb) o

        if (number != rmb.number) return false

        return true
    }

    int hashCode() {
        return (number != null ? number.hashCode() : 0)
    }

    def isCase(o) {
        println o
        if (o instanceof Rmb) {
            o.number == 100
        } else
            false
    }

    def plus(o) {
        if (o instanceof Number) {
            new Rmb(number: number + o)
        } else if (o instanceof Rmb) {
            new Rmb(number: number + o.number)
        } else {
            throw new MissingMethodException('plus', o.class, o)
        }
    }


    @Override
    public String toString() {
        return "Rmb{" +
                "number=" + number +
                '}';
    }
}

def rmb = new Rmb(number: 100)

switch (rmb) {
    case new Rmb(number: 100):
        println "1"
        break
    case 100:
        println "2"
        break
}


rmb == '111'

//println rmb + ""
