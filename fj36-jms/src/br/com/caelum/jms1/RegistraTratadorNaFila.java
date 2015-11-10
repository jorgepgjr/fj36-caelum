package br.com.caelum.jms1;
import java.util.Scanner;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.caelum.jms.TratadorDeMensagem;
 
public class RegistraTratadorNaFila {
 
	/**
	 * @param args
	 * @throws NamingException 
	 * @throws JMSException 
	 */
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext ic = new InitialContext();
		QueueConnectionFactory factory = (QueueConnectionFactory) ic.lookup("ConnectionFactory");
		
		QueueConnection queueConnection = factory.createQueueConnection();
		
		QueueSession queueSession = queueConnection.createQueueSession(false,  Session.AUTO_ACKNOWLEDGE);
		
		Queue queue = (Queue) ic.lookup("FJ36QUEUE");
		
		//TopicSubscriber durableSubscriber = topicSession.createDurableSubscriber(topic, "AssinaturaNotas");
		MessageConsumer consumer = queueSession.createConsumer(queue);
		consumer.setMessageListener(new TratadorDeMensagem());
		
		queueConnection.start();
		
		Scanner teclado = new Scanner(System.in);
		System.out.println("Esperando as mensagens na fila JMS. Aperte ENTER para parar.");
		teclado.nextLine();
		teclado.close();
		queueConnection.close();
		
	}
 
}
