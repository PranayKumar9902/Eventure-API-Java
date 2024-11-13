package com.booking.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.app.entities.Event;


@Repository
public interface EventRepository extends JpaRepository<Event,Integer>{
    
    List<Event> findAllByOrderByUpdatedAtDesc();
}
