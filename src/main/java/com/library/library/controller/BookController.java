package com.library.library.controller;


import com.library.library.dto.request.BookRequest;
import com.library.library.dto.response.BookResponse;
import com.library.library.service.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(path = "book")
@AllArgsConstructor
public class BookController {
    private final IBookService bookService;

    @PostMapping
    public ResponseEntity <BookResponse> post(@Valid @RequestBody BookRequest bookRequest){
        return ResponseEntity.ok(bookService.create(bookRequest));
    }

    @GetMapping
    public ResponseEntity<Set<BookResponse>> getAll(){
        Set<BookResponse> books= bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity <BookResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(bookService.read(id));
    }
    @PutMapping(path = "{id}")
    public ResponseEntity<BookResponse> update(@RequestBody BookRequest bookRequest,@PathVariable Long id){
        return ResponseEntity.ok(this.bookService.update(bookRequest,id));

    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
