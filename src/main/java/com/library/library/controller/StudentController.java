package com.library.library.controller;




import com.library.library.dto.request.AuthorRequest;
import com.library.library.dto.request.LoanRequest;
import com.library.library.dto.request.StudentRequest;
import com.library.library.dto.response.AuthorResponse;
import com.library.library.dto.response.LoanResponse;
import com.library.library.dto.response.StudentResponse;
import com.library.library.service.StudentService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "student")
@AllArgsConstructor
public class StudentController {


    private final StudentService studentService;
    @PostMapping
    public ResponseEntity<StudentResponse> post(@RequestBody StudentRequest studentRequest){
        return ResponseEntity.ok(studentService.create(studentRequest));
    }
    @GetMapping
    public ResponseEntity<Set<StudentResponse>> getAll(){
        return ResponseEntity.ok((this.studentService.findAll()));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity <StudentResponse> get(@PathVariable Long id){
        return  ResponseEntity.ok(this.studentService.read(id));

    }

    @PutMapping(path = "{id}")
    public ResponseEntity<StudentResponse> update(@RequestBody StudentRequest studentResponse, @PathVariable Long id){

        return ResponseEntity.ok(this.studentService.update(studentResponse,id));
    }


}
