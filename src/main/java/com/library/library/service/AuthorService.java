package com.library.library.service;


import com.library.library.dto.request.AuthorRequest;
import com.library.library.dto.response.AuthorResponse;
import com.library.library.entity.Author;
import com.library.library.exception.NotFoundException;
import com.library.library.repository.AuthorRepository;
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
public class AuthorService implements  IAuthorService{

    private final AuthorRepository authorRepository;
    @Override
    public AuthorResponse create(AuthorRequest request) {


       var authorToPersist= Author.builder()
               .authorFirstName(request.getAuthorFirstName())
               .authorLastName(request.getAuthorLastName())
               .nationality(request.getNationality())
               .build();
       var authorPersisted=this.authorRepository.save(authorToPersist);
       return this.entityToResponse(authorPersisted);
    }

    @Override
    public AuthorResponse read(Long id) {

        var authorFromDb = this.authorRepository.findById(id).orElseThrow(()->new NotFoundException("Author not found"));
        return this.entityToResponse(authorFromDb);
    }

    @Override
    public AuthorResponse update(AuthorRequest request, Long id) {

        var authorToUpdate = authorRepository.findById(id).orElseThrow(()->new NotFoundException("Author not found"));
        authorToUpdate.setAuthorFirstName(request.getAuthorFirstName());
        authorToUpdate.setAuthorLastName(request.getAuthorLastName());
        authorToUpdate.setNationality(request.getNationality());
        var authorUpdated=this.authorRepository.save(authorToUpdate);
        return this.entityToResponse(authorUpdated);
    }

    @Override
    public void delete(Long id) {

        var authorToDelete =authorRepository.findById(id).orElseThrow(()->new NotFoundException("Author not found"));
        authorToDelete.getBooks().clear(); // Eliminar todos los libros asociados
        authorRepository.delete(authorToDelete);

    }

    @Override
    public Set<AuthorResponse> findAll() {

        Iterable<Author> authorFromDb=this.authorRepository.findAll();
        Set <AuthorResponse> authorResponses =  new HashSet<>();
        authorFromDb.forEach(author -> authorResponses.add(entityToResponse(author)));
        if(authorResponses.isEmpty()){
            throw new NotFoundException("Author not found");
        }
        return authorResponses;
    }


    private AuthorResponse entityToResponse(Author entity){
        var response =  new AuthorResponse();
        BeanUtils.copyProperties(entity,response);
        return response;

    }
}
