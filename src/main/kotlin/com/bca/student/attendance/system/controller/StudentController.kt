package com.bca.student.attendance.system.controller
import com.bca.student.attendance.system.model.dao.Student
import com.bca.student.attendance.system.service.StudentService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/students")
class StudentController(private val studentService: StudentService) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllStudents(): Flux<Student> = studentService.findAll()

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getStudentById(@PathVariable id: Long): Mono<Student> = studentService.findById(id)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createStudent(@RequestBody student: Student): Mono<Student> = studentService.save(student)

    @PutMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateStudent(@PathVariable id: Long, @RequestBody student: Student): Mono<Student> = studentService.update(id, student)

    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: Long): Mono<Void> = studentService.delete(id)
}