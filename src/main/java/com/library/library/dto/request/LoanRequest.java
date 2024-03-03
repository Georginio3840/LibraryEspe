package com.library.library.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoanRequest {
    private Date loanDate;
    private Date returnDate;
    private Long idBook;
    private Long idStudent;
}
