package com.tabeldata.controller;

import com.sun.istack.Nullable;
import com.tabeldata.entity.RincianPendapatanEntity;
import com.tabeldata.service.RincianPendapatanService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/pendapatan")
public class RincianPendapatanController {

    @Autowired
    private RincianPendapatanService service;

//    @PostMapping("/rincian/save")
//    public ResponseEntity<List<RincianPendapatanEntity>> saveOrUpdateRincianPendapatan(
//            @RequestBody List<RincianPendapatanEntity> listRincianDpt,
//            @RequestParam List<Integer> listIdRincianDelete,
//            @RequestParam Integer idPendapatan,
//            Principal principal) {
//        if (listIdRincianDelete.isEmpty() || listIdRincianDelete == null) {
//            listIdRincianDelete = new ArrayList<>();
//        }
//        List<RincianPendapatanEntity> value = service.saveOrUpdateRincianPendapatan(listRincianDpt, listIdRincianDelete, idPendapatan, principal);
//        if (!value.isEmpty()) {
//            return new ResponseEntity<>(value, HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("/rincian/save")
    public ResponseEntity<List<RincianPendapatanEntity>> saveOrUpdateRincianPendapatan(
            @RequestBody List<RincianPendapatanEntity> listRincianDpt,
            @RequestParam Integer idPendapatan,
            Principal principal) {
        List<RincianPendapatanEntity> value = service.savOnly(listRincianDpt, idPendapatan, principal);
        if (!value.isEmpty()) {
            return new ResponseEntity<>(value, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/rincian/delete/{idDpt}/{idRincian}")
    public ResponseEntity<Integer> deleteRincian(@PathVariable("idDpt") Integer idDpt, @PathVariable("idRincian") Integer idRincian) {
        Integer deleted = service.deleteRincianPendapatan(idDpt, idRincian);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}
