package com.booking.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.app.entities.Event;
import com.booking.app.repositories.EventRepository;
import com.booking.app.repositories.TicketRepository;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.findAll();

        for(Event event : events) {
            event = getEventWithTicketCounts(event.getId());
        }

        return events;
    }

    public Event getEventById(Integer id) {
        Event event = eventRepository.findById(id).orElse(null);

        if (event == null) {
            throw new RuntimeException("Event not found");
        }

        event = getEventWithTicketCounts(event.getId());
        return event;
    }

    public Event createEvent(Event event) {
        System.out.println(event);
        return eventRepository.save(event);
    }

    public Event updateEvent(Integer eventId , Event event) {

        Event existingEvent = eventRepository.findById(eventId).orElse(null);

        if (existingEvent == null) {
            return null;
        }

        event.setId(eventId);
        return eventRepository.save(event);
    }

    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);
    }

    public Event getEventWithTicketCounts(Integer eventId) {
        
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setTotalTicketsPurchased(ticketRepository.countByEventId(eventId));
        event.setTotalTicketsEntered(ticketRepository.countByEventIdAndEntered(eventId));

        return event;
    }
}
