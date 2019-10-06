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

import io.swagger.entity.GenreEntity;
import io.swagger.error.ResourceNotFoundException;
import io.swagger.repository.GenreRepository;

@Service
public class GenreService {

	// camada para conversar com o DB
	@Autowired
	private GenreRepository repository;

	/*
	 * ********** Métodos chamados pelo GenreController **********
	 */

	public ResponseEntity<GenreEntity> save(GenreEntity genreEntity) {
		return new ResponseEntity<GenreEntity>(cadastraNovoGenre(genreEntity), HttpStatus.CREATED);
	}

	public ResponseEntity<GenreEntity> getGenreById(Long genreId) {
		verifyIfGenreExists(genreId);
		return new ResponseEntity<GenreEntity>(repository.findOne(genreId), HttpStatus.OK);
	}

	public ResponseEntity<Page<GenreEntity>> findAll(Pageable pageable) {
		return new ResponseEntity<Page<GenreEntity>>(buscaTodosGeneros(pageable), HttpStatus.OK);
	}

	public ResponseEntity<Page<GenreEntity>> searchDescription(String search, Pageable pageable) {
		return new ResponseEntity<Page<GenreEntity>>(buscaGenrePeloTitulo(search), HttpStatus.OK);
	}

	public ResponseEntity<GenreEntity> updateGenre(Long genreId, GenreEntity genreEntity) {
		return new ResponseEntity<GenreEntity>(update(genreId, genreEntity), HttpStatus.CREATED);
	}

	/*
	 * ********* Métodos auxiliares *********
	 */

	// Cadastra novo Genre e insere a data do momento que o gênero é criado
	private GenreEntity cadastraNovoGenre(GenreEntity genreEntity) {
		genreEntity.setCreatedAt(OffsetDateTime.now());
		genreEntity.setUpdatedAt(OffsetDateTime.now());
		genreEntity = repository.save(genreEntity);

		// Verifica se o Genre foi realmente cadastrado
		verifyIfGenreExists(genreEntity.getId());

		return genreEntity;

	}

	// Busca todos os gêneros cadastrados
	private Page<GenreEntity> buscaTodosGeneros(Pageable pageable) {
		Page<GenreEntity> genreEntity = repository.findAll(pageable);
		verifyIfPageHasContent(genreEntity);
		return genreEntity;
	}

	// Busca Genre pela Desription
	private Page<GenreEntity> buscaGenrePeloTitulo(String search) {
		Iterable<GenreEntity> genres = repository.findAll();
		List<GenreEntity> genresFiltrados = new ArrayList<GenreEntity>();

		genres.forEach(genre -> {
			if (removeAcento(genre.getDescription().toLowerCase()).contains(removeAcento(search.toLowerCase())))
				genresFiltrados.add(genre);
		});

		// convertento List para page
		final Page<GenreEntity> page = new PageImpl<>(genresFiltrados);
		return page;
	}

	// Remove acento
	private static String removeAcento(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\p{ASCII}]", "");
		return str;
	}

	// Realiza a atualização do Genre
	private GenreEntity update(Long genreId, GenreEntity genreEntity) {
		GenreEntity genreEntityWillUpdate = repository.findOne(genreId);
		genreEntityWillUpdate.setDescription(genreEntity.getDescription());
		genreEntityWillUpdate.setUpdatedAt(OffsetDateTime.now());

		repository.save(genreEntityWillUpdate);

		return genreEntityWillUpdate;
	}

	/*
	 * ********* Métodos de Validação *********
	 */

	// Verifica de se o Gênero existe pelo ID
	private void verifyIfGenreExists(Long id) {
		if (repository.findOne(id) == null) {
			throw new ResourceNotFoundException("Gênero com ID '" + id + "' não encontrado.");
		}
	}

	// Verifica se o retorno do tipo page é null na busca de todos os gêneros
	private void verifyIfPageHasContent(Page<GenreEntity> genreEntity) {
		if (!genreEntity.hasContent()) {
			throw new ResourceNotFoundException("Gêneros não encontrados");
		}
	}

}
