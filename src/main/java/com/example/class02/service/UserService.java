package com.example.class02.service;

import com.example.class02.model.AppUser;
import com.example.class02.model.Phone;
import com.example.class02.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AppUser> getAppUser() {
        return userRepository.findAll();
    }

    public AppUser createUser(AppUser user) {
        return userRepository.save(user);
    }

    public Optional<AppUser> getAppUserById(Long id) {
        return userRepository.findById(id);
    }

    public void updateUser(Long userId, String name, String email, String password, Phone phone) {
        AppUser user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            for (Phone existingPhone : user.getPhones()) {
                existingPhone.setNumber(phone.getNumber());
                existingPhone.setCityCode(phone.getCityCode());
                existingPhone.setCountryCode(phone.getCountryCode());
            }
            userRepository.save(user);
        }
    }

    public void saveUserWithPhones(AppUser user) {
        for (Phone phone : user.getPhones()) {
            phone.setId(null);
        }
        userRepository.save(user);
    }
}
