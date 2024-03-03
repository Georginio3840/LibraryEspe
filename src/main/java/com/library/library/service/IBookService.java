package com.library.library.service;

import com.library.library.dto.request.BookRequest;
import com.library.library.dto.response.BookResponse;

public interface IBookService extends  CrudService<BookRequest, BookResponse,Long> {
}
