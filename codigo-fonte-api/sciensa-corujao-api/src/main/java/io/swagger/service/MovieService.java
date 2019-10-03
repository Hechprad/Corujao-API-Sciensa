package io.swagger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.threeten.bp.OffsetDateTime;

import io.swagger.entity.MovieEntity;
import io.swagger.repository.MovieRepository;
import io.swagger.util.RespostasUtil;

@Service
public class MovieService {
	// Mensagens para o header
	public static final String MENSAGEM_DADOS_INVALIDOS = "Filmes - Parâmetros invalidos - client side";

	public static final String MENSAGEM_FAIL = "Filmes não encontrados";

	public static final String SEM_CONTEUDO = "Filme não encontrado";

	// camada para conversar com o DB
	@Autowired
	private MovieRepository repository;

	@Autowired
	private RespostasUtil respostasUtil;

	// Verifica se o título e o ano de lançamento estão vazios
	public boolean isNotValidMovie(MovieEntity movieEntity) {
		if (StringUtils.isEmpty(movieEntity.getTitle())
				|| StringUtils.isEmpty(String.valueOf(movieEntity.getReleaseYear()))) {
			return true;
		}
		return false;
	}

	// Cadastra novo MovieEntity
	private MovieEntity cadastraNovoMovie(MovieEntity movieEntity) {

		movieEntity.setCreatedAt(OffsetDateTime.now());
		movieEntity.setUpdatedAt(OffsetDateTime.now());
		movieEntity = repository.save(movieEntity);

		return movieEntity;

	}

	public ResponseEntity<MovieEntity> save(MovieEntity movieEntity) {
		if (isNotValidMovie(movieEntity)) {
			return respostasUtil.getBadRequestMovie(MENSAGEM_DADOS_INVALIDOS);
		}
		return new ResponseEntity<MovieEntity>(cadastraNovoMovie(movieEntity), HttpStatus.CREATED);
	}

	public ResponseEntity<MovieEntity> getMovieById(Long movieId) {

		MovieEntity movieEntity = repository.findMovieById(movieId);

		if (movieEntity == null) {
			return respostasUtil.getBadRequestMovie(MENSAGEM_DADOS_INVALIDOS);
		}
		return new ResponseEntity<MovieEntity>(movieEntity, HttpStatus.OK);
	}

	public ResponseEntity<List<MovieEntity>> findAll() {

		Iterable<MovieEntity> movieEntity = repository.findAll();

		if (movieEntity == null) {
			return respostasUtil.getBadRequestMovies(MENSAGEM_FAIL);
		}

		return new ResponseEntity<List<MovieEntity>>((List<MovieEntity>) movieEntity, HttpStatus.OK);

	}

	public ResponseEntity<MovieEntity> update(Long movieId, MovieEntity movieEntity) {
		
		if (isNotValidMovie(movieEntity)) {
			return respostasUtil.getBadRequestMovie(MENSAGEM_DADOS_INVALIDOS);
		}
		return new ResponseEntity<MovieEntity>(updateMovie(movieId, movieEntity), HttpStatus.CREATED);

	}

	private MovieEntity updateMovie(Long movieId, MovieEntity movieEntity) {
		
		MovieEntity movieEntityWillUpdate = repository.findMovieById(movieId);
		
		movieEntityWillUpdate.setTitle(movieEntity.getTitle());
		movieEntityWillUpdate.setReleaseYear(movieEntity.getReleaseYear());
		movieEntityWillUpdate.setUpdatedAt(OffsetDateTime.now());
		movieEntityWillUpdate.setGenres(movieEntity.getGenres());

		repository.save(movieEntityWillUpdate);

		return movieEntityWillUpdate;
	}

	public ResponseEntity<MovieEntity> delete(Long movieId) {
		repository.delete(movieId);
		return respostasUtil.getNoContentMovie(SEM_CONTEUDO);
	}

}
