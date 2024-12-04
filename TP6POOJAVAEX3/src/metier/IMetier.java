package metier;

import java.util.List;

public interface IMetier<T> {
    T add(T o);
    List<T> getAll();
    T findByNom(String nom);
    void delete(String nom);
    void saveAll();
}