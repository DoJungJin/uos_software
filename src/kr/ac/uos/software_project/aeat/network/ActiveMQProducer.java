/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.network;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import kr.ac.uos.software_project.aeat.view.MessagePanel;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author comkeen
 */
public class ActiveMQProducer {

    private Connection connection;
    private MessagePanel messagePanel;
    
    public ActiveMQProducer(String address,MessagePanel messagePanel) {
        init(address, messagePanel);     
        
    }

    private void init(String address, MessagePanel messagePanel) {
        try {
            // Create an ActiveMQConnectionFactory to use JMS
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(address);
            
            // Create a Connection
            this.connection = connectionFactory.createConnection();
            connection.start();
            
        } catch (JMSException ex) {
            System.out.print("연결오류가 발생하였습니다.");
            messagePanel.setTextArea("연결오류가 발생하였습니다.");
            Logger.getLogger(ActiveMQProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessageTo(String text, String destinationName,MessagePanel messagePanel) {
        
        try {            
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue(destinationName);
            
            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            
            // Create a messages
            TextMessage message = session.createTextMessage(text);
            
            // Tell the producer to send the message
            System.out.println("Sent message: "+ text);
            producer.send(message);
            messagePanel.clearTextArea();
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnection() {
        try {
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
