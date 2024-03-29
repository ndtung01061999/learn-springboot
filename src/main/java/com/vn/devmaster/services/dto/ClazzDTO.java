package com.vn.devmaster.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClazzDTO {
    private int id;
    private String name;
    private List<StudentDTO> students;
}
