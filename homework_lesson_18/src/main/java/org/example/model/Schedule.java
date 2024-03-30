package org.example.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    private int scheduleId;
    private int staffId;
    private LocalDate workDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public Schedule() {}

    public Schedule(int scheduleId, int staffId, LocalDate workDate, LocalTime startTime, LocalTime endTime) {
        this.scheduleId = scheduleId;
        this.staffId = staffId;
        this.workDate = workDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getScheduleId() { return scheduleId; }
    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }
    public int getStaffId() { return staffId; }
    public void setStaffId(int staffId) { this.staffId = staffId; }
    public LocalDate getWorkDate() { return workDate; }
    public void setWorkDate(LocalDate workDate) { this.workDate = workDate; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
}
