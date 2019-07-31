package com.tabeldata.controller;

import com.tabeldata.entity.KasEntity;
import com.tabeldata.entity.TmrKasEntity;
import com.tabeldata.service.KasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/kasController")
public class KasController {

    @Autowired
    private KasService service;


    @GetMapping("/findAll")
    public ResponseEntity<List<TmrKasEntity>> findAll(
            @RequestParam String tahunAnggaran,
            @RequestParam String skpdId) {

        List<TmrKasEntity> value = service.findAll(tahunAnggaran, skpdId);
        if (!value.isEmpty()) {
            return new ResponseEntity<>(value, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody List<TmrKasEntity> value) {
        service.save(value);
        return ResponseEntity.ok(value);
    }


}
