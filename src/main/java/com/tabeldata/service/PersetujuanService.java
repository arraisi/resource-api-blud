package com.tabeldata.service;

import com.tabeldata.dao.PersetujuanDao;
import com.tabeldata.dto.DataPenggunaLogin;
import com.tabeldata.dto.LoadKegiatanDatatableDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PersetujuanService {

    @Autowired
    private PersetujuanDao dao;

    @Autowired
    private KegiatanService kegiatanService;

    @Autowired
    private DataPenggunaLoginService dataPenggunaLoginService;

    Timestamp tanggalSekarang = new Timestamp(System.currentTimeMillis());

    String statusBaru = "0";
    String statusDikirim = "1";
    String statusDiterima = "2";
    String statusDitolak = "3";
    String statusRevisi = "4";

    /**
     * Update TMRBA Ketika Submit Operator
     */
    @Transactional
    public List<LoadKegiatanDatatableDto> submitKegiatanOperator(String tahunAnggaran, Principal principal) throws SQLException {
        DataPenggunaLogin pengguna = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName());
        dao.updateKegiatanSubmitOperatorBaru(pengguna.getIdSkpd(), tahunAnggaran);
        dao.updateKegiatanSubmitOperatorRevisi(pengguna.getIdSkpd(), tahunAnggaran);
        dao.updateTmrbaSubmitOperator(pengguna.getId(), tanggalSekarang, pengguna.getIdSkpd(), tahunAnggaran);
        List<LoadKegiatanDatatableDto> data = kegiatanService.loadKegiatanDatatable(tahunAnggaran, pengguna.getIdSkpd(), principal);
        return data;
    }

    /**
     * Terima / Setujui Kegiatan Oleh Pejabat Bendahara Keuangan
     */
    @Transactional
    public List<LoadKegiatanDatatableDto> terimaKegiatanBendahara(String tahunAnggaran, Integer kegiatanId, Principal principal) throws SQLException {
        DataPenggunaLogin pengguna = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName());
        dao.updateKegiatanDiterimaBendahara(pengguna.getIdSkpd(), tahunAnggaran, kegiatanId, pengguna.getId(), tanggalSekarang);
        List<LoadKegiatanDatatableDto> data = kegiatanService.loadKegiatanDatatable(tahunAnggaran, pengguna.getIdSkpd(), principal);
        return data;
    }

    /**
     * Tolak  Kegiatan Oleh Pejabat Bendahara Keuangan
     */
    @Transactional
    public List<LoadKegiatanDatatableDto> tolakKegiatanBendahara(String tahunAnggaran, Integer kegiatanId, String catatan, Principal principal) throws SQLException {
        DataPenggunaLogin pengguna = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName());
        dao.updateKegiatanDitolakBendahara(pengguna.getIdSkpd(), tahunAnggaran, kegiatanId, catatan);
        List<LoadKegiatanDatatableDto> data = kegiatanService.loadKegiatanDatatable(tahunAnggaran, pengguna.getIdSkpd(), principal);
        return data;
    }

    /**
     * Kirim  Kegiatan Oleh Pejabat Bendahara Keuangan Ke Kepala SKPD
     */
    @Transactional
    public List<LoadKegiatanDatatableDto> kirimKegiatanOlehBendahara(String tahunAnggaran, Principal principal) throws SQLException {
        DataPenggunaLogin pengguna = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName());
        Map<String, Object> statusKepalaDanDinas = dao.checkStatusAppvKepalaDanDinas(pengguna.getIdSkpd(), tahunAnggaran);
        if (statusKepalaDanDinas.get("statusAppvKepala") == null) {
            dao.updateTmrbaDiterimaSemuaKegiatanBendahara(pengguna.getId(), tanggalSekarang, pengguna.getIdSkpd(), tahunAnggaran, statusDikirim);
        } else {
            dao.updateTmrbaDiterimaSemuaKegiatanBendahara(pengguna.getId(), tanggalSekarang, pengguna.getIdSkpd(), tahunAnggaran, statusRevisi);
        }
        List<LoadKegiatanDatatableDto> data = kegiatanService.loadKegiatanDatatable(tahunAnggaran, pengguna.getIdSkpd(), principal);
        return data;
    }

    /**
     * Check Jumlah Kegiatan Yang Belum Di Setujui
     */
    public Long checkKegiatanBelumDisetujui(String tahunAnggaran, Principal principal) throws DataAccessException {
        DataPenggunaLogin pengguna = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName());
        return dao.checkKegiatanBelumDisetujui(pengguna.getIdSkpd(), tahunAnggaran);
    }

    /**
     * Check Status Approval Kepala SKPD Dan Dinas Teknis
     */
    public Map<String, Object> checkStatusAppvKepalaDanDinas(String tahunAnggaran, Integer skpdId, Principal principal) throws DataAccessException {
        DataPenggunaLogin pengguna = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName());
        return dao.checkStatusAppvKepalaDanDinas(skpdId, tahunAnggaran);
    }

    /**
     * Terima Kegiatan Oleh KEPALA SKPD
     */
    @Transactional
    public List<LoadKegiatanDatatableDto> terimaKegiatanOlehKepalaSKPD(String tahunAnggaran, Principal principal) throws SQLException {
        DataPenggunaLogin pengguna = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName());
        Map<String, Object> statusKepalaDanDinas = dao.checkStatusAppvKepalaDanDinas(pengguna.getIdSkpd(), tahunAnggaran);
        if (statusKepalaDanDinas.get("statusAppvDinas") == null) {
            dao.updateTmrbaDiterimaKepalaSkpd(pengguna.getId(), tanggalSekarang, pengguna.getIdSkpd(), tahunAnggaran, statusDiterima, statusDikirim);
        } else {
            dao.updateTmrbaDiterimaKepalaSkpd(pengguna.getId(), tanggalSekarang, pengguna.getIdSkpd(), tahunAnggaran, statusDiterima, statusRevisi);
        }
        List<LoadKegiatanDatatableDto> data = kegiatanService.loadKegiatanDatatable(tahunAnggaran, pengguna.getIdSkpd(), principal);
        return data;
    }

    /**
     * Tolak Kegiatan Oleh KEPALA SKPD
     */
    @Transactional
    public List<LoadKegiatanDatatableDto> tolakKegiatanOlehKepalaSKPD(String tahunAnggaran, String catatan, Principal principal) throws SQLException {
        DataPenggunaLogin pengguna = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName());
        dao.updateTmrbaDitolakKepalaSkpd(pengguna.getId(), tanggalSekarang, pengguna.getIdSkpd(), tahunAnggaran, catatan);
        List<LoadKegiatanDatatableDto> data = kegiatanService.loadKegiatanDatatable(tahunAnggaran, pengguna.getIdSkpd(), principal);
        return data;
    }

    /**
     * Terima Kegiatan Oleh Dinas Teknis
     */
    @Transactional
    public List<LoadKegiatanDatatableDto> terimaKegiatanOlehDinasTeknis(Integer skpdId, String tahunAnggaran, Principal principal) throws SQLException {
        DataPenggunaLogin pengguna = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName());
        dao.updateTmrbaDiterimaDinasTeknis(pengguna.getId(), tanggalSekarang, skpdId, tahunAnggaran);
        List<LoadKegiatanDatatableDto> data = kegiatanService.loadKegiatanDatatable(tahunAnggaran, skpdId, principal);
        return data;
    }

    /**
     * Tolak Kegiatan Oleh Dinas Teknis
     */
    @Transactional
    public List<LoadKegiatanDatatableDto> tolakKegiatanOlehDinasTeknis(Integer skpdId, String tahunAnggaran, String catatan, Principal principal) throws SQLException {
        DataPenggunaLogin pengguna = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName());
        dao.updateTmrbaDitolakDinasTeknis(pengguna.getId(), tanggalSekarang, skpdId, tahunAnggaran, catatan);
        List<LoadKegiatanDatatableDto> data = kegiatanService.loadKegiatanDatatable(tahunAnggaran, skpdId, principal);
        return data;
    }
}
