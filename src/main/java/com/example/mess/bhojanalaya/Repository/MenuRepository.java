package com.example.mess.bhojanalaya.Repository;

import com.example.mess.bhojanalaya.Model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> findByMessId(Long messId);

    Menu findByMessIdAndDate(Long messId, LocalDate date);
}

