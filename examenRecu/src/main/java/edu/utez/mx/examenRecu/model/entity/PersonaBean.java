package edu.utez.mx.examenRecu.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "personas")
public class PersonaBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer id_persona;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidopaterno")
    private String apellidopaterno;

    @Column(name = "apellidomaterno")
    private String apellidomaterno;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "contacto")
    private String contacto;

}
