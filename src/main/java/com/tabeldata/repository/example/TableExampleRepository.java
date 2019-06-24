package com.tabeldata.repository.example;

import com.tabeldata.entity.example.TableExample;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TableExampleRepository extends CrudRepository<TableExample, String> {

    List<TableExample> findAll();
}
