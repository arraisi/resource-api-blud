package com.tabeldata.service;

import com.tabeldata.dao.KegiatanDao;
import com.tabeldata.dao.RbaNoMaxDao;
import com.tabeldata.dto.DataPenggunaLogin;
import com.tabeldata.dto.LoadKegiatanDatatableDto;
import com.tabeldata.entity.KegiatanEntity;
import com.tabeldata.entity.PendapatanDptEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
public class KegiatanService {

    @Autowired
    private KegiatanDao dao;

    @Autowired
    private RbaNoMaxDao rbaNoMaxDao;

    @Autowired
    private DataPenggunaLoginService dataPenggunaLoginService;

    public List<LoadKegiatanDatatableDto> loadKegiatanDatatable(String tahunAnggaran, Integer idSkpd) {
        List<LoadKegiatanDatatableDto> data = dao.loadKegiatanDatatable(tahunAnggaran, idSkpd);
        return data;
    }

    /**
     * Get No Kegiatan By (tahunAnggaran, idProgram, idSkpd)
     */
    public String getNoKegiatan(String tahunAnggaran, Integer idProgram, Integer idSkpd) throws EmptyResultDataAccessException {
        String noKegiatan = dao.getNoKegiatan(tahunAnggaran, idProgram, idSkpd);
        return noKegiatan;
    }

    public KegiatanEntity saveKegiatan(KegiatanEntity value, Principal principal) {
        DataPenggunaLogin penggunaLogin = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName()); // Get Id Pengguna By Principal
        Integer idKegiatan = rbaNoMaxDao.getIdFromNoMax("TMRBAKEGIATAN"); // get ID from TRRBANOMAX by nama Table "TMRBAKEGIATAN"
        value.setId(idKegiatan);
        value.setIdPenggunaRekam(penggunaLogin.getId());
        value.setTanggalPenggunaRekam(new Timestamp(System.currentTimeMillis()));
        dao.saveKegiatan(value);
        rbaNoMaxDao.updateIdNoMax(idKegiatan, "TMRBAKEGIATAN");
        KegiatanEntity kegiatanEntity = dao.getKegiatanByID(idKegiatan);
        return kegiatanEntity;
    }
}
