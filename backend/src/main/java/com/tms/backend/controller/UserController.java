package com.timesheet.controller;

import com.timesheet.model.*;
import com.timesheet.service.TimesheetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timesheet")
@CrossOrigin
public class TimesheetController {

    @Autowired
    private TimesheetService service;

    @PostMapping("/create/{userId}")
    public Timesheet create(@PathVariable Long userId) {
        return service.createTimesheet(userId);
    }

    @PostMapping("/entry")
    public TimesheetEntry addEntry(@RequestBody TimesheetEntry entry) {
        return service.addEntry(entry);
    }

    @PostMapping("/submit/{id}")
    public Timesheet submit(@PathVariable Long id) {
        return service.submit(id);
    }

    @PostMapping("/approve/{id}/{managerId}")
    public Timesheet approve(
        @PathVariable Long id,
        @PathVariable Long managerId) {

        return service.approve(id, managerId);
    }
}
