package repository.orm.hibernate;

import domain.Lider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.ILiderRepository;

import java.util.List;

public class LiderRepository implements ILiderRepository {

    public LiderRepository(){
    }

    @Override
    public Lider findByUserAndPass(String username, String password) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Lider lider = null;

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Lider> liders = session.createQuery("from Lider", Lider.class).list();
            for (Lider lidr : liders) {
                if(lidr.getUsername().equals(username) && lidr.getPassword().equals(password))
                    lider = new Lider(lidr.getId(),lidr.getUsername(),lidr.getPassword(),lidr.getFirst_name(), lidr.getLast_name(),lidr.getEmail());
            }
            session.getTransaction().commit();
        }

        if(lider != null)
            System.out.println(lider);
        else
            System.out.println("Lider null!!");

        HibernateSession.close();
        return lider;
    }

    @Override
    public Lider findOne(Integer integer) throws IllegalArgumentException {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Lider lider = null;

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Lider> liders = session.createQuery("from Lider", Lider.class).list();
            for (Lider lidr : liders) {
                if(lidr.getId().equals(integer))
                    lider = new Lider(lidr.getId(),lidr.getUsername(),lidr.getPassword(),lidr.getFirst_name(), lidr.getLast_name(),lidr.getEmail());
            }
            session.getTransaction().commit();
        }

        if(lider != null)
            System.out.println(lider);
        else
            System.out.println("Lider null!!");
        HibernateSession.close();
        return lider;
    }

    @Override
    public List<Lider> getAll() {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session  session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Lider> liders = session.createQuery("from Lider", Lider.class).list();

            if (!(liders).isEmpty()) {
                for (Lider lidr : liders) {
                    if (lidr != null) {
                        System.out.println(lidr);
                    }
                }
            } else {
                System.out.println("Nu exista bibliotecari in baza de date.");
            }
            session.getTransaction().commit();
            HibernateSession.close();
            return liders;
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public void save(Lider entity) {

    }

    @Override
    public void update(Lider entity) {

    }

    @Override
    public void delete(Integer integer) {

    }
}
