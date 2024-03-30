package org.example.scheduleDAO;

import org.example.model.Schedule;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScheduleDaoImpl implements ScheduleDao {
    private final Connection connection;

    public ScheduleDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Schedule> findAll() {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM Schedule";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Schedule schedule = new Schedule(
                        rs.getInt("ScheduleID"),
                        rs.getInt("StaffID"),
                        rs.getDate("WorkDate").toLocalDate(),
                        rs.getTime("StartTime").toLocalTime(),
                        rs.getTime("EndTime").toLocalTime());
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    @Override
    public Optional<Schedule> findById(int id) {
        String sql = "SELECT * FROM Schedule WHERE ScheduleID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Schedule schedule = new Schedule(
                        rs.getInt("ScheduleID"),
                        rs.getInt("StaffID"),
                        rs.getDate("WorkDate").toLocalDate(),
                        rs.getTime("StartTime").toLocalTime(),
                        rs.getTime("EndTime").toLocalTime());
                return Optional.of(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Schedule schedule) {
        String sql = "INSERT INTO Schedule (StaffID, WorkDate, StartTime, EndTime) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, schedule.getStaffId());
            pstmt.setDate(2, Date.valueOf(schedule.getWorkDate()));
            pstmt.setTime(3, Time.valueOf(schedule.getStartTime()));
            pstmt.setTime(4, Time.valueOf(schedule.getEndTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Schedule schedule) {
        String sql = "UPDATE Schedule SET StaffID = ?, WorkDate = ?, StartTime = ?, EndTime = ? WHERE ScheduleID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, schedule.getStaffId());
            pstmt.setDate(2, Date.valueOf(schedule.getWorkDate()));
            pstmt.setTime(3, Time.valueOf(schedule.getStartTime()));
            pstmt.setTime(4, Time.valueOf(schedule.getEndTime()));
            pstmt.setInt(5, schedule.getScheduleId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Schedule WHERE ScheduleID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveNextMondaySchedule(Schedule schedule) {
        String sql = "INSERT INTO Schedule (StaffID, WorkDate, StartTime, EndTime) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, schedule.getStaffId());
            pstmt.setDate(2, Date.valueOf(schedule.getWorkDate()));
            pstmt.setTime(3, Time.valueOf(schedule.getStartTime()));
            pstmt.setTime(4, Time.valueOf(schedule.getEndTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateScheduleForNextTuesday(String startTime, String endTime) {
        String sql = "UPDATE Schedule SET StartTime = ?, EndTime = ? WHERE WorkDate = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            LocalDate nextTuesday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
            pstmt.setString(1, startTime);
            pstmt.setString(2, endTime);
            pstmt.setDate(3, Date.valueOf(nextTuesday));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByDateRange(LocalDate startDate, LocalDate endDate) {
        String sql = "DELETE FROM Schedule WHERE WorkDate BETWEEN ? AND ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(startDate));
            pstmt.setDate(2, Date.valueOf(endDate));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

