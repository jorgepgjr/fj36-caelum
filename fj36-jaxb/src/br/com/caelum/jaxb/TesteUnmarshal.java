package br.com.caelum.jaxb;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class TesteUnmarshal {

	public static void main(String[] args) throws FileNotFoundException, JAXBException {
		JAXBContext context = JAXBContext.newInstance(Livro.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Livro livro = (Livro) unmarshaller.unmarshal(new File("livro.xml"));
		System.out.println("Titulo : " + livro.getTitulo());
		System.out.println("Categoria : " + livro.getCategoria().getNome());
	}

}
