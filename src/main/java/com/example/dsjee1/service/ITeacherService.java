package com.example.dsjee1.iservice;

import com.example.dsjee1.entity.Teacher;
import java.util.List;

public interface ITeacherService {

    List<Teacher> getAllTeachers();

    Teacher getTeacherById(Long id);

    Teacher getTeacherByEmail(String email);

    Teacher getTeacherByTeacherNumber(String teacherNumber);

    List<Teacher> searchTeachers(String keyword);

    List<Teacher> getTeachersByCourse(Long courseId);

    Teacher createTeacher(Teacher teacher);

    Teacher updateTeacher(Long id, Teacher teacher);

    void deleteTeacher(Long id);
}
