package edu.utez.mx.examenRecu.service;

import edu.utez.mx.examenRecu.model.dto.PersonaDTO;
import edu.utez.mx.examenRecu.model.entity.PersonaBean;

import java.util.List;

public interface IPersona {
    List<PersonaBean> findAll();
    PersonaBean findById(Integer id);
    PersonaBean save(PersonaDTO personaDTO);
    PersonaBean update(PersonaDTO personaDTO);
    void delete(PersonaBean persona);
}
