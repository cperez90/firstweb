package org.daw.firstweb.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
    private int id;
    private String title;
    private String descripcion;
    private int year;

    public Movie(int id, String title, String descripcion, int year) {
        this.id = id;
        this.title = title;
        this.descripcion = descripcion;
        this.year = year;
    }
}
