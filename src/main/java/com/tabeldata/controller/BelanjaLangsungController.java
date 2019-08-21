package com.tabeldata.controller;

import com.tabeldata.service.BelanjaLangsungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/belanja-langsung")
public class BelanjaLangsungController {

    @Autowired
    BelanjaLangsungService service;

    @GetMapping("/anggaran/pegawai")
    public ResponseEntity<BigDecimal> getAnggaranPegawai(
            @RequestParam Integer idKegiatan,
            @RequestParam String tahunAnggaran,
            @RequestParam Integer idSkpd) {

        BigDecimal anggaran = service.getAnggaranByTipeKomponen(idKegiatan, tahunAnggaran, idSkpd, "pegawai");
        return new ResponseEntity<>(anggaran, HttpStatus.OK);
    }

    @GetMapping("/anggaran/barang")
    public ResponseEntity<BigDecimal> getAnggaranBarang(
            @RequestParam Integer idKegiatan,
            @RequestParam String tahunAnggaran,
            @RequestParam Integer idSkpd) {

        BigDecimal anggaran = service.getAnggaranByTipeKomponen(idKegiatan, tahunAnggaran, idSkpd, "barang");
        return new ResponseEntity<>(anggaran, HttpStatus.OK);
    }

    @GetMapping("/anggaran/modal")
    public ResponseEntity<BigDecimal> getAnggaranModal(
            @RequestParam Integer idKegiatan,
            @RequestParam String tahunAnggaran,
            @RequestParam Integer idSkpd) {

        BigDecimal anggaran = service.getAnggaranByTipeKomponen(idKegiatan, tahunAnggaran, idSkpd, "modal");
        return new ResponseEntity<>(anggaran, HttpStatus.OK);
    }

}
