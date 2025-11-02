package com.example.dsjee1.service;

import com.example.dsjee1.entity.Teacher;
import com.example.dsjee1.entity.Course;
import com.example.dsjee1.iservice.ITeacherService;
import com.example.dsjee1.repository.TeacherRepository;
import com.example.dsjee1.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public Teacher getTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Teacher getTeacherByTeacherNumber(String teacherNumber) {
        return teacherRepository.findByTeacherNumber(teacherNumber).orElse(null);
    }

    @Override
    public List<Teacher> searchTeachers(String keyword) {
        return teacherRepository.searchTeachers(keyword);
    }

    @Override
    public List<Teacher> getTeachersByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course != null && course.getTeacher() != null) {
            // Retourne le seul enseignant du cours
            return List.of(course.getTeacher());
        }
        return List.of(); // Aucun enseignant trouvé
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateTeacher(Long id, Teacher teacher) {
        Teacher existing = teacherRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setFirstName(teacher.getFirstName());
            existing.setLastName(teacher.getLastName());
            existing.setEmail(teacher.getEmail());
            existing.setTeacherNumber(teacher.getTeacherNumber());
            teacherRepository.save(existing);
        }
        return existing;
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
