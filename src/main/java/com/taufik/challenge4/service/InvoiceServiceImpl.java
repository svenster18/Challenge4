package com.taufik.challenge4.service;

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
import org.springframework.util.ResourceUtils;

import java.sql.Connection;
import java.util.*;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    FilmServiceImpl filmServiceImpl;
    UserServiceImpl userServiceImpl;

    @Override
    public ResponseEntity<byte[]> generateInvoice(String username, int filmcode) {
        try {
            Optional<User> user = userServiceImpl.findByUsername(username);
            Optional<Film> film = filmServiceImpl.findById(filmcode);
            Schedule schedule = filmServiceImpl.findScheduleByFilmcode(filmcode).get(0);
            Invoice invoice =  new Invoice(username, user.get().getEmailaddress(), film.get().getFilmname(), schedule.getTanggaltayang(), schedule.getJammulai(), schedule.getJamselesai());

            List<Invoice> listInvoice = new ArrayList<>();
            listInvoice.add(invoice);

            //dynamic parameters required for report
            Map<String, Object> empParams = new HashMap<>();
            empParams.put("invoiceData", new JRBeanCollectionDataSource(listInvoice));

            JasperPrint empReport =
                    JasperFillManager.fillReport
                            (
                                    JasperCompileManager.compileReport(
                                            ResourceUtils.getFile("classpath:invoice.jrxml")
                                                    .getAbsolutePath()) // path of the jasper report
                                    , empParams
                                    , new JREmptyDataSource()
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
