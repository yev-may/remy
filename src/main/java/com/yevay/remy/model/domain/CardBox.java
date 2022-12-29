package com.yevay.remy.model.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardBox {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private LocalDate lastRepeatDate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "box_id")
    private List<Card> cards;
    @ManyToOne
    private User owner;
}
