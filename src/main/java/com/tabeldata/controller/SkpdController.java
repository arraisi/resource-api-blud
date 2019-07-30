package com.tabeldata.controller;

import com.tabeldata.entity.SkpdEntity;
import com.tabeldata.service.SkpdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/skpd")
public class SkpdController {

    @Autowired
    private SkpdService service;

    @GetMapping("/{skpdId}")
    public ResponseEntity<SkpdEntity> getSkpdById(@PathVariable Integer skpdId) {
        try {
            SkpdEntity valuSkpdEntity = service.getSkpdById(skpdId);
            return new ResponseEntity<>(valuSkpdEntity, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
