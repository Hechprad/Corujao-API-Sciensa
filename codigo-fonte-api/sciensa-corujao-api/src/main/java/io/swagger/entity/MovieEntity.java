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
import javax.persistence.Table;

import org.threeten.bp.OffsetDateTime;

@Entity
@Table(name = "movie_tb")
public class MovieEntity implements Serializable {

	private static final long serialVersionUID = 5708169984669280699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = null;

	@Column(nullable = false)
	private String title;

	@Column
	private Integer releaseYear = null;

	@ManyToMany
	@JoinTable(	name="movie_has_genre", 
				joinColumns={@JoinColumn(name="movie_id")}, 
				inverseJoinColumns={@JoinColumn(name="genre_id")})
	private List<GenreEntity> genres;

//	@ManyToMany
//	private Artist director;

//	@ManyToMany
//	private Set<Artist> cast;

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

//	public MovieEntity setGenre(GenreEntity genresItem) {
//		if (this.genres == null) {
//			this.genres = new ArrayList<GenreEntity>();
//		}
//		this.genres.add(genresItem);
//		return this;
//	}

	public List<GenreEntity> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreEntity> genres) {
		this.genres = genres;
	}

//	public Artist getDirector() {
//		return director;
//	}
//
//	public void setDirector(Artist director) {
//		this.director = director;
//	}

//	public MovieEntity setCastItem(Artist castItem) {
//		if (this.cast == null) {
//			this.cast = (Set<Artist>) new ArrayList<Artist>();
//		}
//		this.cast.add(castItem);
//		return this;
//	}
//
//	public Set<Artist> getCast() {
//		return cast;
//	}
//
//	public void setCast(Set<Artist> cast) {
//		this.cast = cast;
//	}

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
