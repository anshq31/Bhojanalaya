package com.example.mess.bhojanalaya.DTO.JoinRequestDto;

import com.example.mess.bhojanalaya.Enums.JoinRequestStatus;
import io.jsonwebtoken.security.Request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessJoinRequestResponseDto {
    private Long id;
    private Long messId;
    private String messName;
    private Long studentId;
    private String studentName;
    private String joinRequestStatus;
    private LocalDateTime requestedAt;
}
