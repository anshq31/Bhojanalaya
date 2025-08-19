package com.example.mess.bhojanalaya.Repository;

import com.example.mess.bhojanalaya.Enums.Role;
import com.example.mess.bhojanalaya.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByRole(Role role);

    List<User> findByMessId(Long messId);

}
