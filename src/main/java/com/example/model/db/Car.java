package com.example.model.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parking")
@Entity
public class Car {
    @Id
    private String carNumber;
    private boolean paid;

    @Column(unique = true)
    private Integer parkingSpot;

    @Version
    private Integer version;
}
