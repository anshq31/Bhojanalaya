package com.example.mess.bhojanalaya.Services.Implementations;

import com.example.mess.bhojanalaya.DTO.MessDto.MessRequestDto;
import com.example.mess.bhojanalaya.DTO.MessDto.MessResponseDto;
import com.example.mess.bhojanalaya.Model.Mess;
import com.example.mess.bhojanalaya.Model.User;
import com.example.mess.bhojanalaya.Repository.MessRepository;
import com.example.mess.bhojanalaya.Repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;



    @Override
    public Optional<MessResponseDto> createMess(MessRequestDto messRequestDto) {
        User admin = userRepository.findById(messRequestDto.getAdminId())
                .orElseThrow(()-> new RuntimeException("Admin not Found"));

        Mess mess = Mess.builder()
                .name(messRequestDto.getName())
                .location(messRequestDto.getLocation())
                .admin(admin)
                .build();

        Mess savedMess = messRepository.save(mess);

        return toResponse(savedMess);
    }

    @Override
    public Optional<MessResponseDto> getMessById(Long id) {
        Mess mess = messRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Mess not found"));
        return toResponse(mess);
    }

    @Override
    public Optional<MessResponseDto> getMessByAdminId(Long adminId) {
        return Optional.empty();
    }


    @Override
    public List<Optional<MessResponseDto>> getAllMesses() {
        return messRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public void deleteMess(Long id) {
        messRepository.deleteById(id);
    }

    @Override
    public MessResponseDto updateMess(Long id, MessResponseDto messResponseDto) {
        return null;
    }

    private Optional<MessResponseDto> toResponse(Mess mess){
        return Optional.ofNullable(MessResponseDto.builder()
                .id(mess.getId())
                .name(mess.getName())
                .location(mess.getLocation())
                .adminId(mess.getAdmin().getId())
                .adminName(mess.getAdmin().getName())
                .studentCount(mess.getStudents() != null ? mess.getStudents().size() : 0)
                .build());
    }
}
