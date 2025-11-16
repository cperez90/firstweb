package org.daw.firstweb.service;

import org.daw.firstweb.dao.CommentDao;
import org.daw.firstweb.dao.CommentOrmDao;
import org.daw.firstweb.dto.CommentDto;
import org.daw.firstweb.model.Comment;
import org.daw.firstweb.util.CommentMapper;

import java.util.ArrayList;
import java.util.List;

public class CommentServiceImpl implements CommentService{
    CommentDao cmtDao = new CommentOrmDao();

    @Override
    public List<CommentDto> findAllByMovieId(Long movieId) {
        List<Comment> commentsDao = cmtDao.findAllByMovieID(movieId);
        List<CommentDto> commentsDto = new ArrayList<>();
        for (Comment cmt : commentsDao) {
            CommentDto cmtDto = CommentMapper.mapToDto(cmt);
            commentsDto.add(cmtDto);
        }
        return commentsDto;

    }

    @Override
    public CommentDto findById(Long id) {
        Comment commentDao = cmtDao.findById(id);
        return CommentMapper.mapToDto(commentDao);
    }

    @Override
    public boolean addComment(CommentDto comment) {
        if (comment != null){
            Comment commentDao = CommentMapper.mapToDao(comment);
            cmtDao.addComment(commentDao);
            return true;
        }else return false;
    }

    @Override
    public CommentDto updateComment(CommentDto comment) {
        return null;
    }

    @Override
    public CommentDto deleteCommentById(Long id) {
        return null;
    }
}
