package io.swagger.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.threeten.bp.OffsetDateTime;

@Entity
@Table(name = "genre_tb")
public class GenreEntity implements Serializable{
	
	private static final long serialVersionUID = -3644154780671051785L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Nome do gênero é obrigatório.")
	@Column(nullable = false)
	private String description;

	@Column
	private OffsetDateTime createdAt;

	@Column
	private OffsetDateTime updatedAt;
	
	@ManyToMany(mappedBy = "genres")
	private List<MovieEntity> movies;

	// construtores para os testes
	public GenreEntity() {}
	
	public GenreEntity(String description, 
			OffsetDateTime createdAt, OffsetDateTime updatedAt) {
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public GenreEntity(Long id, String description, 
			OffsetDateTime createdAt, OffsetDateTime updatedAt) {
		this.id = id;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
