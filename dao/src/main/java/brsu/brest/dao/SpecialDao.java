package brsu.brest.dao;

import brsu.brest.model.Special;

import java.util.List;
import java.util.Optional;

public interface SpecialDao {

    List<Special> findAll();

    Optional<Special> findById(Integer specialId);

    Integer create(Special special);

    Integer  update(Special special);

    Integer  delete(Integer specialId);

}
