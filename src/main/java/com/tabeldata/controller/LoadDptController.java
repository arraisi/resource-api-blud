package com.tabeldata.controller;

import com.tabeldata.entity.LoadDpt;
import com.tabeldata.service.LoadDptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/pendapatan")
public class LoadDptController {

    @Autowired
    private LoadDptService service;

    @GetMapping("/load")
    public ResponseEntity<List<LoadDpt>> getLoadPendapatan(
            @RequestParam String tahunAnggaran,
            @RequestParam String skpdId) {
        List<LoadDpt> value = service.getLoadPendapatan(tahunAnggaran, skpdId);
        if (!value.isEmpty()) {
            return new ResponseEntity<>(value, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }
}
