package org.daw.firstweb.util;

import org.daw.firstweb.dto.MovieDto;

public class MovieMapper {

    public static MovieDto mapToDto(org.daw.firstweb.model.Movie movieDao) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movieDao.getId());
        movieDto.setTitle(movieDao.getTitle());
        movieDto.setDescription(movieDao.getDescription());
        movieDto.setYear(movieDao.getYear());
        return movieDto;
    }
}
