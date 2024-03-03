package com.library.library.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "loan")
@Builder
public class Loan implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Date loanDate;
    private Date returnDate;
    @ManyToOne
    @JoinColumn(name = "bookId")
    public Book book;


    @ManyToOne
    @JoinColumn(name = "studentId")
    public Student student;

}
