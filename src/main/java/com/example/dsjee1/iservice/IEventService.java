package com.example.dsjee1.iservice;

import com.example.dsjee1.entity.Event;
import java.time.LocalDateTime;
import java.util.List;

public interface IEventService {

    List<Event> getAllEvents();
    Event getEventById(Long id);
    List<Event> searchEvents(String keyword);
    List<Event> getEventsByType(String type);
    List<Event> getEventsByRoom(Long roomId);
    List<Event> getEventsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<Event> getEventsByStudent(Long studentId);
    List<Event> getEventsByTeacher(Long teacherId);
    Event createEvent(Event event);
    Event updateEvent(Long id, Event event);
    void deleteEvent(Long id);
    Event addParticipant(Long eventId, Long studentId);
    Event removeParticipant(Long eventId, Long studentId);
    Event addOrganizer(Long eventId, Long teacherId);
    Event removeOrganizer(Long eventId, Long teacherId);
}
