package org.example.scheduleDAO;

import org.example.model.Schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleDao {
    List<Schedule> findAll();
    Optional<Schedule> findById(int id);
    void save(Schedule schedule);
    void update(Schedule schedule);
    void delete(int id);

    void saveNextMondaySchedule(Schedule schedule);

    void updateScheduleForNextTuesday(String startTime, String endTime);

    void deleteByDateRange(LocalDate startDate, LocalDate endDate);
}

