package com.qubits.demo.repositories;

import com.qubits.demo.models.CompositeKey;
import com.qubits.demo.models.Santa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SantasRepository extends CrudRepository<Santa, CompositeKey> {
  List<Santa> findByYear(int year);
}
