package com.vn.devmaster.services.controller;

import com.vn.devmaster.services.dto.ClazzDTO;
import com.vn.devmaster.services.entites.Clazz;
import com.vn.devmaster.services.mapper.ClazzMapper;
import com.vn.devmaster.services.repository.ClazzRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clazz/v1")
@RequiredArgsConstructor
public class ClazzController {
    private final ClazzRepository clazzRepository;
    private final ClazzMapper clazzMapper;
    @GetMapping("")
    public List<ClazzDTO> getAll() {
        List<Clazz> clazzes = clazzRepository.findAll();
        return clazzMapper.toDto(clazzes);
    }
}
