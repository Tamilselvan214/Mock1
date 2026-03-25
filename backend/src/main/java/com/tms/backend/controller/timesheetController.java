package com.tms.backend.controller;

import com.tms.backend.model.Timesheet;
import com.tms.backend.model.TimesheetEntry;
import com.tms.backend.service.TimesheetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timesheet")
@CrossOrigin
public class TimesheetController {

    @Autowired
    private TimesheetService service;

    // Create Timesheet
    @PostMapping("/create/{userId}")
    public Timesheet create(@PathVariable Long userId) {
        return service.createTimesheet(userId);
    }

    // Add Entry (date + hours)
    @PostMapping("/entry")
    public TimesheetEntry addEntry(@RequestBody TimesheetEntry entry) {
        return service.addEntry(entry);
    }

    // Submit Timesheet
    @PostMapping("/submit/{id}")
    public Timesheet submit(@PathVariable Long id) {
        return service.submit(id);
    }

    // Approve Timesheet
    @PostMapping("/approve/{id}/{managerId}")
    public Timesheet approve(
            @PathVariable Long id,
            @PathVariable Long managerId) {

        return service.approve(id, managerId);
    }

    // Get Timesheet (with timestamps)
    @GetMapping("/{id}")
    public Timesheet get(@PathVariable Long id) {
        return service.getTimesheet(id);
    }
}
