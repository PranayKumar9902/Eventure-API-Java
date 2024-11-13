package com.booking.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking.app.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer>{
    
    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.event.id = :eventId")
    Integer countByEventId(Integer eventId);

    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.event.id = :eventId AND t.entered = true")
    Integer countByEventIdAndEntered(Integer eventId);

    List<Ticket> findByUserId(Integer userId);

    Ticket findByUserIdAndId(Integer userId, Integer ticketId);
}
