package com.wongnai.interview.movie.external;

import com.wongnai.interview.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MovieDataServiceImpl implements MovieDataService {

	public static final String MOVIE_DATA_URL
			= "https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/movies.json";

	@Autowired
	private RestOperations restTemplate;

	@Autowired
	private ObjectMapper objectMapper;
	private MovieRepository movieRepository;

	@Override
	public MoviesResponse fetchAll() {
		
		try {
			URL url = new URL(MOVIE_DATA_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			MoviesResponse m = objectMapper.readValue(in,MoviesResponse.class);
			in.close();
			return m;
		} catch(Exception e) {
			return null;
		}
		//TODO:
		// Step 1 => Implement this method to download data from MOVIE_DATA_URL and fix any error you may found.
		// Please noted that you must only read data remotely and only from given source,
		// do not download and use local file or put the file anywhere else.


	}
}
