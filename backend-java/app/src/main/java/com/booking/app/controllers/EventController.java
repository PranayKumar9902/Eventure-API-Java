package com.booking.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.entities.Event;
import com.booking.app.models.Response;
import com.booking.app.services.EventService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/events")
public class EventController {
    
    @Autowired
    private EventService eventService;

    @GetMapping("")
    public Response getAllEvents() {
        
        Response response = new Response();
        try {
            response.setData(eventService.getAllEvents());
            response.setMessage("Events retrieved successfully");
        } catch (Exception e) {
            response.setMessage("Failed to retrieve events");
        }

        return response;
    }

    @GetMapping("/{id}")
    public Response getEventById(@PathVariable Integer id) {
        
        Response response = new Response();
        try {
            response.setData(eventService.getEventById(id));
            response.setMessage("Event retrieved successfully");
        } catch (Exception e) {
            response.setMessage("Failed to retrieve event");
        }

        return response;
    }

    @PostMapping("")
    public Response createEvent(@RequestBody Event event) {
        
        Response response = new Response();
        try {
            response.setData(eventService.createEvent(event));
            response.setMessage("Event created successfully");
        } catch (Exception e) {
            response.setMessage("Failed to create event");
        }

        return response;
    }

    @PutMapping("/{id}")
    public Response updateEvent(@PathVariable Integer id, @RequestBody Event event) {
        
        Response response = new Response();
        try {
            response.setData(eventService.updateEvent(id, event));
            response.setMessage("Event updated successfully");
        } catch (Exception e) {
            response.setMessage("Failed to update event");
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public Response deleteEvent(@PathVariable Integer id) {
        
        Response response = new Response();
        try {
            eventService.deleteEvent(id);
            response.setMessage("Event deleted successfully");
        } catch (Exception e) {
            response.setMessage("Failed to delete event");
        }

        return response;
    }
}
