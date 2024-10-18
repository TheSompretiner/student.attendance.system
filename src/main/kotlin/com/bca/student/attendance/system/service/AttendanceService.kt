package com.bca.student.attendance.system.service

import com.bca.student.attendance.system.model.dao.Attendance
import com.bca.student.attendance.system.repository.AttendanceRepository
import com.bca.student.attendance.system.repository.StudentRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.time.LocalDate

@Service
class AttendanceService(
    private val studentRepository: StudentRepository,
    private val attendanceRepository: AttendanceRepository
) {
    // ... metode lainnya

    fun checkIn(studentId: Long): Mono<Attendance> {
        val date = LocalDate.now()
        return attendanceRepository.findByStudentIdAndDate(studentId, date)
            .flatMap<Attendance?> {
                // Jika sudah check-in hari ini, abort
                Mono.error(Exception("Student already checked in today"))
            }
            .onErrorResume {
                Mono.error(Exception(it))
            }
            .switchIfEmpty {
                val attendance = Attendance(studentId = studentId, date = date, status = true)
                attendanceRepository.save(attendance)
            }
    }

    fun checkOut(studentId: Long): Mono<Attendance> {
        val date = LocalDate.now()
        return attendanceRepository.findByStudentIdAndDate(studentId, date)
            .flatMap {
                // Jika sudah check-in, ubah status menjadi false
                attendanceRepository.save(it.copy(status = false))
            }
            .switchIfEmpty {
                Mono.error(Exception("Student has not checked in today"))
            }
    }

    fun abortCheckIn(studentId: Long): Mono<Void> {
        val date = LocalDate.now()
        return attendanceRepository.findByStudentIdAndDate(studentId, date)
            .flatMap {
                attendanceRepository.deleteById(it.id!!)
            }
            .switchIfEmpty(Mono.empty())
    }

    fun generateReport(date: LocalDate): Flux<Attendance> {
        return attendanceRepository.findByDate(date)
    }
}