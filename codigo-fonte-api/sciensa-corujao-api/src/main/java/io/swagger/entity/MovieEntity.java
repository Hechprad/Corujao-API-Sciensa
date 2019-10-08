package io.swagger.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.threeten.bp.OffsetDateTime;

@Entity
@Table(name = "movie_tb")
public class MovieEntity implements Serializable {

	private static final long serialVersionUID = 9006144777923865184L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = null;

	@NotEmpty(message = "Título é obrigatório")
	@Column(nullable = false)
	private String title;

	@Column
	private Integer releaseYear = null;

	@ManyToMany
	@JoinTable(	name="movie_has_genre", 
				joinColumns={@JoinColumn(name="movie_id")}, 
				inverseJoinColumns={@JoinColumn(name="genre_id")})
	private List<GenreEntity> genres;

	@ManyToMany
	@JoinTable(	name="movie_has_artist", 
				joinColumns={@JoinColumn(name="movie_id")}, 
				inverseJoinColumns={@JoinColumn(name="artist_id")})
	private List<ArtistEntity> cast;

	@ManyToOne
	@JoinTable(	name="movie_has_director", 
				joinColumns={@JoinColumn(name="movie_id")}, 
				inverseJoinColumns={@JoinColumn(name="director_id")})
	private ArtistEntity director;

	// construtores para os testes
	public MovieEntity() {}
	
	public MovieEntity(String title, Integer releaseYear, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
		this.title = title;
		this.releaseYear = releaseYear;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public MovieEntity(Long id, String title, Integer releaseYear, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
		this.id = id;
		this.title = title;
		this.releaseYear = releaseYear;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	@Column
	private OffsetDateTime createdAt = null;

	@Column
	private OffsetDateTime updatedAt = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public List<GenreEntity> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreEntity> genres) {
		this.genres = genres;
	}

	public ArtistEntity getDirector() {
		return director;
	}

	public void setDirector(ArtistEntity director) {
		this.director = director;
	}

	public List<ArtistEntity> getCast() {
		return cast;
	}

	public void setCast(List<ArtistEntity> cast) {
		this.cast = cast;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cast == null) ? 0 : cast.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		result = prime * result + ((genres == null) ? 0 : genres.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((releaseYear == null) ? 0 : releaseYear.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieEntity other = (MovieEntity) obj;
		if (cast == null) {
			if (other.cast != null)
				return false;
		} else if (!cast.equals(other.cast))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (genres == null) {
			if (other.genres != null)
				return false;
		} else if (!genres.equals(other.genres))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (releaseYear == null) {
			if (other.releaseYear != null)
				return false;
		} else if (!releaseYear.equals(other.releaseYear))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		return true;
	}

}
