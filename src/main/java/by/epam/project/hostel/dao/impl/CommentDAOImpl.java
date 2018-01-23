package by.epam.project.hostel.dao.impl;

import by.epam.project.hostel.dao.CommentDAO;

public class CommentDAOImpl extends EntityDAOImpl implements CommentDAO{



    /**
     *
     *
     * @return
     */
    @Override
    protected String getTableName() {
        return "comments";
    }
}
