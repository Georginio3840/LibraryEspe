package com.library.library.service;

import com.library.library.dto.request.LoanRequest;
import com.library.library.dto.response.LoanResponse;


public interface ILoanService  extends  CrudService<LoanRequest, LoanResponse,Long> {
}
