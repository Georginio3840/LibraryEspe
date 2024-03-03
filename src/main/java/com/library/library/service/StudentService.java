package com.library.library.service;



import com.library.library.dto.request.StudentRequest;

import com.library.library.dto.response.StudentResponse;

import com.library.library.entity.Student;
import com.library.library.exception.NotFoundException;
import com.library.library.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class StudentService implements  IStudentService {

    private final StudentRepository studentRepository ;




    @Override
    public StudentResponse create(StudentRequest request) {

        var studentToPersist= Student.builder()
                . firstName(request.getFirstName())
                .lastName(request.getLastName())
                .studentId(request.getStudentId())
                .build();
        var studentPersisted=this.studentRepository.save(studentToPersist);
        return this.entityToResponse(studentPersisted);
    }

    @Override
    public StudentResponse read(Long id) {

        var studentFromDb= this.studentRepository.findById(id).orElseThrow(()->new NotFoundException("Loan not found"));

        return this.entityToResponse(studentFromDb);


    }

    @Override
    public StudentResponse update(StudentRequest request, Long id) {
        var studentToUpdate=studentRepository.findById(id).orElseThrow(()->new NotFoundException("Not found"));
        studentToUpdate.setFirstName(request.getFirstName());
        studentToUpdate.setLastName(request.getLastName());

        studentToUpdate.setStudentId(request.getStudentId());

        var studentUpdated = this.studentRepository.save(studentToUpdate);

        log.info("Ticket updated whith id{}",studentToUpdate.getId());
        return this.entityToResponse(studentToUpdate);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Set<StudentResponse> findAll() {

        Iterable<Student> studentFromDb=this.studentRepository.findAll();
        Set <StudentResponse>studentResponses=new HashSet<>();
        studentFromDb.forEach(student -> studentResponses.add(entityToResponse(student)));
        if(studentResponses.isEmpty()){
            throw new NotFoundException("Student not found");
        }


        return studentResponses;
    }

    private StudentResponse entityToResponse(Student entity){
        var response =  new StudentResponse();
        BeanUtils.copyProperties(entity,response);
        return response;

    }

}
