package com.taufik.challenge4.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(NomorKursi.class)
@Table(name = "seats")
public class Seat implements Serializable {
    private char studioname;
    @Id
    private char seatrow;
    @Id
    private int seatcolumn;
}
