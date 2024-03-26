package com.vn.devmaster.services.service;

import com.vn.devmaster.services.dto.StudentDTO;
import com.vn.devmaster.services.dto.SubjectDTO;
import com.vn.devmaster.services.entites.Clazz;
import com.vn.devmaster.services.entites.Student;
import com.vn.devmaster.services.mapper.ClazzMapper;
import com.vn.devmaster.services.mapper.StudentMapper;
import com.vn.devmaster.services.projection.IStudentPoint;
import com.vn.devmaster.services.repository.ClazzRepository;
import com.vn.devmaster.services.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final ClazzMapper clazzMapper;
    private final ClazzRepository clazzRepository;

    public List<Student> findAll() {
        List<Student> students = studentRepository.findAll();
        return students;
    }

    public List<StudentDTO> findByAddress(String address) {
        List<Student> students = studentRepository.findByAddress(address);
        return studentMapper.toDto(students);
    }

    public Student findById(int id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            System.out.println("Không tìm thấy sinh viên");
            return null;
        }
        return optionalStudent.get();
    }

    @Transactional
    public String save(StudentDTO studentDTO) {
        Clazz clazz = clazzMapper.toEntity(studentDTO.getClazz());
        clazz = clazzRepository.save(clazz);

        Student student = studentMapper.toEntity(studentDTO);
        student.setClazz(clazz);
        studentRepository.save(student);
        return "Thêm thành công";
    }

    public String update(int id, StudentDTO studentDTO) {
        boolean existsStudent = studentRepository.existsById(id);
        Clazz clazz = clazzRepository
                .findById(studentDTO.getClazz().getId())
                .orElse(new Clazz());
        if (!existsStudent) return "Không có sinh viên có id = " + id;
        if (clazz.getId() == null) return "Không có lớp có id =" + id;
        String[] strings = studentDTO.getName().split(" ");
        if (strings.length < 2) return "Tên không đúng yêu cầu";
        Student student = new Student();
        student.setId(id);
        student.setLastName(strings[0]);
        student.setFirstName(strings[1]);
        student.setAddress(studentDTO.getAddress());
        student.setClazz(clazz);
        studentRepository.save(student);
        return "cập nhật thành công";
    }

    // StudentDTO -> trong đó có 1 list objectDTO (thêm point vào object dto)
    public List<IStudentPoint> findStudentById(int id) {
        List<IStudentPoint> iStudentPoint = studentRepository.findStudentById(id);
        StudentDTO dto = new StudentDTO();
        dto.setName(iStudentPoint.get(0).getName());
        //....todo: những phần cần set thêm

        List<SubjectDTO> subjectDTOS = new ArrayList<>();
        iStudentPoint.forEach(item -> {
            SubjectDTO subjectDTO = new SubjectDTO();
            subjectDTO.setName(item.getSubjectName());
            subjectDTO.setPoint(item.getPoint());
        });
        dto.setSubjects(subjectDTOS);
        return dto;
    }
}
