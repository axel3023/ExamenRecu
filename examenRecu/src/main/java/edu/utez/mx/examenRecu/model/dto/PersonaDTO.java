package edu.utez.mx.examenRecu.model.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PersonaDTO {
    private Integer id_persona;
    private String nombre;
    private String apellidopaterno;
    private String apellidomaterno;
    private String direccion;
    private String contacto;
}
