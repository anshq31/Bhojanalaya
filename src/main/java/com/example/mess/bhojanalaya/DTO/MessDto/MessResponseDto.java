package com.example.mess.bhojanalaya.DTO.MessDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessResponseDto {
    private Long id;
    private String name ;
    private String location;
    private Long adminId;
    private String adminName;
    private int studentCount;
}
