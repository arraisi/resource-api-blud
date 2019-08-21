package com.tabeldata.controller;

import com.tabeldata.dto.KomponenBelanjaEditDto;
import com.tabeldata.dto.KomponenBelanjaGetDto;
import com.tabeldata.service.KomponenBelanjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/komponen-belanja")
public class KomponenBelanjaController {

    @Autowired
    KomponenBelanjaService service;

    @PostMapping("/save/pegawai")
    public ResponseEntity<List<KomponenBelanjaGetDto>> savePegawai(@RequestBody List<KomponenBelanjaGetDto> komponenList,
                                                                   @RequestParam Integer idKegiatan,
                                                                   @RequestParam Integer idSkpd,
                                                                   @RequestParam String tahunAnggaran,
                                                                   @RequestParam BigDecimal anggaran,
                                                                   Principal principal
    ) {

        List<KomponenBelanjaGetDto> list = service.saveBelanja(komponenList, idKegiatan, idSkpd, tahunAnggaran, "pegawai", anggaran, principal);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/save/barang")
    public ResponseEntity<List<KomponenBelanjaGetDto>> saveBarang(@RequestBody List<KomponenBelanjaGetDto> komponenList,
                                                                  @RequestParam Integer idKegiatan,
                                                                  @RequestParam Integer idSkpd,
                                                                  @RequestParam String tahunAnggaran,
                                                                  @RequestParam BigDecimal anggaran,
                                                                  Principal principal
    ) {

        List<KomponenBelanjaGetDto> list = service.saveBelanja(komponenList, idKegiatan, idSkpd, tahunAnggaran, "barang", anggaran, principal);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/save/modal")
    public ResponseEntity<List<KomponenBelanjaGetDto>> save(@RequestBody List<KomponenBelanjaGetDto> komponenList,
                                                            @RequestParam Integer idKegiatan,
                                                            @RequestParam Integer idSkpd,
                                                            @RequestParam String tahunAnggaran,
                                                            @RequestParam BigDecimal anggaran,
                                                            Principal principal
    ) {

        List<KomponenBelanjaGetDto> list = service.saveBelanja(komponenList, idKegiatan, idSkpd, tahunAnggaran, "modal", anggaran, principal);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/load/pegawai")
    public ResponseEntity<List<KomponenBelanjaGetDto>> loadPegawai(@RequestParam Integer idKegiatan, @RequestParam String tahunAngg) {
        List<KomponenBelanjaGetDto> listPegawai = service.loadKomponen(idKegiatan, tahunAngg, "pegawai");
        return new ResponseEntity<>(listPegawai, HttpStatus.OK);
    }

    @GetMapping("/load/barang")
    public ResponseEntity<List<KomponenBelanjaGetDto>> loadBarang(@RequestParam Integer idKegiatan, @RequestParam String tahunAngg) {
        List<KomponenBelanjaGetDto> listBarang = service.loadKomponen(idKegiatan, tahunAngg, "barang");
        return new ResponseEntity<>(listBarang, HttpStatus.OK);
    }

    @GetMapping("/load/modal")
    public ResponseEntity<List<KomponenBelanjaGetDto>> loadModal(@RequestParam Integer idKegiatan, @RequestParam String tahunAngg) {
        List<KomponenBelanjaGetDto> listModal = service.loadKomponen(idKegiatan, tahunAngg, "modal");
        return new ResponseEntity<>(listModal, HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<KomponenBelanjaEditDto> getById(@PathVariable Integer id) {
        KomponenBelanjaEditDto komponen = service.getById(id);
        return new ResponseEntity<>(komponen, HttpStatus.OK);
    }

    @PostMapping("/edit-volume")
    public ResponseEntity<?> editVolume(@RequestBody KomponenBelanjaEditDto komponen) {
        service.editVolume(komponen);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
