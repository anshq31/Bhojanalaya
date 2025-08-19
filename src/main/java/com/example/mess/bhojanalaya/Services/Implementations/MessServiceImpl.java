package com.example.mess.bhojanalaya.Services.Implementations;

import com.example.mess.bhojanalaya.Model.Mess;
import com.example.mess.bhojanalaya.Repository.MessRepository;
import com.example.mess.bhojanalaya.Services.Interfaces.MessService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MessServiceImpl implements MessService {
    @Autowired
    private MessRepository messRepository;

    @Override
    public Mess createMess(Mess mess) {
        return messRepository.save(mess);
    }

    @Override
    public Optional<Mess> getMessById(Long id) {
        return messRepository.findById(id);
    }

    @Override
    public Optional<Mess> getMessByAdminId(Long adminId) {
        return messRepository.findByAdminId(adminId);
    }

    @Override
    public List<Mess> getAllMesses() {
        return messRepository.findAll();
    }

    @Override
    public void deleteMess(Long id) {
        messRepository.deleteById(id);
    }
}
