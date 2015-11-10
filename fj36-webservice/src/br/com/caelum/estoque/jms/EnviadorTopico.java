package br.com.caelum.estoque.jms;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.caelum.payfast.modelo.Pagamento;

@Path("/topico")
@Singleton
public class EnviadorTopico {

	@Resource(lookup = "jms/RemoteConnectionFactory")
	ConnectionFactory factory;
	
	@Resource(lookup = "jms/TOPICO.LIVRARIA")
	Destination topico;
	
	
	@GET
	@Path("/{msg}")
	public void getQuantidade(@PathParam("msg") String msg){
		this.send(msg);
	}
	
	
	public void send(String msg){
		JMSContext ctx = factory.createContext();
		ctx.createProducer().send(topico, msg);
	}
}
