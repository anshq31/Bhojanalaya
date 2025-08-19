package com.example.mess.bhojanalaya.Services.Interfaces;

import com.example.mess.bhojanalaya.Model.Mess;

import java.util.List;
import java.util.Optional;

public interface MessService {
    Mess createMess(Mess mess);
    Optional<Mess> getMessById(Long id);
    Optional<Mess> getMessByAdminId(Long adminId);
    List<Mess> getAllMesses();

}
