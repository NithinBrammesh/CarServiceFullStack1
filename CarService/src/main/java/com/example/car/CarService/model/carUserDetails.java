package com.example.car.CarService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="user_details")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // Ignore Hibernate Lazy Initialization & Handler
public class carUserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_id")
    private int userId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Phone_no", unique = true)
    private Long phoneNo;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

//    @OneToMany(mappedBy = "carUserDetails", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference  // This manages the back-reference in SavedAddress
//    private List<SavedAddress> savedAddresses;
    @OneToMany(mappedBy = "carUserDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<SavedAddress> savedAddresses;

    // Constructor with only userId
    public carUserDetails(int userId) {
        this.userId = userId;
    }

    // Constructor with all fields
    public carUserDetails(int userId, String name, Long phoneNo, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
    }
}
