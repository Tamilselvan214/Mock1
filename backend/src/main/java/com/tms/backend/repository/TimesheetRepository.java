package com.timesheet.repository;

import com.timesheet.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    List<Timesheet> findByUserId(Long userId);

    // one timesheet per week
    Timesheet findByUserIdAndWeekStart(Long userId, LocalDate weekStart);
}
