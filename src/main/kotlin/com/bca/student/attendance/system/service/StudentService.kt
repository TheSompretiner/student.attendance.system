package com.bca.student.attendance.system.service

import com.bca.student.attendance.system.model.dao.Student
import com.bca.student.attendance.system.repository.StudentRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class StudentService(private val studentRepository: StudentRepository) {
    fun findAll(): Flux<Student> = studentRepository.findAll()

    fun findById(id: Long): Mono<Student> = studentRepository.findById(id)

    fun save(student: Student): Mono<Student> = studentRepository.save(student)

    fun update(id: Long, student: Student): Mono<Student> =
        studentRepository.findById(id)
            .flatMap { existingStudent ->
                val updatedStudent = existingStudent.copy(name = student.name, attendance = student.attendance)
                studentRepository.save(updatedStudent)
            }

    fun delete(id: Long): Mono<Void> = studentRepository.deleteById(id)
}