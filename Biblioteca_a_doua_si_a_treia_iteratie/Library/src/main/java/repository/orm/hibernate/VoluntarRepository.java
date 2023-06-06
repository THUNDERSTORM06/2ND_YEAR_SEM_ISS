package repository.orm.hibernate;

import domain.Voluntar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.IVoluntarRepository;

import java.util.List;

public class VoluntarRepository implements IVoluntarRepository {
    public VoluntarRepository(){}

    @Override
    public Voluntar findByUserAndPass(String username, String password) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Voluntar voluntar = null;

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Voluntar> voluntars = session.createQuery("from Voluntar ", Voluntar.class).list();
            for (Voluntar cl : voluntars) {
                if(cl.getUsername().equals(username) && cl.getPassword().equals(password))
                    voluntar = new Voluntar(cl.getId(),cl.getUsername(),cl.getPassword(),cl.getFirst_name(), cl.getLast_name(),cl.getEmail(),cl.getPhone_number());
            }
            session.getTransaction().commit();
        }

        if(voluntar != null)
            System.out.println(voluntar);
        else
            System.out.println("Voluntar null!!");

        HibernateSession.close();
        return voluntar;
    }

    @Override
    public Voluntar findOne(Integer integer) throws IllegalArgumentException {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Voluntar voluntar = null;

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Voluntar> voluntars = session.createQuery("from Voluntar", Voluntar.class).list();
            for (Voluntar cl : voluntars) {
                if(cl.getId().equals(integer))
                    voluntar = new Voluntar(cl.getId(),cl.getUsername(),cl.getPassword(),cl.getFirst_name(), cl.getLast_name(),cl.getEmail(),cl.getPhone_number());
            }
            session.getTransaction().commit();
        }

        if(voluntar != null)
            System.out.println(voluntar);
        else
            System.out.println("Voluntar null!!");

        HibernateSession.close();
        return voluntar;
    }

    @Override
    public List<Voluntar> getAll() {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session  session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Voluntar> voluntars = session.createQuery("from Voluntar", Voluntar.class).list();

            if (!(voluntars).isEmpty()) {
                for (Voluntar cl : voluntars) {
                    if (cl != null) {
                        System.out.println(cl);
                    }
                }
            } else {
                System.out.println("Nu exista voluntari in baza de date.");
            }
            session.getTransaction().commit();
            HibernateSession.close();
            return voluntars;
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public void save(Voluntar entity) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(entity);
                tx.commit();
            } catch (RuntimeException ex) {
                HibernateSession.close();
                System.err.println("Eroare la inserare "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        HibernateSession.close();
    }

    @Override
    public void update(Voluntar entity) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();


        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                Voluntar voluntar = session.load( Voluntar.class, entity.getId() );
                System.out.println("------ID REPO UPDATE ------------"+ entity.getId());
                voluntar.setFirst_name(entity.getFirst_name());
                voluntar.setLast_name(entity.getLast_name());
                voluntar.setEmail(entity.getEmail());
                voluntar.setPhone_number(entity.getPhone_number());
                voluntar.setUsername(entity.getUsername());
                voluntar.setPassword(entity.getPassword());

                session.update(voluntar);
                tx.commit();

            } catch(RuntimeException ex){
                System.err.println("Eroare la update "+ex);
                if (tx!=null)
                    tx.rollback();
            }
        }

        HibernateSession.close();
    }

    @Override
    public void delete(Integer integer) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Voluntar crit = session.createQuery("from Voluntar where id = :voluntarId", Voluntar.class)
                        .setParameter("voluntarId", integer)
                        .setMaxResults(1)
                        .uniqueResult();
                System.err.println("Stergem voluntarul " + crit.getId());
                session.delete(crit);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la stergere "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        HibernateSession.close();
    }
}
