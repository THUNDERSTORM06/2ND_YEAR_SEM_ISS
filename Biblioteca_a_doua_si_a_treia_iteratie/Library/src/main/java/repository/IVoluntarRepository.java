package repository;

import domain.Voluntar;

public interface IVoluntarRepository extends IRepository<Integer, Voluntar>{
    Voluntar findByUserAndPass(String username, String password);
}
