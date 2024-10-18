package com.bca.student.attendance.system.controller

import com.bca.student.attendance.system.model.dao.Attendance
import com.bca.student.attendance.system.service.AttendanceService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

@RestController
@RequestMapping("/attendance/student")
class AttendanceController(private val attendanceService: AttendanceService) {

    @PostMapping("/{studentId}/checkin")
    fun checkIn(@PathVariable studentId: Long): Mono<Attendance> =
        attendanceService.checkIn(studentId)

    @PostMapping("/{studentId}/checkout")
    fun checkOut(@PathVariable studentId: Long): Mono<Attendance> =
        attendanceService.checkOut(studentId)

    @DeleteMapping("/{studentId}/abort")
    fun abortCheckIn(@PathVariable studentId: Long): Mono<Void> =
        attendanceService.abortCheckIn(studentId)

    @GetMapping("/report")
    fun generateReport(@RequestParam date: LocalDate): Flux<Attendance> =
        attendanceService.generateReport(date)
}