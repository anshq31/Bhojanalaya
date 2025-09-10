package com.example.mess.bhojanalaya.Services.Implementations;

import com.example.mess.bhojanalaya.DTO.JoinRequestDto.MessJoinRequestDto;
import com.example.mess.bhojanalaya.DTO.JoinRequestDto.MessJoinRequestResponseDto;
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
    public MessJoinRequestResponseDto createRequest(Long studentId, MessJoinRequestDto messJoinRequestDto) {
        User student = userRepository.findById(studentId).orElseThrow(
                ()-> new RuntimeException("User could not be found"));


        Mess mess = messRepository.findById(messJoinRequestDto.getMessId()).
                orElseThrow(()-> new RuntimeException("Mess could not be found"));

        JoinRequest joinRequest = JoinRequest.builder()
                .student(student)
                .mess(mess)
                .status(JoinRequestStatus.PENDING)
                .requestedAt(java.time.LocalDateTime.now())
                .build();

         joinRequestRepository.save(joinRequest);

         return toResponseDto(joinRequest);
    }

    @Override
    public List<MessJoinRequestResponseDto> getRequestsByStudent(Long studentId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(()-> new RuntimeException("Student not found"));
        List<JoinRequest> requests = joinRequestRepository.findByStudentId(student.getId());

        return requests.stream().map(this::toResponseDto).toList();
    }

    @Override
    public List<MessJoinRequestResponseDto> getPendingRequestsForMess(Long messId) {
        List<JoinRequest> pendingRequests =  joinRequestRepository.findByMessIdAndStatus(messId, JoinRequestStatus.PENDING);

        return pendingRequests.stream().map(this::toResponseDto).toList();
    }

    @Override
    public MessJoinRequestResponseDto approveRequest(Long requestId) {
        JoinRequest request = joinRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Join Request not found"));
        request.setStatus(JoinRequestStatus.APPROVED);
        joinRequestRepository.save(request);
        return toResponseDto(request);
    }

    @Override
    public MessJoinRequestResponseDto rejectRequest(Long requestId) {
        JoinRequest request = joinRequestRepository.findById(requestId)
                .orElseThrow(()-> new RuntimeException("Join Request not found"));
        request.setStatus(JoinRequestStatus.REJECTED);
        joinRequestRepository.save(request);
        return toResponseDto(request);
    }

    @Override
    public List<MessJoinRequestResponseDto> getRequestByAdmin(Long adminId) {
        List<JoinRequest> requests = joinRequestRepository.findByMessAdminId(adminId);
        return requests.stream().map(this::toResponseDto).toList();
    }

    private MessJoinRequestResponseDto toResponseDto(JoinRequest joinRequest){
        return MessJoinRequestResponseDto.builder()
                .id(joinRequest.getId())
                .studentName(joinRequest.getStudent().getName())
                .messName(joinRequest.getMess().getName())
                .joinRequestStatus(joinRequest.getStatus().name())
                .requestedAt(joinRequest.getRequestedAt())
                .build();
    }
}


