package com.yevay.remy.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue
    private Long id;
    private String question;
    private String answer;
    private Integer repeatLevel;
    private LocalDate lastRepeatDate;
}
