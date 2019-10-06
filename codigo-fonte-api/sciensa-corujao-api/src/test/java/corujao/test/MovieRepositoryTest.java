package corujao.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
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
	
	private MovieEntity movie = new MovieEntity(
			"Procurando Nemo",
			2003,
			OffsetDateTime.now(), 
			OffsetDateTime.now());
	
	@Before
	public void before() {
		// adicionando gênero
		genreRepository.save(genre);
		
		List<GenreEntity> genres = new ArrayList<GenreEntity>();
		genres.add(genre);
		movie.setGenres(genres);
		
		// adicionando cast e diretor
		artistRepository.save(artistParaCastEDirector);
		
		List<ArtistEntity> cast = new ArrayList<ArtistEntity>();
		cast.add(artistParaCastEDirector);
		movie.setCast(cast);
		
		movieRepository.save(movie);
	}
	
	@After
	public void after() {
		// limpando as listas relacionadas para poder deletar
		movie.setGenres(null);
		
		movie.setCast(null);
		movie.setDirector(artistParaCastEDirector);
		
		movieRepository.save(movie);
		
		movieRepository.delete(movie);
	}
}
