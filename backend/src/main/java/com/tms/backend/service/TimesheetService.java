package com.tms.backend.service;

import com.tms.backend.model.Timesheet;
import com.tms.backend.model.TimesheetEntry;
import com.tms.backend.model.User;
import com.tms.backend.repository.TimesheetRepository;
import com.tms.backend.repository.TimesheetEntryRepository;
import com.tms.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepo;

    @Autowired
    private TimesheetEntryRepository entryRepo;

    @Autowired
    private UserRepository userRepo;

    // ✅ 1. Create Timesheet
    public Timesheet createTimesheet(Long userId) {

        Timesheet t = new Timesheet();
        t.setUserId(userId);
        t.setWeekStart(LocalDate.now());
        t.setStatus("DRAFT");

        return timesheetRepo.save(t);
    }

    // ✅ 2. Add Entry (date + hours)
    public TimesheetEntry addEntry(TimesheetEntry entry) {

        if (entry.getHours() <= 0 || entry.getHours() > 9) {
            throw new RuntimeException("Hours must be between 1 and 9");
        }

        return entryRepo.save(entry);
    }

    // ✅ 3. Validate 9-hour rule
    public void validateBeforeSubmit(Long timesheetId) {

        List<TimesheetEntry> entries =
                entryRepo.findByTimesheetId(timesheetId);

        if (entries.isEmpty()) {
            throw new RuntimeException("No entries found");
        }

        Map<LocalDate, Double> dailyHours = new HashMap<>();

        for (TimesheetEntry e : entries) {
            dailyHours.put(
                e.getDate(),
                dailyHours.getOrDefault(e.getDate(), 0.0) + e.getHours()
            );
        }

        for (Map.Entry<LocalDate, Double> day : dailyHours.entrySet()) {
            if (day.getValue() != 9) {
                throw new RuntimeException(
                    "Each day must be exactly 9 hours: " + day.getKey()
                );
            }
        }
    }

    // ✅ 4. Submit Timesheet
    public Timesheet submit(Long timesheetId) {

        validateBeforeSubmit(timesheetId);

        Timesheet t = timesheetRepo.findById(timesheetId).orElseThrow();
        t.setStatus("SUBMITTED");

        return timesheetRepo.save(t);
    }

    // ✅ 5. Approve Timesheet (Manager check)
    public Timesheet approve(Long timesheetId, Long managerId) {

        Timesheet t = timesheetRepo.findById(timesheetId).orElseThrow();

        User emp = userRepo.findById(t.getUserId()).orElseThrow();

        // Manager validation
        if (emp.getManagerId() == null ||
            !emp.getManagerId().equals(managerId)) {

            throw new RuntimeException("Unauthorized manager");
        }

        t.setStatus("APPROVED");

        return timesheetRepo.save(t);
    }

    // ✅ 6. Get Timesheet
    public Timesheet getTimesheet(Long id) {
        return timesheetRepo.findById(id).orElseThrow();
    }
}
