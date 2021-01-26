package com.senla.entity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "Scooter")
public class Scooter extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "scooter_id")
    private Integer scooterId;

    @NonNull
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ScooterStatus status;

    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "rent_point_id")
    private RentPoint rentPoint;

    @OneToMany(mappedBy = "scooter", cascade = CascadeType.ALL)
    private List<RentStory> rentStoryList;


}

