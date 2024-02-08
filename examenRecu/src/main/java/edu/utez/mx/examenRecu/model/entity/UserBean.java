package edu.utez.mx.examenRecu.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id_user;

    @Column(name = "username")
    private String username;

    @Column(name = "password", unique = true)
    private String password;

    @Column(name = "status")
    private boolean status;

}
