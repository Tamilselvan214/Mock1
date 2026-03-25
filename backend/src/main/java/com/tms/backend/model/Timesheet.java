package com.tms.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "timesheet")
@Data
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timesheetId;

    @Column(name = "user_id")
    private Long userId;

    private LocalDate weekStart;

    private String status;
}
