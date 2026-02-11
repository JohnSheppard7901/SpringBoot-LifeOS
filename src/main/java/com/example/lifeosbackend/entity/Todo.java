package com.example.lifeosbackend.entity;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


import javax.annotation.processing.Generated;
import java.time.temporal.Temporal;
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
