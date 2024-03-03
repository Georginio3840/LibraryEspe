package com.library.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookResponse {

    private Long id;
    private AuthorResponse author;
    private String name;
    private String publishingHouse;
    private Date publicationDate;
    private Boolean state;
}
