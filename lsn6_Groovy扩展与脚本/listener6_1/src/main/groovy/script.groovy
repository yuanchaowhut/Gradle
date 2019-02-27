#!/usr/bin/env groovy -classpath javax.mail.jar
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.MimeMessage

/**
 * Created by Administrator on 2017/4/26 0026.
 */

//运行在服务器上 给所有相关人员发送邮件
//args = []
//args[0] = 'emial.groovy'
def config = new ConfigSlurper().parse(new File(args[0]).toURI().toURL())

//println config.mail.smtp.host
//邮箱会话
def session = Session.getDefaultInstance(config.toProperties(), new Authenticator() {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(config.mail.env.name, config.mail.env.pwd)
    }
})
session.setDebug(true)
//邮件
def msg = new MimeMessage(session)
//设置发件人
msg.setFrom(config.mail.env.name)
//收件人
msg.setRecipients(Message.RecipientType.TO, config.mail.env.toList)
//设置主题
msg.setSubject('ip notify')
//设置正文
def address = InetAddress.localHost
msg.setText(address.hostName + " ip change: " + address.hostAddress)

//Transport.send(msg)