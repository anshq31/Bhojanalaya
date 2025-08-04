package com.example.mess.bhojanalaya.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private MealType timeSlot;

    @Enumerated(EnumType.STRING)
    private VisitStatus status;

    @ManyToOne
    private User student;

    @ManyToOne
    private Mess mess;


}
