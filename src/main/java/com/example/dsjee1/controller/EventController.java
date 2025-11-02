package com.example.dsjee1.controller;

import com.example.dsjee1.entity.Event;
import com.example.dsjee1.iservice.IEventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

    @Autowired
    private IEventService eventService;

    // 🔹 Récupérer tous les événements
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    // 🔹 Récupérer un événement par ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    // 🔹 Recherche d'événements par mot-clé
    @GetMapping("/search")
    public ResponseEntity<List<Event>> searchEvents(@RequestParam String keyword) {
        return ResponseEntity.ok(eventService.searchEvents(keyword));
    }

    // 🔹 Récupérer les événements par type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Event>> getEventsByType(@PathVariable String type) {
        return ResponseEntity.ok(eventService.getEventsByType(type));
    }

    // 🔹 Récupérer les événements par salle
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Event>> getEventsByRoom(@PathVariable Long roomId) {
        return ResponseEntity.ok(eventService.getEventsByRoom(roomId));
    }

    // 🔹 Récupérer les événements entre deux dates
    @GetMapping("/date-range")
    public ResponseEntity<List<Event>> getEventsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(eventService.getEventsByDateRange(startDate, endDate));
    }

    // 🔹 Récupérer les événements d'un étudiant
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Event>> getEventsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(eventService.getEventsByStudent(studentId));
    }

    // 🔹 Récupérer les événements d'un enseignant
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Event>> getEventsByTeacher(@PathVariable Long teacherId) {
        return ResponseEntity.ok(eventService.getEventsByTeacher(teacherId));
    }

    // 🔹 Créer un nouvel événement
    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    // 🔹 Mettre à jour un événement existant
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @Valid @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        return ResponseEntity.ok(updatedEvent);
    }

    // 🔹 Supprimer un événement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Ajouter un participant (étudiant)
    @PostMapping("/{eventId}/participants/{studentId}")
    public ResponseEntity<Event> addParticipant(@PathVariable Long eventId, @PathVariable Long studentId) {
        Event event = eventService.addParticipant(eventId, studentId);
        return ResponseEntity.ok(event);
    }

    // 🔹 Supprimer un participant (étudiant)
    @DeleteMapping("/{eventId}/participants/{studentId}")
    public ResponseEntity<Event> removeParticipant(@PathVariable Long eventId, @PathVariable Long studentId) {
        Event event = eventService.removeParticipant(eventId, studentId);
        return ResponseEntity.ok(event);
    }

    // 🔹 Ajouter un organisateur (enseignant)
    @PostMapping("/{eventId}/organizers/{teacherId}")
    public ResponseEntity<Event> addOrganizer(@PathVariable Long eventId, @PathVariable Long teacherId) {
        Event event = eventService.addOrganizer(eventId, teacherId);
        return ResponseEntity.ok(event);
    }

    // 🔹 Supprimer un organisateur (enseignant)
    @DeleteMapping("/{eventId}/organizers/{teacherId}")
    public ResponseEntity<Event> removeOrganizer(@PathVariable Long eventId, @PathVariable Long teacherId) {
        Event event = eventService.removeOrganizer(eventId, teacherId);
        return ResponseEntity.ok(event);
    }
}
