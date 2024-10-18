package com.bca.student.attendance.system.repository

import com.bca.student.attendance.system.model.dao.Student
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : ReactiveCrudRepository<Student, Long>