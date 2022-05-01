package com.taufik.challenge4.controller;

import com.taufik.challenge4.model.InvoiceInput;
import com.taufik.challenge4.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InvoiceController {
    InvoiceServiceImpl invoiceServiceImpl;
    @Autowired
    public InvoiceController(InvoiceServiceImpl invoiceServiceImpl) {
        this.invoiceServiceImpl = invoiceServiceImpl;
    }
    @PostMapping(value="/invoice")
    public ResponseEntity<byte[]> generateInvoice(@RequestBody InvoiceInput invoiceInput) {
        return invoiceServiceImpl.generateInvoice(invoiceInput.getUsername(), invoiceInput.getFilmcode());
    }
}
