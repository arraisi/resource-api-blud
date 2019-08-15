package com.tabeldata.controller;

import com.tabeldata.entity.KomponenBelanjaEntity;
import com.tabeldata.service.KomponenBelanjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/komponen-belanja")
public class KomponenBelanjaController {

    @Autowired
    KomponenBelanjaService service;

    @PostMapping("/save")
    public  ResponseEntity<List<KomponenBelanjaEntity>> save(@RequestBody List<KomponenBelanjaEntity> komponenList,
                                                             @RequestParam Integer idKegiatan,
                                                             @RequestParam Integer idSkpd,
                                                             @RequestParam String tahunAnggaran,
                                                             @RequestParam String kodeKegiatan,
                                                             Principal principal
                                   ) {

            List<KomponenBelanjaEntity> list = service.saveBelanjaPegawai(komponenList, idKegiatan, idSkpd, tahunAnggaran, kodeKegiatan, principal);
            return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
