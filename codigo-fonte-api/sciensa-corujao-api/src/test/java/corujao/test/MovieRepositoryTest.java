package corujao.test;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;

import io.swagger.Swagger2SpringBoot;
import io.swagger.entity.ArtistEntity;
import io.swagger.entity.GenreEntity;
import io.swagger.entity.MovieEntity;
import io.swagger.repository.ArtistRepository;
import io.swagger.repository.GenreRepository;
import io.swagger.repository.MovieRepository;

//Swagger2SpringBoot = Application.class
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Swagger2SpringBoot.class)
public class MovieRepositoryTest {
	
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private GenreEntity genre = new GenreEntity(
			"Animação", 
			OffsetDateTime.now(), 
			OffsetDateTime.now());
	
	private ArtistEntity artistParaCastEDirector = new ArtistEntity(
			"Sophie",
			"Turner",
			LocalDate.of(1996, 2, 21),
			OffsetDateTime.now(), 
			OffsetDateTime.now());
	
	private MovieEntity movie1 = new MovieEntity(
			"Procurando Nemo",
			2003,
			OffsetDateTime.now(), 
			OffsetDateTime.now());
	
	@Before
	public void before() {
		genreRepository.save(genre);
		artistRepository.save(artistParaCastEDirector);
		movieRepository.save(movie1);
	}
	
	@After
	public void after() {
		// limpando as listas relacionadas para poder deletar
		movie1.setGenres(null);
		movie1.setCast(null);
		movie1.setDirector(null);
		movieRepository.save(movie1);
		
		genreRepository.delete(genre);
		movieRepository.delete(movie1);
		artistRepository.delete(artistParaCastEDirector);
	}
	
	
	@Test
	public void saveDevePersistirDadosNoBanco() {
		
		MovieEntity movie2 = new MovieEntity(
				"Harry Potter",
				2000,
				OffsetDateTime.now(), 
				OffsetDateTime.now());
		
		movieRepository.save(movie2);
		
		Assertions.assertThat(movie2.getId()).isNotNull();
		Assertions.assertThat(movie2.getTitle()).isEqualTo("Harry Potter");
		Assertions.assertThat(movie2.getReleaseYear()).isEqualTo(2000);
		movieRepository.delete(movie2.getId());
	}
	
	@Test
	public void deleteDeveRemoverDadosDoBanco() {
		
		MovieEntity movie2 = new MovieEntity(
				"Harry Potter",
				2000,
				OffsetDateTime.now(), 
				OffsetDateTime.now());
		
		movieRepository.save(movie2);
		
		movieRepository.delete(movie2);
		
		Assertions.assertThat(movieRepository.findOne(movie2.getId())).isNull();
	}
	
	@Test
	public void updateDeveAtualizarDadosDoBanco() {
		
		movie1.setTitle("Senhor dos Anéis");
		movie1.setReleaseYear(2001);
		
		movieRepository.save(movie1);
		
		movie1 = movieRepository.findOne(movie1.getId());
		
		Assertions.assertThat(movie1.getTitle()).isEqualTo("Senhor dos Anéis");
		Assertions.assertThat(movie1.getReleaseYear()).isEqualTo(2001);
		
	}

	@Test
	public void updateDeveAdicionarGenerosAoFilme() {
		
		List<GenreEntity> genres = new ArrayList<GenreEntity>();
		genres.add(genre);
		genres.add(genre);
		movie1.setGenres(genres);
		
		Assertions.assertThat(movie1.getGenres().get(0).getDescription()).isEqualTo("Animação");
		Assertions.assertThat(movie1.getGenres().get(1).getDescription()).isEqualTo("Animação");
	}
	
	@Test
	public void updateDeveAdicionarElencoAoFilme() {
		
		List<ArtistEntity> cast = new ArrayList<ArtistEntity>();
		cast.add(artistParaCastEDirector);
		cast.add(artistParaCastEDirector);
		
		movie1.setCast(cast);
		
		Assertions.assertThat(movie1.getCast().get(0).getFirstName()).isEqualTo("Sophie");
		Assertions.assertThat(movie1.getCast().get(0).getLastName()).isEqualTo("Turner");
	}
	
	@Test
	public void updateDeveAdicionarDiretorAoFilme() {
		
		movie1.setDirector(artistParaCastEDirector);
		
		Assertions.assertThat(movie1.getDirector().getFirstName()).isEqualTo("Sophie");
		Assertions.assertThat(movie1.getDirector().getLastName()).isEqualTo("Turner");
	}
	
	@Test
	public void naoDeveCriarFilmeSeTituloForVazio() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Título é obrigatório");
		
		MovieEntity movieEntity = new MovieEntity();
		movieEntity.setReleaseYear(2019);
		
		movieRepository.save(movieEntity);
	}
	
}
