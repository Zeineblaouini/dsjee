package com.example.dsjee1.repository;

import com.example.dsjee1.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Rechercher par type
    List<Event> findByType(String type);

    // Rechercher par salle
    List<Event> findByRoomId(Long roomId);

    // Recherche par mot-clé dans name, description ou type
    @Query("SELECT e FROM Event e WHERE " +
            "LOWER(e.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.type) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Event> searchEvents(@Param("keyword") String keyword);

    // Rechercher par plage de dates
    @Query("SELECT e FROM Event e WHERE e.startDate >= :startDate AND e.endDate <= :endDate")
    List<Event> findEventsByDateRange(@Param("startDate") LocalDateTime startDate,
                                      @Param("endDate") LocalDateTime endDate);

    // Rechercher par étudiant
    @Query("SELECT e FROM Event e JOIN e.students s WHERE s.id = :studentId")
    List<Event> findEventsByStudentId(@Param("studentId") Long studentId);

    // Rechercher par enseignant
    @Query("SELECT e FROM Event e JOIN e.organizers o WHERE o.id = :teacherId")
    List<Event> findEventsByTeacherId(@Param("teacherId") Long teacherId);
}
