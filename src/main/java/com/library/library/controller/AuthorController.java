package com.library.library.controller;


import com.library.library.dto.request.AuthorRequest;
import com.library.library.dto.response.AuthorResponse;
import com.library.library.service.IAuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "author")
@AllArgsConstructor
public class AuthorController {

    private final IAuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponse> post(@RequestBody AuthorRequest authorRequest){
        return ResponseEntity.ok(authorService.create(authorRequest));

    }

    @GetMapping
    public ResponseEntity<Set<AuthorResponse>> getAll(){
        Set <AuthorResponse> authors=authorService.findAll();
        return ResponseEntity.ok(authors);
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<AuthorResponse> get(@PathVariable Long id){
    return ResponseEntity.ok(this.authorService.read(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<AuthorResponse> update(@RequestBody AuthorRequest authorRequest, @PathVariable Long id){

        return ResponseEntity.ok(this.authorService.update(authorRequest,id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        this.authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
