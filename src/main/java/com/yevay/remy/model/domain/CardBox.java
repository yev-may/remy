package com.yevay.remy.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class CardBox {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private LocalDate lastRepeatDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Card> cards;
}
