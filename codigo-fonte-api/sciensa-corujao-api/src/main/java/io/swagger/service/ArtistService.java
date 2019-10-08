package io.swagger.service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.threeten.bp.OffsetDateTime;

import io.swagger.entity.ArtistEntity;
import io.swagger.entity.MovieEntity;
import io.swagger.error.ResourceNotFoundException;
import io.swagger.repository.ArtistRepository;
import io.swagger.repository.MovieRepository;

@Service
public class ArtistService {

	// camada para conversar com o DB
	@Autowired
	private ArtistRepository repository;

	@Autowired
	private MovieRepository movieRepository;

	/*
	 * ********** Métodos chamados pelo ArtistController **********
	 */

	public ResponseEntity<ArtistEntity> save(ArtistEntity artistEntity) {
		return new ResponseEntity<ArtistEntity>(cadastraNovoArtist(artistEntity), HttpStatus.CREATED);
	}

	public ResponseEntity<ArtistEntity> getArtistById(Long artistId) {
		verifyIfArtistExists(artistId);
		return new ResponseEntity<ArtistEntity>(repository.findOne(artistId), HttpStatus.OK);
	}

	public ResponseEntity<Page<ArtistEntity>> findAll(Pageable pageable) {
		return new ResponseEntity<Page<ArtistEntity>>(buscaTodosArtistas(pageable), HttpStatus.OK);
	}

	public ResponseEntity<Page<ArtistEntity>> searchName(String search, Pageable pageable) {
		return new ResponseEntity<Page<ArtistEntity>>(buscaArtistPeloNome(search, pageable), HttpStatus.OK);
	}

	public ResponseEntity<ArtistEntity> updateArtist(Long artistId, ArtistEntity artistEntity) {
		verifyIfArtistExists(artistId);
		return new ResponseEntity<ArtistEntity>(update(artistId, artistEntity), HttpStatus.CREATED);
	}

	public ResponseEntity<List<MovieEntity>> getFilmography(Long artistId) {
		verifyIfArtistExists(artistId);
		return new ResponseEntity<List<MovieEntity>>(buscaFilmesDoArtista(artistId), HttpStatus.OK);
	}

	/*
	 * ********* Métodos auxiliares *********
	 */

	// Cadastra novo artist e insere a data do momento que é criado
	private ArtistEntity cadastraNovoArtist(ArtistEntity artistEntity) {
		artistEntity.setCreatedAt(OffsetDateTime.now());
		artistEntity.setUpdatedAt(OffsetDateTime.now());
		artistEntity = repository.save(artistEntity);

		// Verifica se o Artist foi realmente cadastrado
		verifyIfArtistExists(artistEntity.getId());

		return artistEntity;
	}

	// Busca todos os artistas cadastrados
	private Page<ArtistEntity> buscaTodosArtistas(Pageable pageable) {
		Page<ArtistEntity> artistEntity = repository.findAll(pageable);
		verifyIfPageHasContent(artistEntity);
		return artistEntity;
	}

	// Busca Artists pelo firstName e lastName
	private Page<ArtistEntity> buscaArtistPeloNome(String search, Pageable pageable) {
		Iterable<ArtistEntity> artists = repository.findAll();

		List<ArtistEntity> artistsFiltrados = new ArrayList<ArtistEntity>();
		artists.forEach(artist -> {
			if (removeAcento(artist.getFirstName() + " " + artist.getLastName()).toLowerCase()
					.contains(removeAcento(search.toLowerCase())))
				artistsFiltrados.add(artist);
		});

		// convertendo List para page
		Page<ArtistEntity> page = new PageImpl<>(artistsFiltrados, pageable, artistsFiltrados.size());
		return page;
	}

	// Remove acento
	private static String removeAcento(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\p{ASCII}]", "");
		return str;
	}

	// Realiza a atualização do Artist
	private ArtistEntity update(Long artistId, ArtistEntity artist) {
		ArtistEntity artistEntityWillUpdate = repository.findOne(artistId);
		artistEntityWillUpdate.setFirstName(artist.getFirstName());
		artistEntityWillUpdate.setLastName(artist.getLastName());
		artistEntityWillUpdate.setDateOfBirth(artist.getDateOfBirth());
		artistEntityWillUpdate.setUpdatedAt(OffsetDateTime.now());

		repository.save(artistEntityWillUpdate);

		return artistEntityWillUpdate;
	}

	// Busca filmes que tenham o Artist no cast
	private List<MovieEntity> buscaFilmesDoArtista(Long artistId) {
		List<MovieEntity> movies = getAllMovies();
		verifyIfMoviesListIsVoid(movies, artistId);
		List<MovieEntity> filmography = new ArrayList<MovieEntity>();

		movies.forEach(movie -> movie.getCast().forEach(artist -> {
			if (artist.getId() == artistId)
				filmography.add(movie);
		}));
		verifyIfMoviesListIsVoid(filmography, artistId);
		return filmography;
	}

	// Busca a lista com todos os filmes cadastrados
	private List<MovieEntity> getAllMovies() {
		Iterable<MovieEntity> movieEntity = movieRepository.findAll();
		List<MovieEntity> movies = (List<MovieEntity>) movieEntity;

		return movies;
	}

	/*
	 * ********* Métodos de Validação *********
	 */

	// Verifica de se o Artista existe pelo ID
	private void verifyIfArtistExists(Long id) {
		if (repository.findOne(id) == null) {
			throw new ResourceNotFoundException("Artista com ID '" + id + "' não encontrado.");
		}
	}

	// Verifica se o retorno do tipo page é null na busca de todos os artistas
	private void verifyIfPageHasContent(Page<ArtistEntity> artistEntity) {
		if (!artistEntity.hasContent()) {
			throw new ResourceNotFoundException("Artistas não encontrados.");
		}
	}

	// Verifica de se a lista de filmes está vazia
	private void verifyIfMoviesListIsVoid(List<MovieEntity> movies, Long artistId) {
		if (movies.isEmpty()) {
			throw new ResourceNotFoundException("Artista com ID '" + artistId + "' não possui filmografia cadastrada.");
		}
	}
}