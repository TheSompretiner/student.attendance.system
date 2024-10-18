package com.bca.student.attendance.system.repository

import com.bca.student.attendance.system.model.dao.Attendance
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

@Repository
interface AttendanceRepository : ReactiveCrudRepository<Attendance, Long> {
    fun findByStudentIdAndDate(studentId: Long, date: LocalDate): Mono<Attendance>
    fun findByDate(date: LocalDate): Flux<Attendance>
}