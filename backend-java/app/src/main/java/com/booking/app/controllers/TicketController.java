package com.booking.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.entities.Ticket;
import com.booking.app.models.Response;
import com.booking.app.models.TicketRequest;
import com.booking.app.models.ValidateTicket;
import com.booking.app.services.TicketService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/tickets")
public class TicketController {
    
    @Autowired
    private TicketService ticketService;
    
    @GetMapping("/{userId}")
    public Response getTickets(@PathVariable Integer userId) {
        
        Response response = new Response();

        try {
            response.setData(ticketService.getTicketsOfUser(userId));
            response.setMessage("Tickets fetched successfully");
        } catch (Exception e) {
            response.setMessage("Failed to fetch tickets");
        }

        return response;
    }

    @GetMapping("/{userId}/{ticketId}")
    public Response getTicket(@PathVariable Integer userId, @PathVariable Integer ticketId) {
        
        Response response = new Response();

        try {
            response.setData(ticketService.getTicketOfUserById(userId, ticketId));
            response.setMessage("Ticket fetched successfully");
        } catch (Exception e) {
            response.setMessage("Failed to fetch ticket");
        }

        return response;
    }

    @PostMapping("")
    public Response createTicket(@RequestBody TicketRequest ticketRequest) {
        
        Response response = new Response();

        try {
            response.setData(ticketService.createTicket(ticketRequest));
            response.setMessage("Ticket created successfully");
        } catch (Exception e) {
            response.setMessage("Failed to create ticket");
        }

        return response;
    }

    @PutMapping("/{ticketId}")
    public Response updateTicket(@PathVariable Integer ticketId, @RequestBody Ticket ticketRequest) {
        
        Response response = new Response();

        try {
            response.setData(ticketService.updateTicket(ticketId, ticketRequest));
            response.setMessage("Ticket updated successfully");
        } catch (Exception e) {
            response.setMessage("Failed to update ticket");
        }

        return response;
    }

    @PostMapping("/validate")
    public Response validateTicket(@RequestBody ValidateTicket validTicket) {
        
        Response response = new Response();

        try {
            ticketService.validateTicket(validTicket.getTicketId(), validTicket.getOwnerId());
            response.setMessage("Ticket is valid");
            
        } catch (Exception e) {
            response.setMessage("Ticket is invalid");
        }

        return response;
    }


}
