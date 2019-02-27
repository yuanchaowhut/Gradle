import groovy.swing.SwingBuilder

import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.WindowConstants
import java.awt.FlowLayout

/**
 * Created by Administrator on 2017/6/23 0023.
 */



def builder  = new SwingBuilder()
def swing = builder.frame(title: '测试',size: [100,100],
        layout: new FlowLayout(),defaultCloseOperation: WindowConstants.EXIT_ON_CLOSE){
    label(text:'文本')
    button(text:'按钮',actionPerformed:{

    })
}
swing.setVisible(true)


def frame = new JFrame()
frame.setTitle('测试')
frame.setSize(100,100)
frame.setLayout(new FlowLayout())
frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
new JLabel()
new JButton().addActionListener {

}