package com.example.mess.bhojanalaya.Repository;

import com.example.mess.bhojanalaya.Model.JoinRequest;
import com.example.mess.bhojanalaya.Enums.JoinRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JoinRequestRepository extends JpaRepository<JoinRequest, Long> {
    List<JoinRequest> findByMessIdAndStatus(Long messId, JoinRequestStatus status);
    List<JoinRequest> findByStudentId(Long studentId);
    List<JoinRequest> findByMessAdminId(Long adminId);
}
