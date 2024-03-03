package com.library.library.service;

import com.library.library.dto.request.AuthorRequest;
import com.library.library.dto.response.AuthorResponse;

public interface IAuthorService extends CrudService<AuthorRequest, AuthorResponse,Long> {
}
