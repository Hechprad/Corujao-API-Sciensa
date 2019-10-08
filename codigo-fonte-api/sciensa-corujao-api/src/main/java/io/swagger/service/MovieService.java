package io.swagger.service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.threeten.bp.OffsetDateTime;

import io.swagger.entity.MovieEntity;
import io.swagger.error.ResourceBadRequestException;
import io.swagger.error.ResourceNotFoundException;
import io.swagger.repository.MovieRepository;

@Service
public class MovieService {

	// camada para conversar com o DB
	@Autowired
	private MovieRepository repository;

	/*
	 * ********** Métodos chamados pelo GenreController **********
	 */
	public ResponseEntity<MovieEntity> save(MovieEntity movieEntity) {
		return new ResponseEntity<MovieEntity>(cadastraNovoMovie(movieEntity), HttpStatus.CREATED);
	}

	public ResponseEntity<MovieEntity> getMovieById(Long movieId) {
		verifyIfMovieExists(movieId);
		return new ResponseEntity<MovieEntity>(repository.findOne(movieId), HttpStatus.OK);
	}

	public ResponseEntity<MovieEntity> delete(Long movieId) {
		verifyIfMovieExists(movieId);
		repository.delete(movieId);
		return respondeMovieDeletado();
	}

	public ResponseEntity<Page<MovieEntity>> searchTitle(String search, Pageable pageable) {
		return new ResponseEntity<Page<MovieEntity>>(buscaMoviePeloTitulo(search, pageable), HttpStatus.OK);
	}

	public ResponseEntity<Page<MovieEntity>> findAll(Pageable pageable) {
		return new ResponseEntity<Page<MovieEntity>>(buscaTodosFilmes(pageable), HttpStatus.OK);
	}

	public ResponseEntity<MovieEntity> updateMovie(Long movieId, MovieEntity movieEntity) {
		verifyIfMovieExists(movieId);
		return new ResponseEntity<MovieEntity>(update(movieId, movieEntity), HttpStatus.CREATED);
	}

	/*
	 * ********* Métodos auxiliares *********
	 */

	// Cadastra novo MovieEntity
	private MovieEntity cadastraNovoMovie(MovieEntity movieEntity) {
		verifyValueOfReleaseYear(movieEntity.getReleaseYear());
		movieEntity.setCreatedAt(OffsetDateTime.now());
		movieEntity.setUpdatedAt(OffsetDateTime.now());
		movieEntity = repository.save(movieEntity);

		// Verifica se o Movie foi realmente cadastrado
		verifyIfMovieExists(movieEntity.getId());

		return movieEntity;
	}

	// Retorna status 204 e header com mensagem de sucesso
	private ResponseEntity<MovieEntity> respondeMovieDeletado() {
		MultiValueMap<String, String> header = new LinkedMultiValueMap<String, String>();
		header.add("Mensagem", "Filme removido com sucesso");
		return new ResponseEntity<MovieEntity>(header, HttpStatus.NO_CONTENT);
	}

	// Busca Movies pelo title
	private Page<MovieEntity> buscaMoviePeloTitulo(String search, Pageable pageable) {
		Iterable<MovieEntity> movies = repository.findAll();
		List<MovieEntity> moviesFiltrados = new ArrayList<MovieEntity>();
		
		movies.forEach(movie -> {
			if (removeAcento(movie.getTitle().toLowerCase()).contains(removeAcento(search.toLowerCase())))
				moviesFiltrados.add(movie);
		});
		
		// convertendo List para page
		Page<MovieEntity> page = new PageImpl<>(moviesFiltrados, pageable, moviesFiltrados.size());
		return page;
	}

	
	// Remove acento
	private static String removeAcento(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\p{ASCII}]", "");
		return str;
	}

	// Busca todos os filmes cadastrados
	private Page<MovieEntity> buscaTodosFilmes(Pageable pageable) {
		Page<MovieEntity> movieEntity = repository.findAll(pageable);
		verifyIfPageHasContent(movieEntity);
		return movieEntity;
	}

	// Realiza a atualização do Movie
	private MovieEntity update(Long movieId, MovieEntity movieEntity) {
		MovieEntity movieEntityWillUpdate = repository.findOne(movieId);
		movieEntityWillUpdate.setTitle(movieEntity.getTitle());
		movieEntityWillUpdate.setReleaseYear(movieEntity.getReleaseYear());
		movieEntityWillUpdate.setGenres(movieEntity.getGenres());
		movieEntityWillUpdate.setCast(movieEntity.getCast());
		movieEntityWillUpdate.setDirector(movieEntity.getDirector());
		movieEntityWillUpdate.setUpdatedAt(OffsetDateTime.now());

		repository.save(movieEntityWillUpdate);

		return movieEntityWillUpdate;
	}

	/*
	 * ********* Métodos de Validação *********
	 */

	// Verifica se o ano de lançamento do filme está entre 1800 e o ano atual mais
	// dez anos
	private void verifyValueOfReleaseYear(Integer releaseYear) {
		if (releaseYear == null) {
			throw new ResourceBadRequestException("É necessário um valor entre 1850 e " + (Calendar.getInstance().get(Calendar.YEAR) + 10));
		}
		if (releaseYear < 1850) {
			throw new ResourceBadRequestException("Não existiam cameras nesta época! Ano muito antigo: " + releaseYear);
		}
		if (releaseYear > Calendar.getInstance().get(Calendar.YEAR) + 10) {
			throw new ResourceBadRequestException("Você é o Marty McFly? Ano muito distante: " + releaseYear);
		}
	}

	// Verifica de se o Filme existe pelo ID
	private void verifyIfMovieExists(Long id) {
		if (repository.findOne(id) == null) {
			throw new ResourceNotFoundException("Filme com o ID '" + id + "' não encontrado.");
		}
	}

	// Verifica se o retorno do tipo page é null na busca de todos os filmes
	private void verifyIfPageHasContent(Page<MovieEntity> movieEntity) {
		if (!movieEntity.hasContent()) {
			throw new ResourceNotFoundException("Filmes não encontrados!");
		}
	}

}
