package com.example.car.CarService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Data
@Table(name = "saved_address")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // Ignore Hibernate Lazy Initialization & Handler
public class SavedAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="S_Id")
    private int Sid;

    @Column(name="Address_nick_name")
    private String AddressNickName;

    @Column(name="Latitude")
    private String Latitude;

    @Column(name="Longitude")
    private String Longitude;

    @Column(name="Pincode")
    private int Pincode;

    @Column(name="City")
    private String City;

    @Column(name="State")
    private String State;

    @Column(name="Nation")
    private String Nation;

    @ManyToOne
    @JoinColumn(name = "User_id", referencedColumnName = "User_id", nullable = false)
    @JsonBackReference  // This prevents recursive serialization from carUserDetails to SavedAddress
    private carUserDetails carUserDetails;

}
