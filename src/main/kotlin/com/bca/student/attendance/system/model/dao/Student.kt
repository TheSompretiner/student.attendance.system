package com.bca.student.attendance.system.model.dao

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("students")
data class Student(
    @Id val id: Long? = null,
    val name: String,
    val attendance: Boolean
)