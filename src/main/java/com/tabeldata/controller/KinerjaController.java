package com.tabeldata.controller;

import com.tabeldata.dto.KinerjaSaveDto;
import com.tabeldata.dto.LoadKinerjaDto;
import com.tabeldata.service.KinerjaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/kinerja")
public class KinerjaController {

    @Autowired
    private KinerjaService service;


    /**
     * Load List Kinerja Table Awal Buka Page Kegiatan di Tab Kegiatan
     */
    @GetMapping("/list/{tahunAnggaran}/{idKegiatan}/{idSkpd}")
    public ResponseEntity<List<LoadKinerjaDto>> loadKinerja(
            @PathVariable String tahunAnggaran,
            @PathVariable Integer idKegiatan,
            @PathVariable Integer idSkpd) {

        List<LoadKinerjaDto> data = service.loadKinerja(tahunAnggaran, idKegiatan, idSkpd);
        if (!data.isEmpty()) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<List<LoadKinerjaDto>> saveKinerja(
            @RequestParam String tahunAnggaran,
            @RequestParam Integer idKegiatan,
            @RequestParam Integer idSkpd,
            @RequestBody KinerjaSaveDto data,
            Principal principal
    ) {
        try {
            List<LoadKinerjaDto> kinerjaSave = service.saveKinerja(data, tahunAnggaran, idKegiatan, idSkpd, principal);
            return new ResponseEntity<>(kinerjaSave, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
