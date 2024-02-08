package edu.utez.mx.examenRecu.model.dao;

import edu.utez.mx.examenRecu.model.entity.PersonaBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaDao extends JpaRepository<PersonaBean,Integer> {
}
