package com.tabeldata.controller;

import com.tabeldata.dto.KegiatanGetDto;
import com.tabeldata.dto.LoadKegiatanDatatableDto;
import com.tabeldata.dto.LokasiKegiatanDto;
import com.tabeldata.entity.KegiatanEntity;
import com.tabeldata.service.KegiatanService;
import com.tabeldata.service.LokasiKegiatanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;
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

    @Autowired
    private LokasiKegiatanService lokasiKegiatanService;


    /**
     * Lokasi Kegiatan List For Master
     */
    @GetMapping("/lokasi/list")
    public ResponseEntity<List<LokasiKegiatanDto>> getListLokasiKegiatan() {
        List<LokasiKegiatanDto> value = lokasiKegiatanService.getListLokasiKegiatan();
        if (!value.isEmpty()) {
            return new ResponseEntity<>(value, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * API Untuk Load Kegiatan Untuk Pertama di Menu Kegiatan Table
     */
    @GetMapping("/load/{tahunAnggaran}/{idSkpd}")
    public ResponseEntity<List<LoadKegiatanDatatableDto>> loadKegiatanDatatable(
            @PathVariable String tahunAnggaran,
            @PathVariable Integer idSkpd,
            Principal principal) {
        List<LoadKegiatanDatatableDto> data = service.loadKegiatanDatatable(tahunAnggaran, idSkpd, principal);
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

    @PostMapping("/save")
    public ResponseEntity<KegiatanGetDto> saveKegiatan(@RequestBody KegiatanEntity kegiatan, Principal principal) {
        KegiatanGetDto value;
        try {
            value = service.saveOrUpdate(kegiatan, principal);
            return new ResponseEntity<>(value, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new KegiatanGetDto(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idKegiatan}")
    public ResponseEntity<KegiatanGetDto> getKegiatanById(@PathVariable Integer idKegiatan) {
        KegiatanGetDto value;
        try {
            value = service.getKegiatanByID(idKegiatan);
            return new ResponseEntity<>(value, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new KegiatanGetDto(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/verifikasi/anggaran")
    public ResponseEntity<?> verifikasiAnggaranSubmitOperator(@RequestParam Integer idSkpd, @RequestParam String tahunAnggaran) {
        try {
            Map<String, Object> value = service.verifikasiAnggaranSubmitOperator(idSkpd, tahunAnggaran);
            return new ResponseEntity<>(value, HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
