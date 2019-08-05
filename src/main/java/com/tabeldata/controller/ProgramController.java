package com.tabeldata.controller;

import com.tabeldata.entity.ProgramEntity;
import com.tabeldata.service.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/program")
public class ProgramController {

    @Autowired
    private ProgramService service;

    @GetMapping("/list")
    public ResponseEntity<List<ProgramEntity>> getListProgram() {
        List<ProgramEntity> program = service.getListProgram();
        if (!program.isEmpty()) {
            return new ResponseEntity<>(program, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }
}
