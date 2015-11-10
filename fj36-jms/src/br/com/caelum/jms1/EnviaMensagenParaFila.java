package br.com.caelum.jms1;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EnviaMensagenParaFila {

	/**
	 * @param args
	 * @throws NamingException 
	 * @throws JMSException 
	 */
	public static void main(String[] args) throws NamingException, JMSException {
	
		InitialContext ic = new InitialContext();
		QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) ic.lookup("ConnectionFactory");
		
		QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
		
		QueueSession queueSession = queueConnection.createQueueSession(false,  Session.AUTO_ACKNOWLEDGE);
		
		TextMessage textMessage = queueSession.createTextMessage();
		Queue queue = (Queue) ic.lookup("FJ36QUEUE");
		for (int i = 0; i<1000000; i++) {
			textMessage.setText("Colocando na fila: "+ i);
			System.out.println("Enviando msg: "+i);
			QueueSender sender = queueSession.createSender(queue);
			sender.send(textMessage);
		}
		
		queueConnection.close();
		
	}

}