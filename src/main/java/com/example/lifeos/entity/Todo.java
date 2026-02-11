package com.example.lifeos.entity;
import jakarta.persistence.*;
import jakarta.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    @Temporal(TemporalType.DATE)
    private Date deadline;
    private boolean done;


}