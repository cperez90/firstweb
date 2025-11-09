package org.daw.firstweb.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
    private int id;
    private String title;
    private String description;

    public Movie(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
