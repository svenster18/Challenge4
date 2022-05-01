package com.taufik.challenge4.dao;

import com.taufik.challenge4.model.Film;
import com.taufik.challenge4.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
