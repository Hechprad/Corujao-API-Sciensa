package corujao.test;
import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
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

// Swagger2SpringBoot = Application.class
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Swagger2SpringBoot.class)
public class GenreRepositoryTests {
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private GenreEntity genreEntity = new GenreEntity(
			"Aventura", 
			OffsetDateTime.now(), 
			OffsetDateTime.now());
	
	@Test
	public void saveDevePersistirDadosNoBanco() {
		
		this.genreRepository.save(genreEntity);
		
		Assertions.assertThat(genreEntity.getId()).isNotNull();
		Assertions.assertThat(genreEntity.getDescription()).isEqualTo("Aventura");
		
		genreRepository.delete(genreEntity.getId());
	}
	
	@Test
	public void deleteDeveRemoverDadosDoBanco() {
		
		this.genreRepository.save(genreEntity);
		
		genreRepository.delete(genreEntity);
		
		Assertions.assertThat(genreRepository.findOne(genreEntity.getId())).isNull();
	}
	
	@Test
	public void updateDeveAtualizarDadosDoBanco() {
		
		this.genreRepository.save(genreEntity);
		
		genreEntity.setDescription("Romance");
		
		this.genreRepository.save(genreEntity);
		
		genreEntity = this.genreRepository.findOne(genreEntity.getId());
		
		Assertions.assertThat(genreEntity.getDescription()).isEqualTo("Romance");
		
		genreRepository.delete(genreEntity.getId());
	}
	
	@Test
	public void naoDeveCriarGeneroSeDescriptionForVazia() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Nome do gênero é obrigatório.");
		this.genreRepository.save(new GenreEntity());
	}
}
