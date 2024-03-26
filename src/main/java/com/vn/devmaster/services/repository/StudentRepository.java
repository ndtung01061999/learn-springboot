package com.vn.devmaster.services.repository;

import com.vn.devmaster.services.entites.Student;
import com.vn.devmaster.services.projection.IStudentPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  StudentRepository extends JpaRepository<Student, Integer> {

    //HQL
//    @Query(value = "select s from Student s where s.address like concat('%',:address,'%')")
//    List<Student> findByAddress(@Param("address") String address);

    //nativate query
    @Query(value = "select * from student where address like concat('%', :address, '%')", nativeQuery = true)
    List<Student> findByAddress(@Param("address") String address);

    List<Student> findAllByAddress(String address);

    @Query(value = " select concat(s.last_name, ' ', s.first_name) name,\n" +
            "       s.address                              address,\n" +
            "       c.name                                 clazzName,\n" +
            "       s2.name                                subjectName,\n" +
            "       ss.point                               point\n" +
            " from student s\n" +
            "         left join clazz c on s.clazz_id = c.id\n" +
            "         left join student_subject ss on s.id = ss.id_student\n" +
            "         left join subject s2 on s2.id = ss.id_subject\n" +
            " where s.id = :id ", nativeQuery = true)
    List<IStudentPoint> findStudentById(@Param("id") int id);
}
