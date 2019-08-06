package com.tabeldata.service;

import com.tabeldata.dao.KegiatanDao;
import com.tabeldata.dto.LoadKegiatanDatatableDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class KegiatanService {

    @Autowired
    private KegiatanDao dao;

    public List<LoadKegiatanDatatableDto> loadKegiatanDatatable(String tahunAnggaran, Integer idSkpd) {
        List<LoadKegiatanDatatableDto> data = dao.loadKegiatanDatatable(tahunAnggaran, idSkpd);
        return data;
    }

    public Integer getNoKegiatan(String tahunAnggaran, Integer idProgram, Integer idSkpd) throws EmptyResultDataAccessException {
        Integer noKegiatan = dao.getNoKegiatan(tahunAnggaran, idProgram, idSkpd);
        return noKegiatan;
    }
}
