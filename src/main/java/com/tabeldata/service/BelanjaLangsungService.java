package com.tabeldata.service;

import com.tabeldata.dao.BelanjaLangsungDao;
import com.tabeldata.dao.RbaNoMaxDao;
import com.tabeldata.dto.DataPenggunaLogin;
import com.tabeldata.entity.BelanjaLangsungEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Timestamp;

@Service
@Transactional(readOnly = true)
public class BelanjaLangsungService {

    @Autowired
    BelanjaLangsungDao dao;

    @Autowired
    RbaNoMaxDao rbaNoMaxDao;

    @Autowired
    DataPenggunaLoginService dataPenggunaLoginService;

    public void save(BelanjaLangsungEntity belanjaLangsungEntity) {
        dao.saveBelanjaLangsung(belanjaLangsungEntity);
    }

    public void saveByParam(Integer idKegiatan, String tahunAnggaran, Integer idSkpd, String tipeKomponen, Principal principal) {

        DataPenggunaLogin penggunaLogin = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName()); // Get Id Pengguna By Principal

        BigDecimal anggaran = dao.getAnggaranByTipeKomponen(idKegiatan, tahunAnggaran, idSkpd, tipeKomponen);

        Integer id = rbaNoMaxDao.getIdFromNoMax("TMRBABL");
        BelanjaLangsungEntity belanjaLangsung = new BelanjaLangsungEntity();
        belanjaLangsung.setId(id);
        belanjaLangsung.setIdKegiatan(idKegiatan);
        belanjaLangsung.setTahunAnggaran(tahunAnggaran);
        belanjaLangsung.setIdSkpd(idSkpd);
        belanjaLangsung.setIdPenggunaRekam(penggunaLogin.getId());
        belanjaLangsung.setTglPenggunaRekam(new Timestamp(System.currentTimeMillis()));
        rbaNoMaxDao.updateIdNoMax(id, "TMRBABL");

        switch (tipeKomponen) {
            case "pegawai":
                belanjaLangsung.setAnggaranDpaBp(anggaran);
                belanjaLangsung.setAnggaranDpaBbj(new BigDecimal(0));
                belanjaLangsung.setAnggaranDpaBm(new BigDecimal(0));
                belanjaLangsung.setAnggaranTapdBp(anggaran);
                belanjaLangsung.setAnggaranTapdBbj(new BigDecimal(0));
                belanjaLangsung.setAnggaranTapdBm(new BigDecimal(0));
                break;
            case "barang":
                belanjaLangsung.setAnggaranDpaBp(new BigDecimal(0));
                belanjaLangsung.setAnggaranDpaBbj(anggaran);
                belanjaLangsung.setAnggaranDpaBm(new BigDecimal(0));
                belanjaLangsung.setAnggaranTapdBp(new BigDecimal(0));
                belanjaLangsung.setAnggaranTapdBbj(anggaran);
                belanjaLangsung.setAnggaranTapdBm(new BigDecimal(0));
                break;
            case "modal":
                belanjaLangsung.setAnggaranDpaBp(new BigDecimal(0));
                belanjaLangsung.setAnggaranDpaBbj(new BigDecimal(0));
                belanjaLangsung.setAnggaranDpaBm(anggaran);
                belanjaLangsung.setAnggaranTapdBp(new BigDecimal(0));
                belanjaLangsung.setAnggaranTapdBbj(new BigDecimal(0));
                belanjaLangsung.setAnggaranTapdBm(anggaran);
                break;
            default:
                break;
        }

        dao.saveBelanjaLangsung(belanjaLangsung);
    }

    public void updateAnggaran(Integer id, Integer idKegiatan, String tahunAnggaran, Integer idSkpd, String tipeKomponen) {
        dao.updateAnggaran(id, idKegiatan, tahunAnggaran, idSkpd, tipeKomponen);
    }

    public Integer getIdByParam(Integer idKegiatan, String tahunAnggaran, Integer idSkpd) {
        return dao.getIdByParam(idKegiatan, tahunAnggaran, idSkpd);
    }

    public BelanjaLangsungEntity getById(Integer id) {
        return dao.getById(id);
    }

    public BigDecimal getAnggaranByTipeKomponen(Integer idKegiatan, String tahunAnggaran, Integer idSkpd, String tipeKomponen) {
        return dao.getAnggaranByTipeKomponen(idKegiatan, tahunAnggaran, idSkpd, tipeKomponen);
    }

    public BelanjaLangsungEntity getAllAnggaran (Integer id) {
        return dao.getAllAnggaran(id);
    }

}
