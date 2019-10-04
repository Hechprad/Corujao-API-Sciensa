package io.swagger.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.threeten.bp.OffsetDateTime;

import io.swagger.entity.ArtistEntity;
import io.swagger.entity.MovieEntity;
import io.swagger.repository.ArtistRepository;
import io.swagger.repository.MovieRepository;
import io.swagger.util.RespostasUtil;

@Service
public class ArtistService {

	// Mensagens para o header
	public static final String MENSAGEM_DADOS_INVALIDOS = "Artista - Parâmetros invalidos - client side";

	public static final String MENSAGEM_FAIL = "Artistass não encontrados";

	// camada para conversar com o DB
	@Autowired
	private ArtistRepository repository;

	@Autowired
	private RespostasUtil respostasUtil;
	
	@Autowired
	private MovieRepository movieRepository;

	// Verifica se nome e sobrenome estão vazios
	public boolean isNotValidArtist(ArtistEntity artistEntity) {

		if(StringUtils.isEmpty(artistEntity.getFirstName()) || StringUtils.isEmpty(artistEntity.getLastName())) {
			return true;
		}
		return false;

	}

	// Cadastra novo artist e insere a data do momento que é criado
	private ArtistEntity cadastraNovoArtist(ArtistEntity artistEntity) {

		artistEntity.setCreatedAt(OffsetDateTime.now());
		artistEntity.setUpdatedAt(OffsetDateTime.now());
		artistEntity = repository.save(artistEntity);

		return artistEntity;

	}

	public ResponseEntity<ArtistEntity> save(ArtistEntity artistEntity) {
		if (isNotValidArtist(artistEntity)) {
			return respostasUtil.getBadRequestArtist(MENSAGEM_DADOS_INVALIDOS);
		}
		return new ResponseEntity<ArtistEntity>(cadastraNovoArtist(artistEntity), HttpStatus.CREATED);
	}

	public ResponseEntity<ArtistEntity> getArtistById(Long artistId) {

		ArtistEntity artistEntity = repository.findArtistById(artistId);

		if (artistEntity == null) {
			return respostasUtil.getBadRequestArtist(MENSAGEM_DADOS_INVALIDOS);
		}
		return new ResponseEntity<ArtistEntity>(artistEntity, HttpStatus.OK);
	}

	public ResponseEntity<Page<ArtistEntity>> findAll(Pageable pageable) {

		Page<ArtistEntity> artistEntity = repository.findAll(pageable);

		if (artistEntity == null) {
			return respostasUtil.getBadRequestArt(MENSAGEM_FAIL);
		}

		return new ResponseEntity<Page<ArtistEntity>>(artistEntity, HttpStatus.OK);

	}

	public ResponseEntity<ArtistEntity> update(Long artistId, ArtistEntity artistEntity) {
		if (isNotValidArtist(artistEntity)) {
			return respostasUtil.getBadRequestArtist(MENSAGEM_DADOS_INVALIDOS);
		}
		return new ResponseEntity<ArtistEntity>(updateArtist(artistId, artistEntity), HttpStatus.CREATED);
	}

	private ArtistEntity updateArtist(Long artistId, ArtistEntity artist) {
		ArtistEntity artistEntityWillUpdate = repository.findArtistById(artistId);
		artistEntityWillUpdate.setFirstName(artist.getFirstName());
		artistEntityWillUpdate.setLastName(artist.getLastName());
		artistEntityWillUpdate.setDateOfBirth(artist.getDateOfBirth());

		artistEntityWillUpdate.setUpdatedAt(OffsetDateTime.now());

		repository.save(artistEntityWillUpdate);

		return artistEntityWillUpdate;
	}

	public List<MovieEntity> getMovieList(){
		Iterable<MovieEntity> movieEntity = movieRepository.findAll();
		List<MovieEntity> movies = (List<MovieEntity>) movieEntity;
		
		return movies;
	}

	public ResponseEntity<Page<ArtistEntity>> searchName(String search, Pageable pageable) {
		
		Iterable<ArtistEntity> artists = repository.findAll();
		List<ArtistEntity> artistsFiltrados = new ArrayList<ArtistEntity>();
		
		artists.forEach(artist -> {
			if(artist.getFirstName().toLowerCase().contains(search.toLowerCase())) artistsFiltrados.add(artist);
		});
		
		// convertento List para page
		final Page<ArtistEntity> page = new PageImpl<>(artistsFiltrados);
		
		return new ResponseEntity<Page<ArtistEntity>>(page, HttpStatus.OK);
	}
}
