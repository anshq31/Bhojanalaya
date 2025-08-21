package com.example.mess.bhojanalaya.DTO.MessDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessRequestDto {
    private String name;
    private String location;
    private long adminId;
}
