package com.etiya.rentACarSpring.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private int id;

    @Column(name = "rent_date")
    private LocalDateTime rentDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "initial_kilometer")
    private int initialKilometer;

    @Column (name = "return_kilometer")
    private int returnKilometer;

    @Column(name="taken_from_city_id")
    private int takenFromCityId;

    @Column(name="return_to_city_id")
    private int returnToCityId;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;


    @ManyToOne
    @JoinColumn(name="user_id")
    private ApplicationUser applicationUser;

    @OneToOne(mappedBy = "rental")
    private Invoice invoice;

    @OneToMany(mappedBy = "rental")
    private List<RentalAdditional> rentalAdditionals;

}
