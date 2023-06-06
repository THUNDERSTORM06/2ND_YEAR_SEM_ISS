package service;

import domain.*;
import repository.IAtributiePresentRepository;
import repository.IAtributieRepository;
import repository.IVoluntarRepository;
import repository.ILiderRepository;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private IAtributieRepository atributieRepository;
    private IVoluntarRepository voluntarRepository;
    private ILiderRepository liderRepository;
    private IAtributiePresentRepository atributiePresentRepository;

    public Service(IAtributieRepository atributieRepository, IVoluntarRepository voluntarRepository, ILiderRepository liderRepository, IAtributiePresentRepository atributiePresentRepository) {
        this.atributieRepository = atributieRepository;
        this.voluntarRepository = voluntarRepository;
        this.liderRepository = liderRepository;
        this.atributiePresentRepository = atributiePresentRepository;
    }

    public User findUser(String username, String password){
        Voluntar cl = voluntarRepository.findByUserAndPass(username,password);
        if(cl != null){
            return cl;
        }
        Lider lr = liderRepository.findByUserAndPass(username,password);
        if(lr != null){
            return lr;
        }
        return null;
    }

    public void addAtributie(Atributie atributie){
        atributieRepository.save(atributie);
    }

    public List<Atributie> getAllAtributii(){
        return atributieRepository.getAll();
    }
    public List<Voluntar> getAllVoluntari(){
        return voluntarRepository.getAll();
    }

    public void addVoluntar(Voluntar voluntar){voluntarRepository.save(voluntar);}

    public void deleteVoluntar(int id){voluntarRepository.delete(id);}

    public void updateVoluntar(Voluntar voluntar){voluntarRepository.update(voluntar);}

    public void deleteAtributie(int id){atributieRepository.delete(id);}


    public void borrowAtributie(AtributiePresent atributiePresent){atributiePresentRepository.save(atributiePresent);}
    public void returnAtributie(Atributie atributie, Voluntar voluntar)
    {
        for(AtributiePresent atributiePresent : atributiePresentRepository.getAll())
            if(atributiePresent.getAtributie() == atributie.getId() && atributiePresent.getVoluntar() == voluntar.getId())
                atributiePresentRepository.returnAtributie(atributiePresent);
    }

    public List<Atributie> getNotPresentAtributii(){
        List<Atributie> atributii = new ArrayList<Atributie>();
        for(Atributie b: atributieRepository.getAll()){
            int ok = 1;
            for(AtributiePresent atributiePresent : atributiePresentRepository.getAll()){
                if(atributiePresent.getAtributie() == b.getId() && atributiePresent.getReturned() == 0)
                    ok = 0;
            }
//            System.err.println(ok);
            if(ok == 1)
                atributii.add(b);
        }

        return atributii;
    }

    public Atributie getAtributie(int id){return atributieRepository.findOne(id);}

    public List<AtributiePresent> getPresentHistory(Voluntar voluntar){
        List<AtributiePresent> atributiePresents = new ArrayList<AtributiePresent>();
        for(AtributiePresent atributiePresent : atributiePresentRepository.getAll())
            if(atributiePresent.getVoluntar() == voluntar.getId())
                atributiePresents.add(atributiePresent);
        return atributiePresents;
    }

    public List<Atributie> getPresentAtributii(Voluntar voluntar){
        List<Atributie> atributii = new ArrayList<Atributie>();
        for(AtributiePresent atributiePresent : atributiePresentRepository.getAll())
            if(atributiePresent.getVoluntar() == voluntar.getId() && atributiePresent.getReturned()==0){
                Atributie atributie = getAtributie(atributiePresent.getAtributie());
                atributii.add(atributie);
            }
        return atributii;
    }
}
