package repository;

import domain.Lider;

public interface ILiderRepository extends IRepository<Integer, Lider>{
    Lider findByUserAndPass(String username, String password);
}
