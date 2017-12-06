package kr.ac.uos.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import kr.ac.uos.software_project.aeat.network.ActiveMQConsumer;
import kr.ac.uos.software_project.aeat.network.ActiveMQProducer;
import org.junit.Test;

public class JMS_Test {
//
    @Test
    public void testSendReceiveMessage() {
        String DESTIANTION_EXAM = "lecture.goal"; // 데스티네이션 이름
                                              // sender 와 receiver둘다에게 필요하다. 우체통 이름같은 것.
        String MQ_ADDRESS = "tcp://172.16.162.203:61616"; // 메시지 브로커 주소
                                              // 메세지를 전달해주는 놈의 주소

        try {
            //ActiveMQProducer producer = new ActiveMQProducer(MQ_ADDRESS);
            //ActiveMQConsumer consumer = new ActiveMQConsumer(MQ_ADDRESS,);

            // Set consumer Destination
            //consumer.setConsumerDestination(DESTIANTION_EXAM);

            // Send Message to Destination
            String text = "2013440043 문범우";
            //producer.sendMessageTo(text, DESTIANTION_EXAM);

            Thread.sleep(1000);
            //producer.closeConnection();
            //consumer.closeConnection();
        } catch (InterruptedException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
