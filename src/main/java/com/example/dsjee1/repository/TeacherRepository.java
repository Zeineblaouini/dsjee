package com.example.dsjee1.repository;

import com.example.dsjee1.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByEmail(String email);

    Optional<Teacher> findByTeacherNumber(String teacherNumber);

    @Query("SELECT t FROM Teacher t WHERE " +
            "LOWER(t.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.teacherNumber) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Teacher> searchTeachers(@Param("keyword") String keyword);

    boolean existsByEmail(String email);

    boolean existsByTeacherNumber(String teacherNumber);
}
