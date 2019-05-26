package com.wongnai.interview.movie.search;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.external.MovieData;
import com.wongnai.interview.movie.external.MoviesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieSearchService;
import com.wongnai.interview.movie.external.MovieDataService;

@Component("simpleMovieSearchService")
public class SimpleMovieSearchService implements MovieSearchService {
	@Autowired
	private MovieDataService movieDataService;
	private  MovieRepository movieRepository;

	@Override
	public List<Movie> search(String queryText) {
		List<Movie> m = new ArrayList<>();
		List<String> a = new ArrayList<>();
		for (MovieData i: movieDataService.fetchAll()) {
			a = Arrays.asList(i.getTitle().toLowerCase().split(" "));
			if(a.contains(queryText.toLowerCase())){
				m.add(new Movie(i));
			}
		}

		//TODO: Step 2 => Implement this method by using data from MovieDataService
		// All test in SimpleMovieSearchServiceIntegrationTest must pass.
		// Please do not change @Component annotation on this class
		return m;
	}
}
