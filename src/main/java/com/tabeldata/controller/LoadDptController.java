package com.tabeldata.controller;

import com.tabeldata.entity.LoadDptEntity;
import com.tabeldata.service.LoadDptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public ResponseEntity<List<LoadDptEntity>> getLoadPendapatan(
            @RequestParam String tahunAnggaran,
            @RequestParam String skpdId) {
        List<LoadDptEntity> value = service.getLoadPendapatan(tahunAnggaran, skpdId);
        if (!value.isEmpty()) {
            return new ResponseEntity<>(value, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<LoadDptEntity> getLoadPendapatanById(
            @RequestParam String tahunAnggaran,
            @RequestParam String skpdId,
            @RequestParam String dptId
    ) {
        try {
            LoadDptEntity value = service.getLoadPendapatanByIdDpt(tahunAnggaran, skpdId, dptId);
            return new ResponseEntity<>(value, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
