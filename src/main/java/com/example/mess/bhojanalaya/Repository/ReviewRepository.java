package com.example.mess.bhojanalaya.Repository;

import com.example.mess.bhojanalaya.Enums.MealType;
import com.example.mess.bhojanalaya.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMessIdAndMealType(Long messId, MealType mealType);
}
