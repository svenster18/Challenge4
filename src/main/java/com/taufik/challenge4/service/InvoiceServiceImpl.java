package com.taufik.challenge4.service;

import com.taufik.challenge4.controller.FilmNotFoundException;
import com.taufik.challenge4.controller.UserNotFoundException;
import com.taufik.challenge4.model.Film;
import com.taufik.challenge4.model.Invoice;
import com.taufik.challenge4.model.Schedule;
import com.taufik.challenge4.model.User;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.sql.Connection;
import java.util.*;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    FilmServiceImpl filmServiceImpl;
    UserServiceImpl userServiceImpl;

    @Override
    public ResponseEntity<byte[]> generateInvoice(int filmcode) {
        try {
            String username = "mohamadrizki";
            User user = userServiceImpl.findByUsername("mohamadrizki")
                    .orElseThrow(()->new UserNotFoundException("User with username :"+username+" is Not Found!"));
            Film film = filmServiceImpl.findById(filmcode)
                    .orElseThrow(()->new FilmNotFoundException("Film with "+filmcode+" is Not Found!"));
            Schedule schedule = filmServiceImpl.findScheduleByFilmcode(filmcode).get(0);
            Invoice invoice =  new Invoice(username, user.getEmail(), film.getFilmname(), schedule.getTanggaltayang(), schedule.getJammulai(), schedule.getJamselesai());

            List<Invoice> listInvoice = new ArrayList<>();
            listInvoice.add(invoice);

            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(listInvoice);

            JasperPrint empReport =
                    JasperFillManager.fillReport
                            (
                                    JasperCompileManager.compileReport(
                                            ResourceUtils.getFile("classpath:invoice.jrxml")
                                                    .getAbsolutePath()) // path of the jasper report
                                    , null
                                    , jrBeanCollectionDataSource
                            );

            HttpHeaders headers = new HttpHeaders();
            //set the PDF format
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "invoice.pdf");
            //create the report in PDF format
            return new ResponseEntity<byte[]>
                    (JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
