package org.daw.firstweb.service;
import org.daw.firstweb.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> findAllByMovieId(Long movieId);
    CommentDto findById(Long id);
    boolean addComment(CommentDto comment);
    CommentDto updateComment(CommentDto comment);
    CommentDto deleteCommentById(Long id);
}
