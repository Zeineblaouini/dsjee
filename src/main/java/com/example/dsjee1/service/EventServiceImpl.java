package com.example.dsjee1.service;

import com.example.dsjee1.entity.Event;
import com.example.dsjee1.iservice.IEventService;
import com.example.dsjee1.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements IEventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }

    @Override
    public List<Event> searchEvents(String keyword) {
        return eventRepository.searchEvents(keyword);
    }

    @Override
    public List<Event> getEventsByType(String type) {
        return eventRepository.findByType(type);
    }

    @Override
    public List<Event> getEventsByRoom(Long roomId) {
        return eventRepository.findByRoomId(roomId);
    }

    @Override
    public List<Event> getEventsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return eventRepository.findEventsByDateRange(startDate, endDate);
    }

    @Override
    public List<Event> getEventsByStudent(Long studentId) {
        return eventRepository.findEventsByStudentId(studentId);
    }

    @Override
    public List<Event> getEventsByTeacher(Long teacherId) {
        return eventRepository.findEventsByTeacherId(teacherId);
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        Event existing = getEventById(id);
        existing.setName(event.getName());
        existing.setType(event.getType());
        existing.setStartDate(event.getStartDate());
        existing.setEndDate(event.getEndDate());
        existing.setRoom(event.getRoom());
        return eventRepository.save(existing);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event addParticipant(Long eventId, Long studentId) {
        Event event = getEventById(eventId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        event.getStudents().add(student);
        return eventRepository.save(event);
    }

    @Override
    public Event removeParticipant(Long eventId, Long studentId) {
        Event event = getEventById(eventId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        event.getStudents().remove(student);
        return eventRepository.save(event);
    }

    @Override
    public Event addOrganizer(Long eventId, Long teacherId) {
        Event event = getEventById(eventId);
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        event.getOrganizers().add(teacher);
        return eventRepository.save(event);
    }

    @Override
    public Event removeOrganizer(Long eventId, Long teacherId) {
        Event event = getEventById(eventId);
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        event.getOrganizers().remove(teacher);
        return eventRepository.save(event);
    }
}
