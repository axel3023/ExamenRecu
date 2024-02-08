package edu.utez.mx.examenRecu.service.Impl;


import edu.utez.mx.examenRecu.model.dao.UserDao;
import edu.utez.mx.examenRecu.model.dto.UserDTO;
import edu.utez.mx.examenRecu.model.entity.UserBean;
import edu.utez.mx.examenRecu.service.IUser;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserImpl implements IUser {

    private final UserDao userDao;

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*";
    private static final int PASSWORD_LENGTH = 8;

    @Override
    @Transactional(readOnly = true)
    public List<UserBean> findAll() {
        return (List<UserBean>) userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserBean findById(Integer id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public UserBean save(UserDTO userDTO) {
        String password = userPasswordGenerator();

        Optional<UserBean> userOptional = userDao.findByUsername(userDTO.getUsername());
        if(userOptional.isPresent()){
            return null;
        }

        UserBean user = UserBean.builder()
                .username(userDTO.getUsername())
                .password(password)
                .status(userDTO.isStatus())
                .build();
        return userDao.save(user);
    }

    @Override
    public UserBean update(UserDTO userDTO) {
        UserBean user = userDao.findById(userDTO.getId_user()).orElse(null);
        if(user != null){
            String password = userPasswordGenerator();
            Optional<User> userOptional= userDao.findByUsernameAndPasswordIsNot(userDTO.getUsername(),user.getPassword());
            if(userOptional.isPresent()){
                return null;
            }

            user.setUsername(userDTO.getUsername());
            user.setPassword(password);
            user.setStatus(userDTO.isStatus());
            return userDao.save(user);
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(UserBean user) {
        userDao.delete(user);
    }

    public String userPasswordGenerator() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

        String allCharacters = LOWERCASE + UPPERCASE + NUMBERS + SPECIAL_CHARACTERS;
        for (int i = 4; i < PASSWORD_LENGTH; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        return password.toString();
    }
}
