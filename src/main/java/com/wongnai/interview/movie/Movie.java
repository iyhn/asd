package com.wongnai.interview.movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import javax.persistence.*;

import com.wongnai.interview.movie.external.MovieData;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> actors = new ArrayList<>();

	@OneToMany(mappedBy = "movie",cascade= CascadeType.ALL)
	private List<InvertedIndex> invertedIndices;

	/**
	 * Required by JPA.
	 */
	protected Movie() {
	}

	public Movie(String name) {
		this.name = name;
	}

	public Movie(MovieData movieData) {
		this.name = movieData.getTitle();
		this.actors = movieData.getCast();
		List<InvertedIndex> invertedIndicesTmp = new ArrayList<>();
		for (String i: Arrays.asList(movieData.getTitle().toLowerCase().split(" "))) {
			invertedIndicesTmp.add(new InvertedIndex(i,this));
		}
		this.invertedIndices = invertedIndicesTmp;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<InvertedIndex> getInvertedIndices() { return invertedIndices; }

	public void setName(String name) {
		this.name = name;
	}

	public void setInvertedIndices(List<InvertedIndex> invertedIndices) { this.invertedIndices = invertedIndices; }

	public List<String> getActors() {
		return actors;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) { return true; }
		if (object == null) { return false; }
		if (getClass() != object.getClass()) { return false; }
		Movie movie = (Movie)object;
		return this.id.equals(movie.getId());
	}
}
