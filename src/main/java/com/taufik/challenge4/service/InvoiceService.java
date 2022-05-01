package com.taufik.challenge4.service;

import org.springframework.http.ResponseEntity;

public interface InvoiceService {
    ResponseEntity<byte[]> generateInvoice(String username, int filmcode);
}
