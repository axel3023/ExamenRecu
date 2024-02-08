package edu.utez.mx.examenRecu.service.Impl;

import edu.utez.mx.examenRecu.model.dao.PersonaDao;
import edu.utez.mx.examenRecu.model.dto.PersonaDTO;
import edu.utez.mx.examenRecu.model.entity.PersonaBean;
import edu.utez.mx.examenRecu.service.IPersona;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PersonaImpl implements IPersona {

    private final PersonaDao personaDao;

    @Override
    @Transactional(readOnly = true)
    public List<PersonaBean> findAll() {
        return (List<PersonaBean>) personaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PersonaBean findById(Integer id) {
        return personaDao.findById(id).orElse(null);
    }

    @Override
    public PersonaBean save(PersonaDTO personaDTO) {
        PersonaBean persona = PersonaBean.builder()
                .nombre(personaDTO.getNombre())
                .apellidopaterno(personaDTO.getApellidopaterno())
                .apellidomaterno(personaDTO.getApellidomaterno())
                .direccion(personaDTO.getDireccion())
                .contacto(personaDTO.getContacto())
                .build();

        return personaDao.save(persona);
    }

    @Override
    public PersonaBean update(PersonaDTO personaDTO) {
        PersonaBean persona = personaDao.findById(personaDTO.getId_persona()).orElse(null);
        if(persona != null){

            persona.setNombre(personaDTO.getNombre());
            persona.setApellidopaterno(personaDTO.getApellidopaterno());
            persona.setApellidomaterno(persona.getApellidomaterno());
            persona.setDireccion(personaDTO.getDireccion());
            persona.setContacto(persona.getContacto());
            return personaDao.save(persona);
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(PersonaBean persona) {
        personaDao.delete(persona);
    }
}
