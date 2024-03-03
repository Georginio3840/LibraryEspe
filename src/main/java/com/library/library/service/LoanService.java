package com.library.library.service;

import com.library.library.dto.request.LoanRequest;
import com.library.library.dto.response.BookResponse;
import com.library.library.dto.response.LoanResponse;
import com.library.library.dto.response.StudentResponse;
import com.library.library.entity.Loan;
import com.library.library.exception.NotFoundException;
import com.library.library.repository.BookRepository;
import com.library.library.repository.LoanRepository;
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
public class LoanService implements  ILoanService {


    private final LoanRepository loanRepository;
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;
    @Override
    public LoanResponse create(LoanRequest request) {
        var student = studentRepository.findById(request.getIdStudent()).orElseThrow(()->new NotFoundException("Student not found"));
        var book= bookRepository.findById(request.getIdBook()).orElseThrow(()->new NotFoundException("Student not found"));
        var loanToPersist = Loan.builder()
                .loanDate(request.getLoanDate())
                .returnDate(request.getReturnDate())
                .book(book)
                .student(student)
                .build();
        var loanPersisted =  this.loanRepository.save(loanToPersist);
        return this.entityToResponse(loanPersisted);
    }

    @Override
    public LoanResponse read(Long id) {

        var loanFromDb= this.loanRepository.findById(id).orElseThrow(()->new NotFoundException("Loan not found"));

        return this.entityToResponse(loanFromDb);
    }

    @Override
    public LoanResponse update(LoanRequest request, Long id) {
        var loanToUpdate=loanRepository.findById(id).orElseThrow(()->new NotFoundException("Not found"));
        loanToUpdate.setLoanDate(request.getLoanDate());
        loanToUpdate.setReturnDate(request.getReturnDate());

        var loanUpdated = this.loanRepository.save(loanToUpdate);

        log.info("Ticket updated whith id{}",loanUpdated.getId());
        return this.entityToResponse(loanUpdated);

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Set<LoanResponse> findAll() {
        Iterable<Loan> ticketFromDb=this.loanRepository.findAll();
        Set <LoanResponse>loanResponses=new HashSet<>();
        ticketFromDb.forEach(loan -> loanResponses.add(entityToResponse(loan)));
        if(loanResponses.isEmpty()){
            throw new NotFoundException("Ticket not found");
        }


        return loanResponses;
    }



    private LoanResponse entityToResponse(Loan entity){
        var response = new LoanResponse();
        //Entity son los datos y copia exactamente en el response
        BeanUtils.copyProperties(entity,response);
        var studentResponse = new StudentResponse();
        BeanUtils.copyProperties(entity.getStudent(),studentResponse);
        response.setStudent(studentResponse);
        var bookResponse = new BookResponse();
        BeanUtils.copyProperties(entity.getBook(),bookResponse);
        response.setBook(bookResponse);
        return response;

    }
}
