package com.taufik.challenge4.controller;

import com.taufik.challenge4.model.InvoiceInput;
import com.taufik.challenge4.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InvoiceController {
    InvoiceServiceImpl invoiceServiceImpl;
    @Autowired
    public InvoiceController(InvoiceServiceImpl invoiceServiceImpl) {
        this.invoiceServiceImpl = invoiceServiceImpl;
    }
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping(value="/invoice/{filmcode}")
    public ResponseEntity<byte[]> generateInvoice(@PathVariable("filmcode") int filmcode) {
        return invoiceServiceImpl.generateInvoice(filmcode);
    }
}
