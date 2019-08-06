package com.tabeldata.controller;

import com.tabeldata.dto.LoadKinerjaDto;
import com.tabeldata.service.KinerjaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
