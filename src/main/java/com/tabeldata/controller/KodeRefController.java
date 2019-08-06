package com.tabeldata.controller;

import com.tabeldata.dto.KodeRefDto;
import com.tabeldata.service.KodeRefService;
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
@RequestMapping("/api/kodefungsi")
public class KodeRefController {

    @Autowired
    private KodeRefService service;

    /**
     * API Get List Lookup INDIKATOR (Sewaktu akan Tambah Kinerja)
     */
    @GetMapping("/list")
    public ResponseEntity<List<KodeRefDto>> getKodeRef() {
        List<KodeRefDto> data = service.getKodeRef();
        if (!data.isEmpty()) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }
}
