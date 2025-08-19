package com.example.mess.bhojanalaya.Services.Implementations;

import com.example.mess.bhojanalaya.Model.JoinRequest;
import com.example.mess.bhojanalaya.Enums.JoinRequestStatus;
import com.example.mess.bhojanalaya.Model.Mess;
import com.example.mess.bhojanalaya.Model.User;
import com.example.mess.bhojanalaya.Repository.JoinRequestRepository;
import com.example.mess.bhojanalaya.Repository.MessRepository;
import com.example.mess.bhojanalaya.Repository.UserRepository;
import com.example.mess.bhojanalaya.Services.Interfaces.JoinRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinRequestServiceImpl implements JoinRequestService {

    @Autowired
    private JoinRequestRepository joinRequestRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessRepository messRepository;

    @Override
    public JoinRequest createRequest(Long studentId, Long messId) {
        User student = userRepository.findById(studentId).orElseThrow();
        Mess mess = messRepository.findById(messId).orElseThrow();

        JoinRequest Request = JoinRequest.builder()
                .student(student)
                .mess(mess)
                .status(JoinRequestStatus.PENDING)
                .requestedAt(java.time.LocalDateTime.now())
                .build();

        return joinRequestRepository.save(Request);
    }

    @Override
    public List<JoinRequest> getRequestsByStudent(Long studentId) {
        return joinRequestRepository.findByStudentId(studentId);
    }

    @Override
    public List<JoinRequest> getPendingRequestsForMess(Long messId) {
        return joinRequestRepository.findByMessIdAndStatus(messId, JoinRequestStatus.PENDING);
    }

    @Override
    public JoinRequest updateRequestStatus(Long requestId, JoinRequestStatus status) {
        JoinRequest req = joinRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Join Request not found"));
        req.setStatus(status);
        return joinRequestRepository.save(req);
    }
}
