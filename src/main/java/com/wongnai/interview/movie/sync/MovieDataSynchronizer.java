package com.wongnai.interview.movie.sync;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.external.MovieDataService;
import com.wongnai.interview.movie.external.MovieData;
import com.wongnai.interview.movie.Movie;

@Component
public class MovieDataSynchronizer {
	@Autowired
	private MovieDataService movieDataService;

	@Autowired
	private MovieRepository movieRepository;

	@Transactional
	public void forceSync() {
		//TODO: implement this to sync movie into repository
		movieRepository.deleteAll();
		for (MovieData i: movieDataService.fetchAll()){
			Movie tmp = new Movie(i);
			movieRepository.save(tmp);
		}
	}
}
