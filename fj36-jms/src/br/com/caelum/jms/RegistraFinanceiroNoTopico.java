package br.com.caelum.jms;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RegistraFinanceiroNoTopico {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Topic topic = (Topic) ic.lookup("jms/TOPICO.LIVRARIA");
		
		try(JMSContext context = factory.createContext("jms", "jms2")){
			context.setClientID("Financeiro-AAAA");
			//Como não foi expecificado a property formato, esse consumer vai receber todos os itens do Topico
			JMSConsumer consumer = context.createDurableConsumer(topic,"AssinaturaNotas");
			consumer.setMessageListener(new TratadorDeMensagem());
			context.start();
			Scanner teclado  = new Scanner(System.in);
			System.out.println("Financeiro esperando as mensagens do Topico JMS.");
			System.out.println("Aperte enter para fechar a conexão");
			teclado.nextLine(); //Aperte enter para parar
			teclado.close();
			context.stop();
		}
		
		
	}

}
