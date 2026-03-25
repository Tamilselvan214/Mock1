package com.timesheet.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "timesheet_entry")
public class TimesheetEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryId;

    @Column(name = "timesheet_id")
    private Long timesheetId;

    private LocalDate date;
    private double hours;

    // getters and setters
}
