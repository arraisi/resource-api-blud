package com.tabeldata.service;

import com.tabeldata.dao.KinerjaDao;
import com.tabeldata.dto.LoadKinerjaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class KinerjaService {

    @Autowired
    private KinerjaDao dao;

    public List<LoadKinerjaDto> loadKinerja(String tahunAnggaran, Integer idKegiatan, Integer idSkpd) {
        List<LoadKinerjaDto> data = dao.loadKinerja(tahunAnggaran, idKegiatan, idSkpd);
        return data;
    }
}
