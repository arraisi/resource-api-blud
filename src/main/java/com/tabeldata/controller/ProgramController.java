package com.tabeldata.controller;

import com.tabeldata.entity.ProgramEntity;
import com.tabeldata.service.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<ProgramEntity>> getListProgramByIdUrusan(@PathVariable Integer idUrusan, @RequestParam String tahunAnggaran) {
        List<ProgramEntity> program = service.getListProgramByIdUrusan(idUrusan, tahunAnggaran);
        if (!program.isEmpty()) {
            return new ResponseEntity<>(program, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }
}
