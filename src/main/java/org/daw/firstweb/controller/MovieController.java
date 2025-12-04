package org.daw.firstweb.controller;

import org.daw.firstweb.dto.MovieDto;
import org.daw.firstweb.service.MovieService;
import org.daw.firstweb.service.MovieServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    MovieService service = new MovieServiceImpl();
    @GetMapping()
    public List<MovieDto> movies(){
        return service.findAll();
    }
}
