package com.tabeldata.service;

import com.tabeldata.dao.LoadKomponenDao;
import com.tabeldata.dto.LoadKomponenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LoadKomponenService {

    @Autowired
    private LoadKomponenDao dao;

    public List<LoadKomponenDto> loadKomponen(String type, String tahunAnggaran, Integer idSkpd) {
        List<LoadKomponenDto> data = dao.loadKomponen(type, tahunAnggaran, idSkpd);
        return data;
    }
}
