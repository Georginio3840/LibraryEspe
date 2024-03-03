package com.library.library.service;


import com.library.library.dto.request.StudentRequest;
import com.library.library.dto.response.StudentResponse;


public interface IStudentService  extends  CrudService<StudentRequest, StudentResponse,Long> {

}
