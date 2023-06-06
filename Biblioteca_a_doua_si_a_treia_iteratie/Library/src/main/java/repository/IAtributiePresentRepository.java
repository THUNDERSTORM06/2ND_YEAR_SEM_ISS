package repository;

import domain.AtributiePresent;

public interface IAtributiePresentRepository extends IRepository<Integer, AtributiePresent>{
    public void returnAtributie(AtributiePresent atributiePresent);
}
