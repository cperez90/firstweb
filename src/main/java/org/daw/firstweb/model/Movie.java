package org.daw.firstweb.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
    private int id;
    private String title;
    private String description;
    private int year;

    public Movie(int id, String title, String description,int year) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.year = year;
    }
}
