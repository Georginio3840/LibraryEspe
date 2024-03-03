package com.library.library.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthorResponse {
    private Long id;
    private String authorFirstName;
    private String authorLastName;
    private String nationality;


}
