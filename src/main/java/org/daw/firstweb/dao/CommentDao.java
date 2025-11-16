package org.daw.firstweb.dao;

import org.daw.firstweb.model.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> findAll();
    Comment findById(Long id);
    boolean addComment(Comment comment);
    Comment updateComment(Comment comment);
    Comment deleteCommentById(Long id);
}
