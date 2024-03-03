package com.library.library.service;

import java.util.Set;

public interface CrudService  <RQ,RS,Long> {
    RS create(RQ request);
    RS read (Long id);
    RS update(RQ request, Long id);
    void delete (Long id);
    Set<RS> findAll();
}
