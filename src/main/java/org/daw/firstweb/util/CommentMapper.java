package org.daw.firstweb.util;

import org.daw.firstweb.dao.MovieOrmDao;
import org.daw.firstweb.dto.CommentDto;
import org.daw.firstweb.model.Comment;
import org.daw.firstweb.model.Movie;

public class CommentMapper {

    public static CommentDto mapToDto(Comment commentDao){
        CommentDto commentDto = new CommentDto();
        commentDto.setComment_text(commentDao.getComment_text());
        commentDto.setMovie_id(commentDao.getMovie().getId());
        commentDto.setCreated_at(commentDao.getCreated_at());
        return commentDto;
    }

    public static Comment mapToDao(CommentDto commentDto){
        Comment commentDao = new Comment();
        commentDao.setComment_text(commentDto.getComment_text());
        MovieOrmDao movieOrmDao = new MovieOrmDao();
        Movie movie = movieOrmDao.findById(commentDto.getMovie_id());
        commentDao.setMovie(movie);
        return commentDao;
    }
}
