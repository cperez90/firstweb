package org.daw.firstweb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.daw.firstweb.model.Comment;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class MovieDto {
    private Long id;
    private String title;
    private String description;
    private int year;
    private List<Comment> comments = new ArrayList<>();

    public MovieDto(Long id, String title, String description, int year) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.year = year;
    }
    public MovieDto(String movieName, String movieDescription, int movieYear) {
        this.title = movieName;
        this.description = movieDescription;
        this.year = movieYear;
    }
}
