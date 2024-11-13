package com.booking.app.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.app.entities.Event;
import com.booking.app.entities.Ticket;
import com.booking.app.entities.User;
import com.booking.app.models.TicketRequest;
import com.booking.app.models.TicketResponse;
import com.booking.app.repositories.TicketRepository;
import com.booking.app.repositories.UserRepository;
import com.google.zxing.WriterException;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private QRCodeService qrCodeService;

    public List<Ticket> getTicketsOfUser(Integer userId) {
        return ticketRepository.findByUserId(userId);
    }

    public TicketResponse getTicketOfUserById(Integer userId, Integer ticketId) throws WriterException, IOException {

        Ticket ticket =  ticketRepository.findByUserIdAndId(userId, ticketId);

        if(ticket == null) {
            throw new RuntimeException("Ticket not found");
        }
        byte[] qrCodeBytes = qrCodeService.generateQRCode(ticketId, userId);

        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setTicket(ticket);
        ticketResponse.setQrCode(qrCodeBytes);

        return ticketResponse;
    }

    public Ticket createTicket(TicketRequest ticketRequest) {

        User user = userRepository.findById(ticketRequest.getUserId()).orElse(null);
        Event event = eventService.getEventById(ticketRequest.getEventId());

        if(user == null || event == null) {
            throw new RuntimeException("User or Event not found");
        }

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setEvent(event);

        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Integer ticketId , Ticket ticket) {

        Ticket ticketToUpdate = ticketRepository.findById(ticketId).orElse(null);

        if(ticketToUpdate == null) {
            throw new RuntimeException("Ticket not found");
        }

        ticket.setId(ticketId);
        return ticketRepository.save(ticket);
    }

    public void validateTicket(Integer ticketId , Integer userId) {

        Ticket ticket = ticketRepository.findByUserIdAndId(userId, ticketId);

        if(ticket == null) {
            throw new RuntimeException("Ticket not found");
        }

        if(ticket.isEntered()) {
            throw new RuntimeException("Ticket already entered");
        }

        ticket.setEntered(true);
        ticketRepository.save(ticket);
    }
}
