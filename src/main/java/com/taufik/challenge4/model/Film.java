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
@Table(name = "film")
public class Film implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int filmcode;
    private String filmname;
    private String statustayang;
    @OneToMany
    @JoinColumn(name = "filmcode")
    private List<Schedule> scheduleList;
}
