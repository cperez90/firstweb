package org.daw.firstweb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private Long movie_id;
    String comment_text;
    Timestamp created_at;
}
