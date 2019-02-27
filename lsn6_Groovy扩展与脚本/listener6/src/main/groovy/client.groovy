/**
 * Created by Administrator on 2017/4/17 0017.
 */


def client = new Socket('localhost', 9999)

Thread.start {
    while (!client.isClosed()) {
        try {
            def buffer = new byte[2048]
            def len = client.inputStream.read(buffer)
            if (len > 0)
                println "[${client.remoteSocketAddress}]:${new String(buffer, 0, len)}"
        } catch (ex) {
            println "异常:${ex.getLocalizedMessage()}"
        }
    }
}

def scanner = new Scanner(System.in)

Thread.start {
    while (!client.isClosed()) {
        def msg = scanner.nextLine()
        if (msg == 'exit') {
            isRunning = false
            client.close()
            break
        }
        client.outputStream.write(msg.getBytes())
    }
}
