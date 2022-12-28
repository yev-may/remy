package com.yevay.remy.model.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Workspace {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CardBox> cardBoxes;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User owner;
}
