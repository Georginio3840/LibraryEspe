package com.library.library.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String studentId;
}
