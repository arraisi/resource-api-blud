package com.tabeldata.controller;

import com.tabeldata.entity.UrusanEntity;
import com.tabeldata.service.UrusanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/urusan")
public class UrusanController {

    @Autowired
    private UrusanService service;

    @GetMapping("/list")
    public ResponseEntity<List<UrusanEntity>> getListUrusan() {
        List<UrusanEntity> urusanEntities = service.getListUrusan();
        if (!urusanEntities.isEmpty()) {
            return new ResponseEntity<>(urusanEntities, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }
}
