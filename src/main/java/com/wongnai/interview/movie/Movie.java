package com.wongnai.interview.movie;

import com.wongnai.interview.movie.external.MovieData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

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

	public Movie(MovieData movieData){
		this.name = movieData.getTitle();
		this.actors = movieData.getCast();
		List<InvertedIndex> invertedIndicesTmp = new ArrayList<>();
//		String tmp = "";
		for (String i: Arrays.asList(movieData.getTitle().toLowerCase().split(" "))) {
//			tmp = tmp + " " + i;
//			tmp = tmp.trim();
//			if(!tmp.equals(i)){
			invertedIndicesTmp.add(new InvertedIndex(i,this));
//			}
//			invertedIndicesTmp.add(new InvertedIndex(tmp,this));
		}
		this.invertedIndices = invertedIndicesTmp;

	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
