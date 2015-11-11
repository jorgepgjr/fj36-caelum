package br.com.caelum.jaxb;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TesteMarshalJson {
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		Livro livro = new Livro();
		livro.setCodigo("ARQ");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("livro.json"), livro);
	}
}
