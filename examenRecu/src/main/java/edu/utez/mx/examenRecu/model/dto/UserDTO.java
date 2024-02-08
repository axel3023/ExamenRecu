package edu.utez.mx.examenRecu.model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDTO {
    private Integer id_user;

    private String username;

    private String password;

    private boolean status;
}
