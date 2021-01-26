package com.senla.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "discount")
public class Discount  extends AbstractEntity{

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "discount_id")
    private Integer discountId;

    @NonNull
    @Column(name = "discount_rate")
    private Integer discountRate;

/*    @OneToOne(mappedBy = "discount")
    @JsonIgnore
    private Profile profile;*/

    @Override
    public String toString() {
        return "Discount{" +
                "DiscountId=" + getDiscountId() +
                ", DiscountRate=" + discountRate +
                '}';
    }
}