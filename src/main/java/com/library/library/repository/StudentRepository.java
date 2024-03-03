package com.library.library.repository;


import com.library.library.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;




@Service
public interface StudentRepository extends CrudRepository<Student,Long> {


}
