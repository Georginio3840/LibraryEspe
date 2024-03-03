package com.library.library.controller;


import com.library.library.dto.request.LoanRequest;
import com.library.library.dto.response.LoanResponse;
import com.library.library.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "loan")
@AllArgsConstructor
public class LoanController {

    private final ILoanService loanService;

    @PostMapping
    public ResponseEntity <LoanResponse> post(@RequestBody LoanRequest loanRequest){
        return ResponseEntity.ok(loanService.create(loanRequest));
    }

    @GetMapping
    public ResponseEntity <Set<LoanResponse>> getAll(){

        return  ResponseEntity.ok(loanService.findAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity <LoanResponse> get(@PathVariable Long id){
        return  ResponseEntity.ok(this.loanService.read(id));

    }

    @PutMapping(path = "{id}")
    public ResponseEntity <LoanResponse> put(@RequestBody LoanRequest loanRequest, @PathVariable Long id){

        return null;
    }


}
