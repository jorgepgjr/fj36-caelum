package br.com.caelum.estoque.jms;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Enviando para uma fila do próprio servidor
 * @author Jorge Peres
 *
 */
@Path("/topico")
@Singleton
public class EnviadorTopico {

	@Inject
	JMSContext ctx;
	
	@Resource(lookup = "java:jboss/exported/jms/TOPICO.LIVRARIA")
	private Destination topico;
	
	
	@GET
	@Path("/{msg}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getQuantidade(@PathParam("msg") String msg){
		this.send(msg);
		return msg;
	}
	
	
	public void send(String msg){
		ctx.createProducer().send(topico, msg);
	}
}
