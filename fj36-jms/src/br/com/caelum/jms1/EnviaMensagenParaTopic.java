package br.com.caelum.jms1;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EnviaMensagenParaTopic {

	/**
	 * @param args
	 * @throws NamingException 
	 * @throws JMSException 
	 */
	public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext ic = new InitialContext();
		
		TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) ic.lookup("ConnectionFactory");
		TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();
		TopicSession topicSession = topicConnection.createTopicSession(false,  Session.AUTO_ACKNOWLEDGE);
		
		TextMessage textMessage = topicSession.createTextMessage();
		Topic topic = (Topic) ic.lookup("livraria");
		for (int i = 0; i<1000000; i++) {
			textMessage.setText("Colocando no topico durável: "+ i);
			System.out.println("Enviando msg: "+i);
			TopicPublisher publisher = topicSession.createPublisher(topic);
			publisher.publish(textMessage);
		}
		
		topicConnection.close();
		
	}

}