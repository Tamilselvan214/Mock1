package com.tms.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "timesheet_entry")
public class TimeSheetEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryId;

    @Column(name = "timesheet_id")
    private Long timesheetId;

    private LocalDate date;
    private double hours;

}

