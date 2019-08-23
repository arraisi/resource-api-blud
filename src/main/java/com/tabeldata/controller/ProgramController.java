package com.tabeldata.controller;

import com.tabeldata.entity.ProgramEntity;
import com.tabeldata.service.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/program")
public class ProgramController {

    @Autowired
    private ProgramService service;

    /**
     * Get All List Program
     */
    @GetMapping("/list")
    public ResponseEntity<List<ProgramEntity>> getListProgram() {
        List<ProgramEntity> program = service.getListProgram();
        if (!program.isEmpty()) {
            return new ResponseEntity<>(program, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Get List Program By ID Urusan
     */
    @GetMapping("/list/by-id-urusan/{idUrusan}")
    public ResponseEntity<List<ProgramEntity>> getListProgramByIdUrusan(@PathVariable Integer idUrusan, @RequestParam String tahunAnggaran, @RequestParam Integer idSkpd) {
        List<ProgramEntity> program = service.getListProgramByIdUrusan(idUrusan, tahunAnggaran, idSkpd);
        if (!program.isEmpty()) {
            return new ResponseEntity<>(program, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Get All List Program
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProgramEntity> getListProgramById(@PathVariable Integer id) {
        ProgramEntity program;
        try {
            program = service.getProgramByID(id);
            return new ResponseEntity<>(program, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ProgramEntity(), HttpStatus.NO_CONTENT);
        }
    }
}
