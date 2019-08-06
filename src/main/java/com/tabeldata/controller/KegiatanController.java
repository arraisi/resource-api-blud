package com.tabeldata.controller;

import com.tabeldata.dto.LoadKegiatanDatatableDto;
import com.tabeldata.service.KegiatanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/kegiatan")
public class KegiatanController {

    @Autowired
    private KegiatanService service;

    /**
     * API Untuk Load Kegiatan Untuk Pertama di Menu Kegiatan Table
     */
    @GetMapping("/load/{tahunAnggaran}/{idSkpd}")
    public ResponseEntity<List<LoadKegiatanDatatableDto>> loadKegiatanDatatable(@PathVariable String tahunAnggaran, @PathVariable Integer idSkpd) {
        List<LoadKegiatanDatatableDto> data = service.loadKegiatanDatatable(tahunAnggaran, idSkpd);
        if (!data.isEmpty()) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * API Untuk Get No Kegiatan
     */
    @GetMapping("/load/no-kegiatan/{tahunAnggaran}/{idProgram}/{idSkpd}")
    public ResponseEntity<?> loadNoKegiatan(
            @PathVariable String tahunAnggaran,
            @PathVariable Integer idProgram,
            @PathVariable Integer idSkpd
    ) {
        Map<String, Object> kegiatanNo = new HashMap<>();
        try {
            kegiatanNo.put("noKegiatan", service.getNoKegiatan(tahunAnggaran, idProgram, idSkpd));
            return new ResponseEntity<>(kegiatanNo, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
