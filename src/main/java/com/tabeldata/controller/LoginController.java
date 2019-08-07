package com.tabeldata.controller;

import com.tabeldata.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService service;

    @GetMapping("/get-tahun-anggaran/{nrk}")
    public ResponseEntity<List<String>> getTahunAnggaranByNrk(@PathVariable String nrk) {

        try {
            List<String> list = service.getTahunAnggaranByNrk(nrk);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

}
