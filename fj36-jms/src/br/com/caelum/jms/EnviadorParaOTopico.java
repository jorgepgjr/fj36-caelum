package br.com.caelum.jms;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EnviadorParaOTopico {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Topic topic =  (Topic) ic.lookup("jms/TOPICO.LIVRARIA");
		try(JMSContext context = factory.createContext("jms", "jms2")){
			JMSProducer producer = context.createProducer();
			
			//Define uma propriedade da fila
			producer.setProperty("formato", "ebook");
			Scanner scanner = new Scanner(System.in);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				producer.send(topic, line);
			}
			scanner.close();
			
//			long i = 0;
//			while (i != 10000000L) {
//				producer.send(topic, "Mensagem de numero " + i);
//				i++;
//			}
//			System.out.println("Fim do envio");
		}
	}
}
