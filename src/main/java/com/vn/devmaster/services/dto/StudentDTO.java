package com.vn.devmaster.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentDTO {
    private Integer id;
    private String name;
    private String address;
    private ClazzDTO clazz;
    private List<SubjectDTO> subjects = new ArrayList<>();
}
