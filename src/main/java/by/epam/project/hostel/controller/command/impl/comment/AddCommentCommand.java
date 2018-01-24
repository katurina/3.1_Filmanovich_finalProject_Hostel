package by.epam.project.hostel.controller.command.impl.comment;

import by.epam.project.hostel.controller.command.Command;
import by.epam.project.hostel.entity.Comment;
import by.epam.project.hostel.entity.User;
import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static by.epam.project.hostel.controller.constant.Constant.Comment.COMMENT;
import static by.epam.project.hostel.controller.constant.Constant.Comment.RATE;
import static by.epam.project.hostel.controller.constant.Constant.Guestroom.ID;
import static by.epam.project.hostel.controller.constant.Constant.User.USER;


public class AddCommentCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddCommentCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer guestroomId = Integer.valueOf(request.getParameter(ID));
        Integer userId = ((User) request.getSession().getAttribute(USER)).getId();
        String commentText = (String) request.getAttribute(COMMENT);
        LocalDate localDate = LocalDate.now();
        Integer rate = (Integer) request.getAttribute(RATE);
        Comment comment = new Comment(userId, guestroomId, commentText, localDate, rate);
        try {
            ServiceFactory.getInstance().getCommentService().addComment(comment);
        } catch (ServiceException e) {
            logger.error("error during adding comment in db", e);
        }
    }
}
