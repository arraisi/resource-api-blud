package com.tabeldata.service;

import com.tabeldata.dao.*;
import com.tabeldata.dto.DataPenggunaLogin;
import com.tabeldata.dto.KegiatanGetDto;
import com.tabeldata.dto.KomponenBelanjaEditDto;
import com.tabeldata.dto.KomponenBelanjaGetDto;
import com.tabeldata.entity.KomponenEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class KomponenBelanjaService {

    @Autowired
    KomponenBelanjaDao dao;

    @Autowired
    KomponenDao komponenDao;

    @Autowired
    KegiatanDao kegiatanDao;

    @Autowired
    BelanjaLangsungService belanjaLangsungService;

    @Autowired
    DataPenggunaLoginService dataPenggunaLoginService;

    @Autowired
    RbaNoMaxDao rbaNoMaxDao;

    @Transactional
    public List<KomponenBelanjaGetDto> saveBelanja(List<KomponenBelanjaGetDto> komponenList,
                                                   Integer idKegiatan,
                                                   Integer idSkpd,
                                                   String tahunAnggaran,
                                                   String tipeKomponen,
                                                   Principal principal) {

        List<KomponenBelanjaGetDto> listAll = dao.listByParamater(idKegiatan, tahunAnggaran, tipeKomponen);
        listAll.addAll(komponenList);
        Integer noUrut = 0;

//        BigDecimal anggaran = belanjaLangsungService.getAnggaranByTipeKomponen(idKegiatan, tahunAnggaran, idSkpd, tipeKomponen);

        for (KomponenBelanjaGetDto komponenBelanja : listAll) {
            noUrut += 1;
            if (komponenBelanja.getId() != null) {

                komponenBelanja.setIdAnggaranNoUrut(noUrut);
                dao.update(komponenBelanja);

            } else {

                Integer id = rbaNoMaxDao.getIdFromNoMax("TMRBABLRINCI");

                KomponenEntity komponen = komponenDao.getById(komponenBelanja.getIdBasKomponen());
                KegiatanGetDto kegiatan = kegiatanDao.getKegiatanByID(idKegiatan);
                DataPenggunaLogin penggunaLogin = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName()); // Get Id Pengguna By Principal

                komponenBelanja.setId(id);

                komponenBelanja.setTahunAnggaran(tahunAnggaran);
                komponenBelanja.setIdAnggaranNoUrut(noUrut);
                komponenBelanja.setIdSkpd(idSkpd);

                komponenBelanja.setIdKegiatan(idKegiatan);
                komponenBelanja.setKodeKegiatan(kegiatan.getKodeKegiatan());


                komponenBelanja.setKodeKomponen(komponen.getKodeKomponen());
                komponenBelanja.setNamaKomponen(komponen.getNamaKomponen());

                komponenBelanja.setSwakelola("1");

                komponenBelanja.setAnggaranDpa(komponenBelanja.getKomponenHarga());
                komponenBelanja.setAnggaranTapd(komponenBelanja.getKomponenHarga());

                komponenBelanja.setIdPenggunaRekam(penggunaLogin.getId());
                komponenBelanja.setTglPenggunaRekam(new Timestamp(System.currentTimeMillis()));

                rbaNoMaxDao.updateIdNoMax(id, "TMRBABLRINCI");
//                anggaran = anggaran.add(komponenBelanja.getKomponenHarga());
                dao.saveKomponenBelanja(komponenBelanja);
            }

            Integer idBelanjaLangsung = belanjaLangsungService.getIdByParam(idKegiatan, tahunAnggaran, idSkpd);
            if (idBelanjaLangsung == null) {
                belanjaLangsungService.saveByParam(idKegiatan, tahunAnggaran, idSkpd, tipeKomponen, principal);
            } else {
                belanjaLangsungService.updateAnggaran(idBelanjaLangsung, idKegiatan, tahunAnggaran, idSkpd, tipeKomponen);
            }

        }

        return dao.listByParamater(idKegiatan, tahunAnggaran, tipeKomponen);
    }

    public List<KomponenBelanjaGetDto> loadKomponen(Integer idKegiatan, String tahunAngg, String tipeKomponen) {
        return dao.listByParamater(idKegiatan, tahunAngg, tipeKomponen);
    }

    public KomponenBelanjaEditDto getById(Integer id) {
        return dao.getKomponenEditById(id);
    }

    @Transactional
    public void editVolume(KomponenBelanjaEditDto komponenEdit, String tipeKomponen) {

        KomponenBelanjaGetDto komponenGet = dao.getKomponenGetById(komponenEdit.getId());
        komponenGet.setAnggaranDpa(komponenEdit.getTotalHarga());
        komponenGet.setAnggaranTapd(komponenEdit.getTotalHarga());
        komponenGet.setVolume(komponenEdit.getVolume());
        komponenGet.setKoefisien(komponenEdit.getKoefisien());
        komponenGet.setVolume1(komponenEdit.getVolume1());
        komponenGet.setSatuan1(komponenEdit.getSatuan1());
        komponenGet.setVolume2(komponenEdit.getVolume2());
        komponenGet.setSatuan2(komponenEdit.getSatuan2());
        komponenGet.setVolume3(komponenEdit.getVolume3());
        komponenGet.setSatuan3(komponenEdit.getSatuan3());
        komponenGet.setVolume4(komponenEdit.getVolume4());
        komponenGet.setSatuan4(komponenEdit.getSatuan4());
        komponenGet.setPajak(komponenEdit.getPajak());
        komponenGet.setEntryAnggaranRinci(komponenEdit.getKeterangan());

        dao.update(komponenGet);
        Integer idBelanjaLangsung = belanjaLangsungService.getIdByParam(komponenGet.getIdKegiatan(), komponenGet.getTahunAnggaran(), komponenGet.getIdSkpd());

        belanjaLangsungService.updateAnggaran(idBelanjaLangsung, komponenGet.getIdKegiatan(), komponenGet.getTahunAnggaran(), komponenGet.getIdSkpd(), tipeKomponen);
    }

    public KomponenBelanjaGetDto getKomponenBelanjaById(Integer id) {
        return dao.getKomponenGetById(id);
    }

    @Transactional
    public int updateRpaKomponen(KomponenBelanjaGetDto data, Principal principal) throws DataAccessException {
        DataPenggunaLogin penggunaLogin = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName()); // Get Id Pengguna By Principal
        return dao.updateRinci(data, penggunaLogin.getId(), new Timestamp(System.currentTimeMillis()));
    }

    @Transactional
    public void updateAnggaranKegiatan(Integer id, BigDecimal anggaran) {
        dao.updateAnggaranKegiatan(id, anggaran);
    }

    @Transactional
    public List<KomponenBelanjaGetDto> deleteKomponenFromRinci(Integer idKomponen, Integer idKegiatan, String tahunAnggaran, Integer idSkpd, String tipeKomponen) {
        dao.deleteKomponenFromRinci(idKomponen);
        List<KomponenBelanjaGetDto> komponenGetList = dao.listByParamater(idKegiatan, tahunAnggaran, tipeKomponen);
        Integer noUrut = 0;
        for (KomponenBelanjaGetDto komponen : komponenGetList) {
            noUrut += 1;
            komponen.setIdAnggaranNoUrut(noUrut);
            dao.update(komponen);
        }

        Integer idBelanjaLangsung = belanjaLangsungService.getIdByParam(idKegiatan, tahunAnggaran, idSkpd);
        belanjaLangsungService.updateAnggaran(idBelanjaLangsung, idKegiatan, tahunAnggaran, idSkpd, tipeKomponen);

        return dao.listByParamater(idKegiatan, tahunAnggaran, tipeKomponen);
    }

}
