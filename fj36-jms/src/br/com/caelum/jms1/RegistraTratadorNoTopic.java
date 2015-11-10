package br.com.caelum.jms1;

import java.util.Scanner;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.caelum.jms.TratadorDeMensagem;

public class RegistraTratadorNoTopic {

	/**
	 * @param args
	 * @throws NamingException 
	 * @throws JMSException 
	 */
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext ic = new InitialContext();
		TopicConnectionFactory factory = (TopicConnectionFactory) ic.lookup("ConnectionFactory");
		
		TopicConnection topicConnection = factory.createTopicConnection();
		topicConnection.setClientID("Financeiro");
		
		TopicSession topicSession = topicConnection.createTopicSession(false,  Session.AUTO_ACKNOWLEDGE);
		
		Topic topic = (Topic) ic.lookup("livraria");
		
		TopicSubscriber durableSubscriber = topicSession.createDurableSubscriber(topic, "AssinaturaNotas");
		
		durableSubscriber.setMessageListener(new TratadorDeMensagem());
		
		topicConnection.start();
		
		Scanner teclado = new Scanner(System.in);
		System.out.println("Esperando as mensagens na fila JMS. Aperte ENTER para parar.");
		teclado.nextLine();
		teclado.close();
		topicConnection.close();
		
	}

}