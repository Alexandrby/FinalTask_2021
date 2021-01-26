package com.senla.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table (name = "profile")
public class Profile extends AbstractEntity  {

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "profile_id")
  private Integer profileId;


  @NotNull
  @Column(name = "first_name")
  private String firstName;

  @NotNull
  @Column(name = "second_name")
  private String secondName;

  @ManyToOne(optional=false, cascade=CascadeType.ALL)
  @JoinColumn (name = "discount_id")
  private Discount discount;

  @OneToOne(mappedBy = "profile",cascade = CascadeType.ALL)
  @JsonIgnore
  private User user;

  @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL)
  private List<SeasonTicket> SeasonTicketList;

  @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
  private List<RentStory> rentStoryList;

}
