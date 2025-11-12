package org.daw.firstweb.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Transient
    private Long id;
    private String title;
    private String description;
    private int year;
    @Transient
    private float rate;

    public Movie(Long id, String title, String description,int year, float rate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.year = year;
        this.rate = rate;
    }
}
