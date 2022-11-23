package com.libr.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterRepository extends CrudRepository<Writer, Long> {}

