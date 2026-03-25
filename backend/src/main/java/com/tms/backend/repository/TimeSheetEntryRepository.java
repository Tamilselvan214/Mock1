package com.tms.backend.repository;

import com.tms.backend.model.TimeSheetEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimeSheetEntryRepository
        extends JpaRepository<TimeSheetEntry, Long> {

    List<TimeSheetEntry> findByTimesheetId(Long timesheetId);

    List<TimeSheetEntry> findByTimesheetIdAndDate(Long timesheetId, LocalDate date);
}
