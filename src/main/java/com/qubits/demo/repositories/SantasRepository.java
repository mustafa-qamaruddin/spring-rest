package com.qubits.demo.repositories;

import com.qubits.demo.models.CompositeKey;
import com.qubits.demo.models.Santa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SantasRepository extends CrudRepository<Santa, CompositeKey> {
}
