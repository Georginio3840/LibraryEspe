package com.library.library.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "author")
@Builder
public class Author implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    
    @NotBlank(message = "Author name cannot be blank")
    private String authorFirstName;

    @NotBlank(message = "Author's last name cannot be blank")
    private String authorLastName;
    
    @NotBlank(message = "The author's nationality cannot be blank")
    private String nationality;
    
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    public Set<Book> books;
}
