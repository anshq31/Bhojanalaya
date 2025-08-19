package com.example.mess.bhojanalaya.Model;

import com.example.mess.bhojanalaya.Enums.JoinRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User student;

    @ManyToOne
    private Mess mess;

    @Enumerated(EnumType.STRING)
    private JoinRequestStatus status;

    private LocalDateTime requestedAt;

}
