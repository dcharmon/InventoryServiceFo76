package edu.matc.inventory.persistence;

import edu.matc.inventory.persistence.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class GenericDao<T> {

    private final Class<T> type;

    public GenericDao(Class<T> type) {
        this.type = type;
    }

    public int insert(T entity) {
        Transaction transaction = null;
        int id;

        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            id = (int) session.save(entity);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw exception;
        }

        return id;
    }

    public T getById(int id) {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            return session.get(type, id);
        }
    }

    public List<T> getAll() {
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            return session.createQuery("FROM " + type.getName(), type).list();
        }
    }
}