package com.example.lifeos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "habit_logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;  // → jeder Log bekommt seine eigene ID

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime completedAt;  // → wann wurde der Habit abgehakt

    @ManyToOne                           // → viele Logs gehören zu einem Habit
    @JoinColumn(name = "habit_id")       // → erstellt die "habit_id" Spalte in der Tabelle
    Habit habit;                         // → das Java-Objekt das den Habit repräsentiert
}