package com.tabeldata.controller;

import com.tabeldata.entity.LoadDptEntity;
import com.tabeldata.entity.PendapatanDptEntity;
import com.tabeldata.entity.RincianPendapatanEntity;
import com.tabeldata.service.PendapatanDptService;
import com.tabeldata.service.RincianPendapatanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/pendapatan")
public class PendapatanDptController {

    @Autowired
    private PendapatanDptService service;

    @Autowired
    private RincianPendapatanService rincianPendapatanService;

    @PostMapping("/save")
    public ResponseEntity<PendapatanDptEntity> savePendapatan(@RequestBody LoadDptEntity loadDptEntity, Principal principal) {
        PendapatanDptEntity value = new PendapatanDptEntity();
        try {
            value = service.savePendapatan(loadDptEntity, principal);
            return new ResponseEntity<>(value, HttpStatus.CREATED);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            log.info("Error Catch : {}", e);
            return new ResponseEntity<>(value, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rincian/load/{idDpt}")
    public ResponseEntity<List<RincianPendapatanEntity>> getRincianPendapatanByDptID(@PathVariable Integer idDpt) {
        List<RincianPendapatanEntity> value = rincianPendapatanService.getListRincianPendapatanBydptId(idDpt);
        if (!value.isEmpty()) {
            return new ResponseEntity<>(value, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }
}
