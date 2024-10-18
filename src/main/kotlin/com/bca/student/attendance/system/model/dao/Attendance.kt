package com.bca.student.attendance.system.model.dao

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("attendances")
data class Attendance(
    @Id val id: Long? = null,
    val studentId: Long,
    val date: LocalDate,
    val status: Boolean
)