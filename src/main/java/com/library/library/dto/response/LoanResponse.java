package com.library.library.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoanResponse {
    private Long id;
    private Date loanDate;
    private Date returnDate;
    private StudentResponse student;
    private BookResponse book;


}
