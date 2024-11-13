package com.booking.app.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.booking.app.entities.Ticket;

import lombok.Data;

@Data
@Component
@Scope("prototype")
public class TicketResponse {
    
    private Ticket ticket;
    private byte[] qrCode;
}
