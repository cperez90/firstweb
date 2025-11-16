package org.daw.firstweb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.daw.firstweb.model.Movie;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private Movie movie;
    String comment_text;
    Timestamp created_at;
}
