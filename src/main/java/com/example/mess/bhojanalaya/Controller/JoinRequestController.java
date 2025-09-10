package com.example.mess.bhojanalaya.Controller;

import com.example.mess.bhojanalaya.DTO.JoinRequestDto.MessJoinRequestDto;
import com.example.mess.bhojanalaya.DTO.JoinRequestDto.MessJoinRequestResponseDto;
import com.example.mess.bhojanalaya.Services.Implementations.JoinRequestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/join-requests")
@RequiredArgsConstructor
public class JoinRequestController {

    @Autowired
    private JoinRequestServiceImpl joinRequestService;

    @PostMapping("student/{studentId}")
    public ResponseEntity<MessJoinRequestResponseDto> createRequest(
            @PathVariable Long studentId,
            @RequestBody MessJoinRequestDto messJoinRequestDto){
        return ResponseEntity.ok(joinRequestService.createRequest(studentId,messJoinRequestDto));
    }

    @GetMapping("student/{studentId}")
    public ResponseEntity<List<MessJoinRequestResponseDto>> getRequestsByStudent(
            @PathVariable Long studentId
    ){
        return ResponseEntity.ok(joinRequestService.getRequestsByStudent(studentId));
    }

    @GetMapping("mess/{messId}/pending")
    public ResponseEntity<List<MessJoinRequestResponseDto>> getPendingRequestForMess(
            @PathVariable Long messId){
        return ResponseEntity.ok(joinRequestService.getPendingRequestsForMess(messId));
    }

    @PutMapping("{requestedId}/approve")
    public ResponseEntity<MessJoinRequestResponseDto> approveRequest(@PathVariable Long requestedId){
        return ResponseEntity.ok(joinRequestService.approveRequest(requestedId));
    }

    @PutMapping("{requestedId}/reject")
    public ResponseEntity<MessJoinRequestResponseDto> rejectRequest(@PathVariable Long requestedId){
        return ResponseEntity.ok(joinRequestService.rejectRequest(requestedId));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<MessJoinRequestResponseDto>> getRequestByAdmin(@PathVariable Long adminId){
        return  ResponseEntity.ok(joinRequestService.getRequestByAdmin(adminId));
    }
}
