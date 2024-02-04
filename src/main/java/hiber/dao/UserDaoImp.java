package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("From User");
        return query.getResultList();
    }

    @Override
    public void getUserByModelAndSeries(String model, int series) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT u FROM User u " +
                    "WHERE u.car.model = :model " +
                    "AND u.car.series = :series";
            User user = session.createQuery(hql, User.class).setParameter("model", model).setParameter("series", series).uniqueResult();
            if (user != null) {
                System.out.println(user);
            } else {
                System.out.println("Пользователь не найден.");
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }


}
