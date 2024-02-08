package edu.utez.mx.examenRecu.controller;

import edu.utez.mx.examenRecu.model.dto.UserDTO;
import edu.utez.mx.examenRecu.model.entity.UserBean;
import edu.utez.mx.examenRecu.service.IUser;
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
public class UserController {

    private final IUser userService;

    @GetMapping("usuarios")
    public List<UserBean> showAll(){return userService.findAll();}

    @GetMapping("usuario/{id}")
    public UserBean showById(@PathVariable Integer id){return userService.findById(id);}

    @PostMapping("usuarios")
    public UserDTO create(@RequestBody UserDTO userDTO){
        UserBean userSave = userService.save(userDTO);
        if(userSave==null){
            return null;
        }
        return UserDTO.builder()
                .id_user(userSave.getId_user())
                .username(userSave.getUsername())
                .password(userSave.getPassword())
                .status(userSave.isStatus())
                .build();
    }

    @PutMapping("usuarios")
    public UserDTO update(@RequestBody UserDTO userDTO){
        UserBean userSave = userService.update(userDTO);
        if(userSave==null){
            return null;
        }
        return UserDTO.builder()
                .id_user(userSave.getId_user())
                .username(userSave.getUsername())
                .password(userSave.getPassword())
                .status(userSave.isStatus())
                .build();
    }

    @DeleteMapping("usuario/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            UserBean user = userService.findById(id);
            userService.delete(user);
            return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
        }catch (DataAccessException ex){
            response.put("msg",ex.getMessage());
            response.put("usuario",null);
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
