package com.tabeldata.service;

import com.tabeldata.dao.KegiatanDao;
import com.tabeldata.dao.RbaNoMaxDao;
import com.tabeldata.dto.DataPenggunaLogin;
import com.tabeldata.dto.KegiatanGetDto;
import com.tabeldata.dto.LoadKegiatanDatatableDto;
import com.tabeldata.entity.KegiatanEntity;
import com.tabeldata.entity.PendapatanDptEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly = true)
public class KegiatanService {

    @Autowired
    private KegiatanDao dao;

    @Autowired
    private RbaNoMaxDao rbaNoMaxDao;

    @Autowired
    private DataPenggunaLoginService dataPenggunaLoginService;

    @Autowired
    private KasService kasService;

    @Autowired
    private PendapatanDptService pendapatanDptService;

    public List<LoadKegiatanDatatableDto> loadKegiatanDatatable(String tahunAnggaran, Integer idSkpd, Principal principal) {
        DataPenggunaLogin dataPenggunaLogin = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName());
        List<LoadKegiatanDatatableDto> data = dao.loadKegiatanDatatable(tahunAnggaran, idSkpd, dataPenggunaLogin.getOtor());
        return data;
    }

    /**
     * Get No Kegiatan By (tahunAnggaran, idProgram, idSkpd)
     */
    public String getNoKegiatan(String tahunAnggaran, Integer idProgram, Integer idSkpd) throws EmptyResultDataAccessException {
        String noKegiatan = dao.getNoKegiatan(tahunAnggaran, idProgram, idSkpd);
        return noKegiatan;
    }

    public KegiatanGetDto saveOrUpdate(KegiatanEntity value, Principal principal) throws DataAccessException {
        KegiatanGetDto kegiatan;
        if (value.getId() == null) {
            kegiatan = saveKegiatan(value, principal);
        } else {
            kegiatan = updateKegiatan(value, principal);
        }
        return kegiatan;
    }

    /**
     * Save Kegiatan
     */
    @Transactional
    public KegiatanGetDto saveKegiatan(KegiatanEntity value, Principal principal) throws DataAccessException {
        DataPenggunaLogin penggunaLogin = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName()); // Get Id Pengguna By Principal
        Integer idKegiatan = rbaNoMaxDao.getIdFromNoMax("TMRBAKEGIATAN"); // get ID from TRRBANOMAX by nama Table "TMRBAKEGIATAN"
        value.setId(idKegiatan);
        value.setIdPenggunaRekam(penggunaLogin.getId());
        value.setTanggalPenggunaRekam(new Timestamp(System.currentTimeMillis()));
        value.setIdSkpd(penggunaLogin.getIdSkpd());
        log.info("KEGIATAN DATA: {}", value);
        dao.saveKegiatan(value);
        rbaNoMaxDao.updateIdNoMax(idKegiatan, "TMRBAKEGIATAN");
        KegiatanGetDto kegiatanEntity = dao.getKegiatanByID(idKegiatan);
        return kegiatanEntity;
    }

    /**
     * Update Kegiatan
     */
    @Transactional
    public KegiatanGetDto updateKegiatan(KegiatanEntity value, Principal principal) throws DataAccessException {
        DataPenggunaLogin penggunaLogin = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName()); // Get Id Pengguna By Principal
        value.setIdPenggunaUbah(penggunaLogin.getId());
        value.setTanggalPenggunaUbah(new Timestamp(System.currentTimeMillis()));
        value.setIdSkpd(penggunaLogin.getIdSkpd());
        dao.updateKegiatan(value);
        KegiatanGetDto kegiatanEntity = dao.getKegiatanByID(value.getId());
        return kegiatanEntity;
    }

    public KegiatanGetDto getKegiatanByID(Integer idKegiatan) throws EmptyResultDataAccessException {
        KegiatanGetDto kegiatan = dao.getKegiatanByID(idKegiatan);
        return kegiatan;
    }

    /**
     * Get Total Anggaran Kegiatan By Id Skpd dan Tahun Anggaran
     */
    public BigDecimal getTotalAnggaranBySkpdIdDanTahunAnggaran(Integer idSkpd, String tahunAnggaran) {
        return dao.getTotalAnggaranBySkpdIdDanTahunAnggaran(idSkpd, tahunAnggaran);
    }

    public Map<String, Object> verifikasiAnggaranSubmitOperator(Integer idSkpd, String tahunAnggaran) throws SQLException {
        Map<String, Object> value = new HashMap<>();

        BigDecimal totalAnggKas = kasService.totalKasAuditedByIdSkpdDanTahunAnggaran(idSkpd, tahunAnggaran);
        log.info("totalKas: {}", totalAnggKas);
        BigDecimal totalAnggPendapatan = pendapatanDptService.getTotalAnggaranPendapatanBySkpdIdDanTahunAnggaran(idSkpd, tahunAnggaran);
        log.info("totalAnggPendapatan: {}", totalAnggPendapatan);
        BigDecimal totalAnggKegiatan = dao.getTotalAnggaranBySkpdIdDanTahunAnggaran(idSkpd, tahunAnggaran);
        log.info("totalAnggKegiatan: {}", totalAnggKegiatan);
        BigDecimal totalKasPendapatan = totalAnggKas.add(totalAnggPendapatan);
        log.info("totalKasPendapatan: {}", totalKasPendapatan);
        BigDecimal sisaAngg = totalKasPendapatan.subtract(totalAnggKegiatan);
        log.info("sisaAngg: {}", sisaAngg);

        value.put("totalKas", totalAnggKas);
        value.put("totalAnggPendapatan", totalAnggPendapatan);
        value.put("totalAnggaranKegiatan", totalAnggKegiatan);
        value.put("totalKasPendapatan", totalKasPendapatan);
        value.put("sisa", sisaAngg);
        return value;
    }

}
