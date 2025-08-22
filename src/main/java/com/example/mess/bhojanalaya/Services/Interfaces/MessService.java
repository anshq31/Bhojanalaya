package com.example.mess.bhojanalaya.Services.Interfaces;

import com.example.mess.bhojanalaya.DTO.MessDto.MessRequestDto;
import com.example.mess.bhojanalaya.DTO.MessDto.MessResponseDto;

import java.util.List;
import java.util.Optional;

public interface MessService {
    Optional<MessResponseDto> createMess(MessRequestDto messRequestDto);
    Optional<MessResponseDto> getMessById(Long id);
    Optional<MessResponseDto> getMessByAdminId(Long adminId);
    List<Optional<MessResponseDto>> getAllMesses();
    void deleteMess(Long id);
    Optional<MessResponseDto> updateMess(Long id, MessRequestDto messRequestDto);

}
