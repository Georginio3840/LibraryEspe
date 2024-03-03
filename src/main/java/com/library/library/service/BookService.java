package com.library.library.service;

import com.library.library.dto.request.BookRequest;
import com.library.library.dto.response.AuthorResponse;
import com.library.library.dto.response.BookResponse;
import com.library.library.entity.Book;
import com.library.library.exception.NotFoundException;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.BookRepository;
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
public class BookService implements IBookService{
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    @Override
    public BookResponse create(BookRequest request) {
        var author = this.authorRepository.findById(request.getIdAuthor()).orElseThrow(()->new NotFoundException("Author not found"));
        var bookToPersist = Book.builder()
                .name(request.getName())
                .publishingHouse(request.getPublishingHouse())
                .publicationDate(request.getPublicationDate())
                .state(request.getState())
                .author(author)
                .build();

        var bookPersisted=this.bookRepository.save(bookToPersist);
        return this.entityToResponse(bookPersisted);

    }

    @Override
    public BookResponse read(Long id) {

        var receiptFromDb = this.bookRepository.findById(id).orElseThrow(()->new NotFoundException("Book not Found"));

        return this.entityToResponse(receiptFromDb);
    }

    @Override
    public BookResponse update(BookRequest request, Long id) {

        var bookToUpdate=bookRepository.findById(id).orElseThrow(()->new NotFoundException("Not found"));
        bookToUpdate.setName(request.getName());
        bookToUpdate.setPublishingHouse(request.getPublishingHouse());
        bookToUpdate.setPublicationDate(request.getPublicationDate());

        var bookUpdated = this.bookRepository.save(bookToUpdate);

        log.info("Ticket updated whith id{}",bookUpdated.getId());
        return this.entityToResponse(bookToUpdate);
    }

    @Override
    public void delete(Long id) {

        var bookToDelete = bookRepository.findById(id).orElseThrow(()->new NotFoundException("Not found"));
        this.bookRepository.delete(bookToDelete);
    }

    @Override
    public Set<BookResponse> findAll() {

        Iterable <Book> bookFromDb= this.bookRepository.findAll();
        Set <BookResponse> bookResponses=new HashSet<>();
        bookFromDb.forEach(book -> bookResponses.add(entityToResponse(book)));

        if (bookResponses.isEmpty()) {
            throw new NotFoundException("Books not found");
        }
        return bookResponses;
    }
    private BookResponse entityToResponse(Book entity){
        var response = new BookResponse();
        BeanUtils.copyProperties(entity,response);
        var authorResponse = new AuthorResponse();
        BeanUtils.copyProperties(entity.getAuthor(),authorResponse);
        response.setAuthor(authorResponse);
        return response;
    }

}
