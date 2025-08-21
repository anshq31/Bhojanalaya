package com.example.mess.bhojanalaya.Controller;

import com.example.mess.bhojanalaya.DTO.MessDto.MessRequestDto;
import com.example.mess.bhojanalaya.DTO.MessDto.MessResponseDto;
import com.example.mess.bhojanalaya.Model.Mess;
import com.example.mess.bhojanalaya.Services.Interfaces.MessService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/messes")
@RequiredArgsConstructor
public class MessController {

    @Autowired
    private final MessService messService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Optional<MessResponseDto>> createMess(@RequestBody MessRequestDto messRequestDto){
        return ResponseEntity.ok(messService.createMess(messRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MessResponseDto>> getMess(@PathVariable Long id){
        return ResponseEntity.ok(messService.getMessById(id));
    }

    @GetMapping
    public ResponseEntity<List<Optional<MessResponseDto>>> getAllMess(){
        return ResponseEntity.ok(messService.getAllMesses());
    }

    
}
