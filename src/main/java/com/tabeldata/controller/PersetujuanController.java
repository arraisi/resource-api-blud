package com.tabeldata.controller;

import com.tabeldata.dto.LoadKegiatanDatatableDto;
import com.tabeldata.service.PersetujuanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/persetujuan")
public class PersetujuanController {

    @Autowired
    private PersetujuanService service;

    @PostMapping("/operator/kirim")
    public ResponseEntity<List<LoadKegiatanDatatableDto>> submitKegiatanOperator(@RequestParam String tahunAnggaran, Principal principal) {

        try {
            List<LoadKegiatanDatatableDto> data = service.submitKegiatanOperator(tahunAnggaran, principal);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/bendahara/terima")
    public ResponseEntity<List<LoadKegiatanDatatableDto>> terimaKegiatanBendahara(@RequestParam String tahunAnggaran, @RequestParam Integer kegiatanId, Principal principal) {

        try {
            List<LoadKegiatanDatatableDto> data = service.terimaKegiatanBendahara(tahunAnggaran, kegiatanId, principal);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/bendahara/tolak")
    public ResponseEntity<List<LoadKegiatanDatatableDto>> tolakKegiatanBendahara(@RequestBody String catatan, @RequestParam String tahunAnggaran, @RequestParam Integer kegiatanId, Principal principal) {

        try {
            List<LoadKegiatanDatatableDto> data = service.tolakKegiatanBendahara(tahunAnggaran, kegiatanId, catatan, principal);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/bendahara/kirim")
    public ResponseEntity<List<LoadKegiatanDatatableDto>> kirimKegiatanBendahara(@RequestParam String tahunAnggaran, Principal principal) {
        try {
            List<LoadKegiatanDatatableDto> data = service.kirimKegiatanOlehBendahara(tahunAnggaran, principal);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/kegiatan/status/tidak-terima")
    public ResponseEntity<Long> checkKegiatanBelumDiTerima(@RequestParam String tahunAnggaran, Principal principal) {
        try {
            Long data = service.checkKegiatanBelumDisetujui(tahunAnggaran, principal);
            if (data > 0) {
                return new ResponseEntity<>(data, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status/appv/kepala-dinas")
    public ResponseEntity<?> checkStatusAppvKepalaDanDinas(@RequestParam String tahunAnggaran, @RequestParam Integer idSkpd, Principal principal) {
        try {
            Map<String, Object> data = service.checkStatusAppvKepalaDanDinas(tahunAnggaran, idSkpd, principal);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/kepala/terima")
    public ResponseEntity<List<LoadKegiatanDatatableDto>> terimaKegiatanKepalaSkpd(@RequestParam String tahunAnggaran, Principal principal) {

        try {
            List<LoadKegiatanDatatableDto> data = service.terimaKegiatanOlehKepalaSKPD(tahunAnggaran, principal);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/kepala/tolak")
    public ResponseEntity<List<LoadKegiatanDatatableDto>> tolakKegiatanKepalaSkpd(@RequestBody String catatan, @RequestParam String tahunAnggaran, Principal principal) {

        try {
            List<LoadKegiatanDatatableDto> data = service.tolakKegiatanOlehKepalaSKPD(tahunAnggaran, catatan, principal);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/dinas/terima")
    public ResponseEntity<List<LoadKegiatanDatatableDto>> terimaKegiatanDinasTeknis(@RequestParam Integer idSkpd, @RequestParam String tahunAnggaran, Principal principal) {

        try {
            List<LoadKegiatanDatatableDto> data = service.terimaKegiatanOlehDinasTeknis(idSkpd, tahunAnggaran, principal);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/dinas/tolak")
    public ResponseEntity<List<LoadKegiatanDatatableDto>> tolakKegiatanDinasTeknis(@RequestParam Integer idSkpd, @RequestBody String catatan, @RequestParam String tahunAnggaran, Principal principal) {

        try {
            List<LoadKegiatanDatatableDto> data = service.tolakKegiatanOlehDinasTeknis(idSkpd, tahunAnggaran, catatan, principal);
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
