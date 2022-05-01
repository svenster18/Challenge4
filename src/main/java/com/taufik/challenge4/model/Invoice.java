package com.taufik.challenge4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    private String username;
    private String email;
    private String filmname;
    private Date tanggaltayang;
    private Time jammulai;
    private Time jamselesai;
}
