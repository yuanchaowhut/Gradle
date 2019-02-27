/**
 * Created by Administrator on 2017/4/17 0017.
 */

//线程 流
def server = new ServerSocket(9999)

def isRunning = true

def sockets = []

def close(socket) {
    if (null != socket) {
        if (!socket.isClosed()) {
            socket.close()
        }
    }
}

InputStream is
is.text


sockets.each {}

Thread.start {
    println("开启服务器")
    while (isRunning) {
        try {
            def socket = server.accept()
            sockets << socket
            println("新的连接:${socket.remoteSocketAddress}")
            Thread.start {
                //读取内容
                while (!socket.isClosed()) {
                    try {
                        def buffer = new byte[2048]
                        def len = socket.inputStream.read(buffer)
                        if (len > 0)
                            println "[${socket.remoteSocketAddress}]:${new String(buffer, 0, len)}"
                    } catch (ex) {
                        println "异常:${ex.getLocalizedMessage()}"
                    }
                }
                sockets.remove(socket)
                println("断开连接:${socket.remoteSocketAddress}")
            }
        } catch (ex) {
            println "异常:${ex.getLocalizedMessage()}"
        }
    }
    println("关闭服务器")
    close(server)
    sockets.each {
        close(it)
    }
}
def scanner = new Scanner(System.in)

Thread.start {
    while (isRunning) {
        def msg = scanner.nextLine()
        if (msg == 'exit') {
            isRunning = false
            close(server)
            sockets.each {
                close(it)
            }
            break
        }
        sockets*.outputStream*.write(msg.getBytes())
    }
}