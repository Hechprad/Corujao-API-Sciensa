package corujao.test;

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
import io.swagger.repository.ArtistRepository;

//Swagger2SpringBoot = Application.class
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Swagger2SpringBoot.class)
public class ArtistRepositoryTests {

	@Autowired
	private ArtistRepository artistRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private ArtistEntity artist1 = new ArtistEntity(
			"Sophie",
			"Turner",
			LocalDate.of(1996, 2, 21),
			OffsetDateTime.now(), 
			OffsetDateTime.now());
	
	@Before
	public void before() {
		artistRepository.save(artist1);
	}
	
	@After
	public void after() {
		artistRepository.delete(artist1);
	}
	
	@Test
	public void saveDevePersistirDadosNoBanco() {
		
		ArtistEntity artist2 = new ArtistEntity(
				"Sophie",
				"Turner",
				LocalDate.of(1996, 2, 21),
				OffsetDateTime.now(), 
				OffsetDateTime.now());
		
		artistRepository.save(artist2);
		
		Assertions.assertThat(artist2.getId()).isNotNull();
		Assertions.assertThat(artist2.getFirstName()).isEqualTo("Sophie");
		Assertions.assertThat(artist2.getLastName()).isEqualTo("Turner");
		artistRepository.delete(artist2.getId());
	}
	
	@Test
	public void deleteDeveRemoverDadosDoBanco() {
		
		ArtistEntity artist2 = new ArtistEntity(
				"Sophie",
				"Turner",
				LocalDate.of(1996, 2, 21),
				OffsetDateTime.now(), 
				OffsetDateTime.now());
		
		artistRepository.save(artist2);
		
		artistRepository.delete(artist2);
		
		Assertions.assertThat(artistRepository.findOne(artist2.getId())).isNull();
	}
	
	@Test
	public void updateDeveAtualizarDadosDoBanco() {
		
		artistRepository.save(artist1);
		
		artist1.setFirstName("Roberta");
		artist1.setLastName("Almeida");
		artist1.setDateOfBirth(LocalDate.of(1970, 10, 30));
		
		artistRepository.save(artist1);
		
		artist1 = artistRepository.findOne(artist1.getId());
		
		Assertions.assertThat(artist1.getFirstName()).isEqualTo("Roberta");
		Assertions.assertThat(artist1.getLastName()).isEqualTo("Almeida");
		Assertions.assertThat(artist1.getDateOfBirth()).isEqualTo(LocalDate.of(1970, 10, 30));
		
	}

	@Test
	public void naoDeveCriarArtistaSePrimeiroNomeForVazio() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Primeiro nome é obrigatório.");
		
		ArtistEntity artistEntity = new ArtistEntity();
		artistEntity.setLastName("Silva");
		artistEntity.setDateOfBirth(LocalDate.of(1970, 10, 30));
		
		artistRepository.save(new ArtistEntity());
	}
	
	@Test
	public void naoDeveCriarArtistaSeUltimoNomeForVazio() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Sobrenome é obrigatório.");
		
		ArtistEntity artistEntity = new ArtistEntity();
		artistEntity.setFirstName("Antonio");
		artistEntity.setDateOfBirth(LocalDate.of(1970, 10, 30));
		
		artistRepository.save(new ArtistEntity());
	}
	
}
