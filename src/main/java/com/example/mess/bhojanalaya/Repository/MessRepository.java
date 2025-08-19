package com.example.mess.bhojanalaya.Repository;

import com.example.mess.bhojanalaya.Model.Mess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessRepository extends JpaRepository<Mess, Long> {
    Optional<Mess> findByAdminId(Long adminId);
}
