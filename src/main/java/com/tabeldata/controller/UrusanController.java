package com.tabeldata.controller;

import com.tabeldata.entity.UrusanEntity;
import com.tabeldata.service.UrusanService;
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
@RequestMapping("/api/urusan")
public class UrusanController {

    @Autowired
    private UrusanService service;

    /**
     * Get List Urusan By Tahun Anggaran
     */
    @GetMapping("/list")
    public ResponseEntity<List<UrusanEntity>> getListUrusan(@RequestParam String tahunAnggaran) {
        List<UrusanEntity> urusanEntities = service.getListUrusan(tahunAnggaran);
        if (!urusanEntities.isEmpty()) {
            return new ResponseEntity<>(urusanEntities, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Get Urusan By ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UrusanEntity> getListUrusaById(@PathVariable Integer id) {
        UrusanEntity urusanEntity;
        try {
            urusanEntity = service.getUrusanById(id);
            return new ResponseEntity<>(urusanEntity, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new UrusanEntity(), HttpStatus.NO_CONTENT);
        }
    }
}
