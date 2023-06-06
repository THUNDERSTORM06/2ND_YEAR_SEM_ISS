package repository.orm.hibernate;

import domain.Atributie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.IAtributieRepository;

import java.util.List;

public class AtributieRepository implements IAtributieRepository {

    public AtributieRepository(){}

    @Override
    public Atributie findOne(Integer integer) throws IllegalArgumentException {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Atributie atributie = null;

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Atributie> atributies = session.createQuery("from Atributie", Atributie.class).list();
            for (Atributie bk : atributies) {
                if(bk.getId().equals(integer))
                    atributie = new Atributie(bk.getId(),bk.getTitle(),bk.getCatre(),bk.getDescriere(),bk.getYear_of_publication());
            }
            session.getTransaction().commit();
        }

        if(atributie != null)
            System.out.println(atributie);
        else
            System.out.println("Atributie null!!");

        HibernateSession.close();
        return atributie;
    }

    @Override
    public List<Atributie> getAll() {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session  session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Atributie> atributies = session.createQuery("from Atributie ", Atributie.class).list();

            if (!(atributies).isEmpty()) {
                for (Atributie bk : atributies) {
                    if (bk != null) {
                        System.out.println(bk);
                    }
                }
            } else {
                System.out.println("Nu exista carti in baza de date.");
            }
            session.getTransaction().commit();
            HibernateSession.close();
            return atributies;
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public void save(Atributie entity) {
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
    public void update(Atributie entity) {

    }

    @Override
    public void delete(Integer integer) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Atributie crit = session.createQuery("from AtributiePresent where id = :id", Atributie.class)
                        .setParameter("id", integer)
                        .setMaxResults(1)
                        .uniqueResult();
                System.err.println("Stergem atributia " + crit.getId());
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
