package edu.utez.mx.examenRecu.model.dao;

import edu.utez.mx.examenRecu.model.entity.UserBean;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserBean,Integer> {

    Optional<UserBean> findByUsername(String username);
    Optional<User> findByUsernameAndPasswordIsNot(String username, String password);
}
