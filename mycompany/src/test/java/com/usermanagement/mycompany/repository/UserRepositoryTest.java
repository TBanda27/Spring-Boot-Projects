package com.usermanagement.mycompany.repository;


import com.usermanagement.mycompany.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddNew(){
        User user = new User();

        user.setEmail("user2@users.com");
        user.setFirstName("user2");
        user.setLastName("user2LastName");
        user.setPassword("user2Password");

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAll(){
        Iterable<User> userIterable = userRepository.findAll();

        Assertions.assertThat(userIterable).hasSizeGreaterThan(0);

        for(User user: userIterable){
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
        Integer id = 1;
        Optional<User> userOptional= userRepository.findById(id);

        User user = userOptional.get();
        user.setPassword("newUserPassword");

        Assertions.assertThat(user.getPassword()).isEqualTo("newUserPassword");
    }

    @Test
    public void testGet(){
        Integer userId = 1;
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();
        Assertions.assertThat(userOptional).isPresent();
        System.out.println(user);
    }

    @Test
    public void testDelete(){
        Integer userId = 1;
        userRepository.deleteById(userId);
        Optional<User> userOptional = userRepository.findById(userId);

        Assertions.assertThat(userOptional).isNotPresent();
    }
}
