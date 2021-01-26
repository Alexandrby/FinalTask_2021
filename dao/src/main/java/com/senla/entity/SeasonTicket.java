package com.senla.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "SeasonTicket")
public class SeasonTicket extends AbstractEntity{

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "season_ticket_id")
    private Integer seasonTicketId;
    @Column(name = "hours_left")
    private Integer hoursLeft;
    @Column(name = "price_per_hour")
    private Integer costPerHour;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    @JsonIgnore
    private Profile profile;


}
