package com.tabeldata.controller;

import com.tabeldata.dto.KomponenBelanjaEditDto;
import com.tabeldata.dto.KomponenBelanjaGetDto;
import com.tabeldata.service.KomponenBelanjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
                                                                   Principal principal
    ) {

        List<KomponenBelanjaGetDto> list = service.saveBelanja(komponenList, idKegiatan, idSkpd, tahunAnggaran, "pegawai", principal);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/save/barang")
    public ResponseEntity<List<KomponenBelanjaGetDto>> saveBarang(@RequestBody List<KomponenBelanjaGetDto> komponenList,
                                                                  @RequestParam Integer idKegiatan,
                                                                  @RequestParam Integer idSkpd,
                                                                  @RequestParam String tahunAnggaran,
                                                                  Principal principal
    ) {

        List<KomponenBelanjaGetDto> list = service.saveBelanja(komponenList, idKegiatan, idSkpd, tahunAnggaran, "barang", principal);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/save/modal")
    public ResponseEntity<List<KomponenBelanjaGetDto>> saveModal(@RequestBody List<KomponenBelanjaGetDto> komponenList,
                                                            @RequestParam Integer idKegiatan,
                                                            @RequestParam Integer idSkpd,
                                                            @RequestParam String tahunAnggaran,
                                                            Principal principal
    ) {

        List<KomponenBelanjaGetDto> list = service.saveBelanja(komponenList, idKegiatan, idSkpd, tahunAnggaran, "modal", principal);
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

    @GetMapping("/{id}")
    public ResponseEntity<KomponenBelanjaGetDto> getKomponenById(@PathVariable Integer id) {
        KomponenBelanjaGetDto komponen = service.getKomponenBelanjaById(id);
        return new ResponseEntity<>(komponen, HttpStatus.OK);
    }

    @PostMapping("/edit-volume/pegawai")
    public ResponseEntity<?> editVolumePegawai(@RequestBody KomponenBelanjaEditDto komponen) {
        service.editVolume(komponen, "pegawai");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/edit-volume/barang")
    public ResponseEntity<?> editVolumeBarang(@RequestBody KomponenBelanjaEditDto komponen) {
        service.editVolume(komponen, "barang");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/edit-volume/modal")
    public ResponseEntity<?> editVolumeModal(@RequestBody KomponenBelanjaEditDto komponen) {
        service.editVolume(komponen, "modal");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/rpa/updateAnggaran")
    public ResponseEntity<?> updateRpa(@RequestBody KomponenBelanjaGetDto komponen, Principal principal) {
        try {
            service.updateRpaKomponen(komponen, principal);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/update-anggaran-kegiatan/{id}")
    public ResponseEntity<?> updateAnggaranKegiatan(@PathVariable Integer id, @RequestParam BigDecimal anggaran) {
        service.updateAnggaranKegiatan(id, anggaran);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/delete/pegawai/{id}")
    public ResponseEntity<List<KomponenBelanjaGetDto>> deleteKomponenPegawaiFromRinci(@PathVariable Integer id,
                                                     @RequestParam Integer idKegiatan,
                                                     @RequestParam String tahunAnggaran,
                                                     @RequestParam Integer idSkpd) {

        List<KomponenBelanjaGetDto> list = service.deleteKomponenFromRinci(id, idKegiatan, tahunAnggaran, idSkpd, "pegawai");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/delete/barang/{id}")
    public ResponseEntity<List<KomponenBelanjaGetDto>> deleteKomponenBarangFromRinci(@PathVariable Integer id,
                                                                               @RequestParam Integer idKegiatan,
                                                                               @RequestParam String tahunAnggaran,
                                                                               @RequestParam Integer idSkpd) {

        List<KomponenBelanjaGetDto> list = service.deleteKomponenFromRinci(id, idKegiatan, tahunAnggaran, idSkpd, "barang");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/delete/modal/{id}")
    public ResponseEntity<List<KomponenBelanjaGetDto>> deleteKomponenModalFromRinci(@PathVariable Integer id,
                                                                               @RequestParam Integer idKegiatan,
                                                                               @RequestParam String tahunAnggaran,
                                                                               @RequestParam Integer idSkpd) {

        List<KomponenBelanjaGetDto> list = service.deleteKomponenFromRinci(id, idKegiatan, tahunAnggaran, idSkpd, "modal");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
