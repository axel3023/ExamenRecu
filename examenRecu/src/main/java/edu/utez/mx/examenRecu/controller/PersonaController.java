package edu.utez.mx.examenRecu.controller;


import edu.utez.mx.examenRecu.model.dto.PersonaDTO;
import edu.utez.mx.examenRecu.model.entity.PersonaBean;
import edu.utez.mx.examenRecu.service.IPersona;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class PersonaController {
    private final IPersona personaService;

    @GetMapping("personas")
    public List<PersonaBean> showAll(){return personaService.findAll();}

    @GetMapping("persona/{id}")
    public PersonaBean showById(@PathVariable Integer id){return personaService.findById(id);}

    @PostMapping("personas")
    public PersonaDTO create(@RequestBody PersonaDTO personaDTO){
        PersonaBean personaSave = personaService.save(personaDTO);

        return PersonaDTO.builder()
                .id_persona(personaSave.getId_persona())
                .nombre(personaSave.getNombre())
                .apellidopaterno(personaSave.getApellidopaterno())
                .apellidomaterno(personaSave.getApellidomaterno())
                .direccion(personaSave.getDireccion())
                .contacto(personaSave.getContacto())
                .build();
    }

    @PutMapping("personas")
    public PersonaDTO update(@RequestBody PersonaDTO personaDTO){
        PersonaBean personaSave = personaService.update(personaDTO);

        return PersonaDTO.builder()
                .id_persona(personaSave.getId_persona())
                .nombre(personaSave.getNombre())
                .apellidopaterno(personaSave.getApellidopaterno())
                .apellidomaterno(personaSave.getApellidomaterno())
                .direccion(personaSave.getDireccion())
                .contacto(personaSave.getContacto())
                .build();
    }

    @DeleteMapping("persona/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            PersonaBean persona = personaService.findById(id);
            personaService.delete(persona);
            return new ResponseEntity<>(persona, HttpStatus.NO_CONTENT);
        }catch (DataAccessException ex){
            response.put("msg",ex.getMessage());
            response.put("usuario",null);
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
