package com.example.mess.bhojanalaya.Model;

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
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;
    private String remarks;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    private LocalDateTime timeStamp;

    @ManyToOne
    private User student;

    @ManyToOne
    private Mess mess;


}
