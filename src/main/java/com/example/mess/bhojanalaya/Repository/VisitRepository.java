package com.example.mess.bhojanalaya.Repository;

import com.example.mess.bhojanalaya.Enums.MealType;
import com.example.mess.bhojanalaya.Model.Visit;
import com.example.mess.bhojanalaya.Enums.VisitStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    // mess aur slot ke liye
    List<Visit> findByMessIdAndDateAndTimeSlot(Long messId, LocalDate date, MealType timeSlot);

    // student aur date ke liye
    List<Visit> findByStudentIdAndDate(Long studentId, LocalDate date);

    // count ke liye
    long countByMessIdAndDateAndTimeSlotAndStatus(Long messId, LocalDate date, MealType timeSlot, VisitStatus status);
}
