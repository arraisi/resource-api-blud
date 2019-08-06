package com.tabeldata.controller;

import com.tabeldata.dto.LoadKomponenDto;
import com.tabeldata.service.LoadKomponenService;
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
@RequestMapping("/api/komponen")
public class KomponenController {

    @Autowired
    private LoadKomponenService loadKomponenService;

    /**
     * API Load Lookup Komponen Table Untuk Page Kegiatan TAB Komponen - Belanja Pegawai
     */
    @GetMapping("/load/belanja/pegawai")
    public ResponseEntity<List<LoadKomponenDto>> loadKomponenBelanjaPegawai(@RequestParam String tahunAnggaran, @RequestParam Integer idSkpd) {
        List<LoadKomponenDto> data = loadKomponenService.loadKomponen("PEGAWAI", tahunAnggaran, idSkpd);
        if (!data.isEmpty()) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * API Load Lookup Komponen Table Untuk Page Kegiatan TAB Komponen - Belanja Barang & Jasa
     */
    @GetMapping("/load/belanja/barang")
    public ResponseEntity<List<LoadKomponenDto>> loadKomponenBelanjaBarangDanJasa(@RequestParam String tahunAnggaran, @RequestParam Integer idSkpd) {
        List<LoadKomponenDto> data = loadKomponenService.loadKomponen("BARANG", tahunAnggaran, idSkpd);
        if (!data.isEmpty()) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * API Load Lookup Komponen Table Untuk Page Kegiatan TAB Komponen - Belanja Modal
     */
    @GetMapping("/load/belanja/modal")
    public ResponseEntity<List<LoadKomponenDto>> loadKomponenBelanjaModal(@RequestParam String tahunAnggaran, @RequestParam Integer idSkpd) {
        List<LoadKomponenDto> data = loadKomponenService.loadKomponen("MODAL", tahunAnggaran, idSkpd);
        if (!data.isEmpty()) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }
}
