package by.epam.project.hostel.service.impl;

import by.epam.project.hostel.dao.db.transaction.TransactionManager;

public abstract class BaseService {
    protected static final TransactionManager transactionManager = new TransactionManager();
}
