package com.library.library.entity;


import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "book")
@Builder
public class Book implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

     @NotBlank(message = "El nombre del libro no puede estar en blanco")
    private String name;
  
    @NotBlank(message = "La editorial del libro no puede estar en blanco")
    private String publishingHouse;

    @NotNull(message = "La fecha de publicación del libro no puede ser nula")
    @PastOrPresent(message = "La fecha de publicación del libro debe ser en el pasado o el presente")
    private Date publicationDate;

    @NotNull(message = "El estado del libro no puede ser nulo")
    private Boolean state;


    @ManyToOne
    @JoinColumn(name = "authorId")
    public Author author;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public List<Loan> loans;


}
