package javatest.wcc.CalculateDistance.repo;

import javatest.wcc.CalculateDistance.model.Postal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostalRepository extends CrudRepository<Postal, Integer> {
    // Using a custom JPQL query
    @Query("SELECT p FROM Postal p")
   // @Query(value = "SELECT * FROM Postal.postaloutcode", nativeQuery = true)
    List<Postal> findAll();

    Postal findByPostcode1(String postcode1);

    @Query("SELECT p.postcode1 FROM Postal p")
    List<String> findAllPostcode1();
}
