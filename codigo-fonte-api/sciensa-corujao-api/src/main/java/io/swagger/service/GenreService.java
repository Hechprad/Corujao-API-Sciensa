package io.swagger.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.threeten.bp.OffsetDateTime;

import io.swagger.converter.GenreConverter;
import io.swagger.entity.GenreEntity;
import io.swagger.model.Genre;
import io.swagger.repository.GenreRepository;
import io.swagger.util.RespostasUtil;

@Service
public class GenreService {

	//Mensagems para o header
	public static final String MENSAGEM_DADOS_INVALIDOS = "Gênero - Parâmetros invalidos - client side";

	public static final String MENSAGEM_FAIL = "Gêneros não encontrados";

	// camada para conversar com o DB
	@Autowired
	private GenreRepository repository;
	
	@Autowired
	private RespostasUtil respostasUtil;
	
	//Verifica se a Description está vazia
	public boolean isNotValidGenre(Genre genre) {
		
		return StringUtils.isEmpty(genre.getDescription());
		
	}
	
	//Cadastra novo Genre e insere a data do momento que o gênero é criado 
	private Genre cadastraNovoGenre(Genre genre) {
		
		GenreEntity genreEntity = GenreConverter.toGenreEntity(genre);
		genreEntity.setCreatedAt(OffsetDateTime.now());
		genreEntity.setUpdatedAt(OffsetDateTime.now());
		genreEntity = repository.save(genreEntity);
		
		return GenreConverter.toGenre(genreEntity);
		
	}
	
	public ResponseEntity<Genre> save(Genre genre) {
		if(isNotValidGenre(genre)) {
			return respostasUtil.getBadRequestGenre(MENSAGEM_DADOS_INVALIDOS);
		}
		return new ResponseEntity<Genre>(cadastraNovoGenre(genre), HttpStatus.CREATED);
	}

	public ResponseEntity<Genre> getGenreById(Long genreId) {
		
		GenreEntity genreEntity = repository.findGenreById(genreId);
		
		if(genreEntity == null) {
			return respostasUtil.getBadRequestGenre(MENSAGEM_DADOS_INVALIDOS);
		}
		return new ResponseEntity<Genre>(GenreConverter.toGenre(genreEntity), HttpStatus.OK);
	}

	public ResponseEntity<List<Genre>> findAll() {
		
		Iterable<GenreEntity> genreEntity = repository.findAll();
		
		if(genreEntity == null) {
			return respostasUtil.getBadRequestGenders(MENSAGEM_FAIL);
		}
		
		List<Genre> genres = new ArrayList<Genre>();
		
		genreEntity.forEach(genre -> genres.add(GenreConverter.toGenre(genre)));
		
		return new ResponseEntity<List<Genre>>(genres, HttpStatus.OK);
		
	}

	public ResponseEntity<Genre> update(Long genreId, Genre genre) {
		if(isNotValidGenre(genre)) {
			return respostasUtil.getBadRequestGenre(MENSAGEM_DADOS_INVALIDOS);
		}
		return new ResponseEntity<Genre>(updateGenre(genreId, genre), HttpStatus.CREATED);
	}

	private Genre updateGenre(Long genreId, Genre genre) {
		GenreEntity genreEntity = repository.findOne(genreId);
		genreEntity.setDescription(genre.getDescription());
		genreEntity.setUpdatedAt(OffsetDateTime.now());
		
		repository.save(genreEntity);
		
		return GenreConverter.toGenre(genreEntity);
	}

}
