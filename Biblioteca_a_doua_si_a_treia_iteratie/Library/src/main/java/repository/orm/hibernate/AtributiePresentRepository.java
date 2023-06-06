package repository.orm.hibernate;

import domain.AtributiePresent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.IAtributiePresentRepository;

import java.util.List;

public class AtributiePresentRepository implements IAtributiePresentRepository {
    public AtributiePresentRepository(){}
    @Override
    public AtributiePresent findOne(Integer integer) throws IllegalArgumentException {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        AtributiePresent atributiePresent = null;

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<AtributiePresent> atributiePresents = session.createQuery("from AtributiePresent", AtributiePresent.class).list();
            for (AtributiePresent bk : atributiePresents) {
                if(bk.getId().equals(integer))
                    atributiePresent = new AtributiePresent(bk.getId(), bk.getVoluntar(), bk.getAtributie(),bk.getReturned(), bk.getDays());
            }
            session.getTransaction().commit();
        }

        if(atributiePresent != null)
            System.out.println(atributiePresent);
        else
            System.out.println("Atributie null!!");

        HibernateSession.close();
        return atributiePresent;
    }

    @Override
    public List<AtributiePresent> getAll() {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session  session = sessionFactory.openSession()){
            session.beginTransaction();
            List<AtributiePresent> atributiePresents = session.createQuery("from AtributiePresent", AtributiePresent.class).list();

            if (!(atributiePresents).isEmpty()) {
                for (AtributiePresent bk : atributiePresents) {
                    if (bk != null) {
                        System.out.println(bk);
                    }
                }
            } else {
                System.out.println("Nu exista imprumuturi in baza de date.");
            }
            session.getTransaction().commit();
            HibernateSession.close();
            return atributiePresents;
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public void save(AtributiePresent entity) {
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
    public void update(AtributiePresent entity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void returnAtributie(AtributiePresent entity) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();


        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                AtributiePresent atributiePresent = session.load( AtributiePresent.class, entity.getId() );
                atributiePresent.setReturned(1);
                session.update(atributiePresent);
                tx.commit();

            } catch(RuntimeException ex){
                System.err.println("Eroare la update "+ex);
                if (tx!=null)
                    tx.rollback();
            }
        }

        HibernateSession.close();
    }
}
