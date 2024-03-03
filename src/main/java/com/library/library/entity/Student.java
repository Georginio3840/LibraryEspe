package com.library.library.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "student")
@Builder
public class Student implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private  String username;
    private  String password;

    private String firstName;
    private String lastName;
    private String studentId;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public List<Loan> loans;


}
