package com.taufik.challenge4.model;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class NomorKursi implements Serializable {

    @Getter
    @Setter
    private char seatrow;

    @Getter @Setter
    private int seatcolumn;

}
