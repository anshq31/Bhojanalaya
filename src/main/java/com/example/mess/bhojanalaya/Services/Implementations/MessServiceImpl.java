package com.example.mess.bhojanalaya.Services.Implementations;

import com.example.mess.bhojanalaya.DTO.MessDto.MessRequestDto;
import com.example.mess.bhojanalaya.DTO.MessDto.MessResponseDto;
import com.example.mess.bhojanalaya.Enums.Role;
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
        Mess mess = messRepository.findByAdminId(adminId)
                .orElseThrow(()-> new RuntimeException("No mess under this Admin"));

        return toResponse(mess);
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
    public Optional<MessResponseDto> updateMess(Long messId, MessRequestDto messRequestDto) {
        Mess mess = messRepository.findById(messId)
                .orElseThrow(()-> new RuntimeException("Mess not found with id :"+ messId));

        mess.setName(messRequestDto.getName());
        mess.setLocation(messRequestDto.getLocation());

        if (messRequestDto.getAdminId() != null){
            User admin = userRepository.findById(messRequestDto.getAdminId())
                    .orElseThrow(()-> new RuntimeException("Admin was not found with id"+messRequestDto.getAdminId()));

            if (admin.getRole() != Role.ADMIN) {
                throw new RuntimeException("Only admins can be assigned to a mess");
            }

            mess.setAdmin(admin);
        }

        Mess updatedMess = messRepository.save(mess);

        return toResponse(updatedMess);
    }

    private Optional<MessResponseDto> toResponse(Mess mess){
        return Optional.ofNullable(MessResponseDto.builder()
                .id(mess.getId())
                .name(mess.getName())
                .location(mess.getLocation())
                .adminId(mess.getAdmin().getId())
                .adminName(mess.getAdmin().getUsername())
                .studentCount(mess.getStudents() != null ? mess.getStudents().size() : 0)
                .build());
    }
}
