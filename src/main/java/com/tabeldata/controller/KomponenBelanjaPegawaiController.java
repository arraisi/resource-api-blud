package com.tabeldata.controller;

import com.tabeldata.entity.KomponenBelanjaPegawaiEntity;
import com.tabeldata.service.KomponenBelanjaPegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/komponen/belanja-pegawai")
public class KomponenBelanjaPegawaiController {

    @Autowired
    KomponenBelanjaPegawaiService service;

    @PostMapping("/save")
    public  ResponseEntity<?> save(@RequestBody List<KomponenBelanjaPegawaiEntity> komponenList,
                                   @RequestParam Integer idKegiatan,
                                   @RequestParam Integer idSkpd,
                                   @RequestParam String tahunAnggaran,
                                   @RequestParam String kodeKegiatan,
                                   Principal principal
                                   ) {

            service.saveBelanjaPegawai(komponenList, idKegiatan, idSkpd, tahunAnggaran, kodeKegiatan, principal);
            return new ResponseEntity<>(HttpStatus.OK);
    }

}
