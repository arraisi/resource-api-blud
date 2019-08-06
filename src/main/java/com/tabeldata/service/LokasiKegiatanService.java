package com.tabeldata.service;

import com.tabeldata.dao.LokasiKegiatanDao;
import com.tabeldata.dto.LokasiKegiatanDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LokasiKegiatanService {

    @Autowired
    private LokasiKegiatanDao dao;


    public List<LokasiKegiatanDto> getListLokasiKegiatan() {
        List<LokasiKegiatanDto> data = dao.listLokasiKegiatan();
        return data;
    }
}
