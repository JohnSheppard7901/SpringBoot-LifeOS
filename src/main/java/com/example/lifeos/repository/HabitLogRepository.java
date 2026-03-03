package com.example.lifeos.repository;
import com.example.lifeos.entity.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HabitLogRepository extends JpaRepository<HabitLog, UUID> {
    List<HabitLog> findAllByHabitId(UUID habitId);
}