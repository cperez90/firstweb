package org.daw.firstweb.util;

import org.daw.firstweb.dto.MovieDto;
import org.daw.firstweb.model.Movie;

public class MovieMapper {

    public static MovieDto mapToDto(Movie movieDao) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movieDao.getId());
        movieDto.setTitle(movieDao.getTitle());
        movieDto.setDescription(movieDao.getDescription());
        movieDto.setYear(movieDao.getYear());
        movieDto.setComments(movieDao.getComments());
        return movieDto;
    }

    public static Movie mapToDao(MovieDto movieDto){
        Movie movieDao = new Movie();
        movieDao.setId(movieDto.getId());
        movieDao.setTitle(movieDto.getTitle());
        movieDao.setDescription(movieDto.getDescription());
        movieDao.setYear(movieDto.getYear());
        return movieDao;
    }
}
