package org.daw.firstweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private int year;
    @Transient
    private float rate;

    public Movie(Long id, String title, String description, int year, float rate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.year = year;
        this.rate = rate;
    }
    public Movie(String movieName, String movieDescription, int movieYear) {
        this.title = movieName;
        this.description = movieDescription;
        this.year = movieYear;
    }
}
