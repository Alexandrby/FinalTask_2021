package com.senla.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "RentStory")
public class RentStory extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "rentStory_id")
    private Integer rentStoryId;

    @NotNull
    @Column(name = "rent_duration")
    private Integer rentDuration;

    @NotNull
    private Integer money;

    @NotNull
    @Column(name = "paid_way")
    private String paidWay;

    @ManyToOne( cascade = CascadeType.PERSIST)
    @JoinColumn(name = "profile_id")
    @JsonIgnore
    private Profile profile;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "scooter_id")
    private Scooter scooter;

    @Override
    public String toString() {
        return "<br />" + "RentStory{" +
                "rentStoryId=" + rentStoryId +
                ", scooter=" + scooter +
                ", rentDuration=" + rentDuration +
                ", Money=" + money +
                ", paidWay='" + paidWay + '\'' +
                '}';
    }
}
