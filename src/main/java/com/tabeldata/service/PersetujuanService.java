package com.tabeldata.service;

import com.tabeldata.dao.PersetujuanDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Timestamp;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PersetujuanService {

    @Autowired
    private PersetujuanDao dao;

    /**
     * Update TMRBA Ketika Submit Operator
     */
    public void UpdateTmrbaSubmitOperator(Integer idOperator, Timestamp tanggalSubmitOperator, Integer idSkpd, String tahunAnggaran) throws SQLException {

    }

    /**
     * Update TMRBAKEGIATAN Ketika Submit Operator
     */
    public void UpdateKegiatanSubmitOperatorBaru(Integer idSkpd, String tahunAnggaran) throws SQLException {

    }

    /**
     * Update TMRBAKEGIATAN Ketika Revisi Operator
     */
    public void UpdateKegiatanSubmitOperatorRevisi(Integer idSkpd, String tahunAnggaran) throws SQLException {

    }

    /**
     * Update TMRBA Ketika Sudah Diterima Semua Kegiatan Oleh Pejabat Bendahara Keuangan
     */
    public void UpdateTmrbaDiterimaSemuaKegiatanBendahara(Integer idBendahara, Timestamp tanggalDiterimaBendahara, Integer idSkpd, String tahunAnggaran) throws SQLException {

    }

    /**
     * Update TMRBAKEGIATAN Ketika Diterima Oleh Pejabat Bendahara Keuangan
     */
    public void UpdateKegiatanDiterimaBendahara(Integer idSkpd, String tahunAnggaran, Integer idKegiatan) throws SQLException {

    }

    /**
     * Update TMRBAKEGIATAN Ketika Ditolak Oleh Pejabat Bendahara Keuangan
     */
    public void UpdateKegiatanDitolakBendahara(Integer idSkpd, String tahunAnggaran, Integer idKegiatan) throws SQLException {

    }
}
