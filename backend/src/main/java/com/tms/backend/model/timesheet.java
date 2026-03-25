package com.timesheet.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "timesheet")
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timesheetId;

    @Column(name = "user_id")
    private Long userId;

    private LocalDate weekStart;

    private String status;

    // getters and setters
}
