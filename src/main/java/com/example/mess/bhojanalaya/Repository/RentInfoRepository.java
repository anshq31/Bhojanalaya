package com.example.mess.bhojanalaya.Repository;

import com.example.mess.bhojanalaya.Model.RentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RentInfoRepository extends JpaRepository<RentInfo, Long> {
    RentInfo findByStudentId(Long studentId);

    List<RentInfo> findByMessIdAndEndDateBefore(Long messId, LocalDate endDate);
}
