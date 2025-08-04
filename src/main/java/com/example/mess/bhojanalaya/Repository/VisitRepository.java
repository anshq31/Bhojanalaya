package com.example.mess.bhojanalaya.Repository;

import com.example.mess.bhojanalaya.Model.MealType;
import com.example.mess.bhojanalaya.Model.Visit;
import com.example.mess.bhojanalaya.Model.VisitStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByMessIDAndDateTimeSlot(Long messId, LocalDate date, MealType mealType);

    List<Visit> findByStudentIdAndDate(Long studentId, LocalDate date);

    long countByMessIDAndDateTimeSlotAndStatus(Long messId, LocalDate date, MealType slot, VisitStatus status);
}
