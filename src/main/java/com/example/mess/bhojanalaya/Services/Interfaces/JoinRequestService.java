package com.example.mess.bhojanalaya.Services.Interfaces;

import com.example.mess.bhojanalaya.Model.JoinRequest;
import com.example.mess.bhojanalaya.Enums.JoinRequestStatus;

import java.util.List;

public interface JoinRequestService {
    JoinRequest createRequest(Long studentId, Long messId);
    List<JoinRequest> getRequestsByStudent(Long studentId);
    List<JoinRequest> getPendingRequestsForMess(Long messId);
    JoinRequest updateRequestStatus(Long requestId, JoinRequestStatus status);
}
