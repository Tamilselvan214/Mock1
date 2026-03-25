package com.timesheet.repository;

import com.timesheet.model.TimesheetEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimesheetEntryRepository 
        extends JpaRepository<TimesheetEntry, Long> {

    List<TimesheetEntry> findByTimesheetId(Long timesheetId);

    List<TimesheetEntry> findByTimesheetIdAndDate(Long timesheetId, LocalDate date);
}
