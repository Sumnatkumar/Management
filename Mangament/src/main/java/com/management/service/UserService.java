package com.management.service;

import com.management.model.Address;
import com.management.model.AddressDTO;
import com.management.model.User;
import com.management.model.UserDTO;
import com.management.repository.AddressRepository;
import com.management.repository.UserRepository;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword()); 
        user.setEmail(userDTO.getEmail());

        User savedUser = userRepository.save(user);

        AddressDTO addressDTO = userDTO.getAddress();
        Address address = new Address();
        address.setUser(savedUser);
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setCountry(addressDTO.getCountry());
        address.setPinCode(addressDTO.getPinCode());

        addressRepository.save(address);

        return savedUser;
    }

    public User authenticate(String username, String password) {
        Pageable pageable;
        User user = userRepository.findByUsername(username, pageable);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public Page searchUsers(String name, String email, Long id, Pageable pageable) {

        if (name != null && !name.isEmpty()) {
            return userRepository.findByUsername(name, pageable);
        }
        else if (email != null && !email.isEmpty()) {
            return userRepository.findByEmail(email, pageable);
        }
        else if (id != null) {
            return userRepository.findById(id)
                    .map(user -> new PageImpl<>(List.of(user), pageable, 1))
                    .orElse(Page.empty());
        }
        else {
            return Page.empty();
        }
    }


}
