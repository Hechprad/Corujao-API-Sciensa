package io.swagger.util;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import io.swagger.model.Genre;

@Component
public class RespostasUtil {
	
	public static final String HEADER_MENSAGEM = "Mensagem";
	
	//Armazenando valores da mensagem de resposta no header
	private MultiValueMap<String, String> getNewHeaderWithMessage(String mensagem) {
		
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		header.add(HEADER_MENSAGEM, mensagem);
		return header;
		
	}
	
	public ResponseEntity<Genre> getBadRequestGenre(String mensagem) {
		return new ResponseEntity<Genre>(getNewHeaderWithMessage(mensagem), HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<Genre> getErroInternoGenre(String mensagem) {
		return new ResponseEntity<Genre>(getNewHeaderWithMessage(mensagem), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<List<Genre>> getBadRequestGenders(String mensagem) {
		return new ResponseEntity<List<Genre>>(getNewHeaderWithMessage(mensagem), HttpStatus.BAD_REQUEST);
	}

}
