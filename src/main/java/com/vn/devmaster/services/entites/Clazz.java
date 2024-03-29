package com.vn.devmaster.services.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Table(name = "clazz")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "clazz", fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();
}