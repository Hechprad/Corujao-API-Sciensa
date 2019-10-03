package io.swagger.util;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import io.swagger.entity.GenreEntity;
import io.swagger.entity.MovieEntity;

@Component
public class RespostasUtil {
	
	public static final String HEADER_MENSAGEM = "Mensagem";
	
	//Armazenando valores da mensagem de resposta no header
	private MultiValueMap<String, String> getNewHeaderWithMessage(String mensagem) {
		
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		header.add(HEADER_MENSAGEM, mensagem);
		return header;
		
	}
	
	public ResponseEntity<GenreEntity> getBadRequestGenre(String mensagem) {
		return new ResponseEntity<GenreEntity>(getNewHeaderWithMessage(mensagem), HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<GenreEntity> getInternalErrorGenre(String mensagem) {
		return new ResponseEntity<GenreEntity>(getNewHeaderWithMessage(mensagem), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<List<GenreEntity>> getBadRequestGenders(String mensagem) {
		return new ResponseEntity<List<GenreEntity>>(getNewHeaderWithMessage(mensagem), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<MovieEntity> getBadRequestMovie(String mensagem) {
		return new ResponseEntity<MovieEntity>(getNewHeaderWithMessage(mensagem), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<MovieEntity> getInternalErrorMovie(String mensagem) {
		return new ResponseEntity<MovieEntity>(getNewHeaderWithMessage(mensagem), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<List<MovieEntity>> getBadRequestMovies(String mensagem) {
		return new ResponseEntity<List<MovieEntity>>(getNewHeaderWithMessage(mensagem), HttpStatus.BAD_REQUEST);
	}

}
