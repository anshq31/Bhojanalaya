package com.example.mess.bhojanalaya.Services.Interfaces;

import com.example.mess.bhojanalaya.DTO.JoinRequestDto.MessJoinRequestDto;
import com.example.mess.bhojanalaya.DTO.JoinRequestDto.MessJoinRequestResponseDto;
import com.example.mess.bhojanalaya.Model.JoinRequest;
import com.example.mess.bhojanalaya.Enums.JoinRequestStatus;

import java.util.List;

public interface JoinRequestService {
    MessJoinRequestResponseDto createRequest(Long studentId, MessJoinRequestDto messJoinRequestDto);
    List<MessJoinRequestResponseDto> getRequestsByStudent(Long studentId);
    List<MessJoinRequestResponseDto> getPendingRequestsForMess(Long messId);
    MessJoinRequestResponseDto approveRequest(Long requestId);
    MessJoinRequestResponseDto rejectRequest(Long requestedId);
    List<MessJoinRequestResponseDto> getRequestByAdmin(Long AdminId);
}
