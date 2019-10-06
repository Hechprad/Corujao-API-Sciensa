package corujao.test;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.threeten.bp.OffsetDateTime;

import io.swagger.Swagger2SpringBoot;
import io.swagger.entity.GenreEntity;
import io.swagger.repository.GenreRepository;

@RunWith(SpringRunner.class)
//@DataJpaTest
// Swagger2SpringBoot = Application.class
@SpringBootTest(classes = Swagger2SpringBoot.class) 
public class GenreRepositoryTest {
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void before() {
		
	}
	
	@Test
	public void saveDevePersistirDadosNoBanco() {
		GenreEntity genreEntity = new GenreEntity(
				"lelele", 
				OffsetDateTime.now(), 
				OffsetDateTime.now());
		this.genreRepository.save(genreEntity);
		
		Assertions.assertThat(genreEntity.getId()).isNotNull();
		Assertions.assertThat(genreEntity.getDescription()).isEqualTo("lelele");
	}
	
//	@Test
//	public void deleteDeveRemoverDadosDoBanco() {
//		GenreEntity genreEntity = new GenreEntity(
//				"Terror", 
//				OffsetDateTime.now(), 
//				OffsetDateTime.now());
//		this.genreRepository.save(genreEntity);
//		genreRepository.delete(genreEntity);
//		Assertions.assertThat(genreRepository.findOne(genreEntity.getId())).isNull();
//	}
	
	@Test
	public void updateDeveAtualizarDadosDoBanco() {
		GenreEntity genreEntity = new GenreEntity(
				"Terror", 
				OffsetDateTime.now(), 
				OffsetDateTime.now());
		this.genreRepository.save(genreEntity);
		genreEntity.setDescription("Romance");
		this.genreRepository.save(genreEntity);
		genreEntity = this.genreRepository.findOne(genreEntity.getId());
		Assertions.assertThat(genreEntity.getDescription()).isEqualTo("Romance");
	}
}
