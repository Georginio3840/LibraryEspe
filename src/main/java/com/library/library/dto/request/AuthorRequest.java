package com.library.library.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthorRequest {


    private String authorFirstName;
    private String authorLastName;
    private String nationality;

}
