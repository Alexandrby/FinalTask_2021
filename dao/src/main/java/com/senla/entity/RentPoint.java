package com.senla.entity;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "rentpoint")
public class RentPoint  extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "rent_point_id")
    private Integer rentPointId;

    @NotNull
    private String address;

    @NotNull
    private String phone;

    @OneToMany(mappedBy = "rentPoint", cascade = CascadeType.ALL)
    private List<Scooter> scooterList;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    @Override
    public String toString() {
        return "RentPoint{" +
               ", Address='" + address + '\'' +
                ", Phone='" + phone + '\'' +
                '}';
    }
}
