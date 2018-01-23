package by.epam.project.hostel.controller.command.impl.comment;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.entity.Comment;
import by.epam.project.hostel.service.CommentService;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.project.hostel.controller.constant.Constant.Comment.COMMENTS;
import static by.epam.project.hostel.controller.constant.Constant.Guestroom.ID;

public class GetGuestroomCommentCommand implements Command {

    private static final Logger logger = LogManager.getLogger(GetGuestroomCommentCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer guestroomId = (Integer) request.getAttribute(ID);
        ServiceFactory instance = ServiceFactory.getInstance();
        CommentService commentService = instance.getCommentService();
        try {
            List<Comment> comments = commentService.getCommentsByRoomId(guestroomId);
            request.setAttribute(COMMENTS, comments);
        } catch (ServiceException e) {
            logger.error("error during getting comments", e);
        }
    }
}
