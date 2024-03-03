package com.library.library.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookRequest {

    @NotNull(message = "El campo no puede estar vacío")
    @Size(min = 1, max = 60)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Solo se permiten letras")
    public Long  idAuthor;

    @NotNull(message = "El campo no puede estar vacío")
    @Size(max = 60)
    public String name;
    public String publishingHouse;
    public Date publicationDate;
    public Boolean state;

}
