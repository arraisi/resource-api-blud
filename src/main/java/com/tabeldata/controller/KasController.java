package com.tabeldata.controller;

import com.tabeldata.entity.KasEntity;
import com.tabeldata.entity.TmrKasEntity;
import com.tabeldata.service.KasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/kasController")
public class KasController {

    @Autowired
    private KasService service;


    @GetMapping("/findAll")
    @ResponseBody
    public List<TmrKasEntity> findAll() {
        return service.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TmrKasEntity value) {
        service.save(value);
        return ResponseEntity.ok(value);
    }


}
