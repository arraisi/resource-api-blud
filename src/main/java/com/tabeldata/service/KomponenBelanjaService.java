package com.tabeldata.service;

import com.tabeldata.dao.BelanjaLangsungDao;
import com.tabeldata.dao.KomponenBelanjaDao;
import com.tabeldata.dao.KomponenDao;
import com.tabeldata.dao.RbaNoMaxDao;
import com.tabeldata.dto.DataPenggunaLogin;
import com.tabeldata.dto.KomponenBelanjaEditDto;
import com.tabeldata.dto.KomponenBelanjaGetDto;
import com.tabeldata.entity.BelanjaLangsungEntity;
import com.tabeldata.entity.KomponenEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    BelanjaLangsungDao belanjaLangsungDao;

    @Autowired
    DataPenggunaLoginService dataPenggunaLoginService;

    @Autowired
    RbaNoMaxDao rbaNoMaxDao;

    @Transactional
    public List<KomponenBelanjaGetDto> saveBelanja(List<KomponenBelanjaGetDto> komponenList,
                                                   Integer idKegiatan,
                                                   Integer idSkpd,
                                                   String tahunAnggaran,
                                                   String kodeKegiatan,
                                                   String tipeKomponen,
                                                   Principal principal) {

        List<KomponenBelanjaGetDto> listAll = dao.listByParamater(idKegiatan, tahunAnggaran, tipeKomponen);
        listAll.addAll(komponenList);
        Integer noUrut = 0;

        Integer idBelanjaLangsung = rbaNoMaxDao.getIdFromNoMax("TMRBABL");

        for (KomponenBelanjaGetDto komponenBelanja : listAll) {
            noUrut += 1;
            if (komponenBelanja.getId() != null) {

                komponenBelanja.setIdAnggaranNoUrut(noUrut);
                dao.update(komponenBelanja);

            } else {

                Integer id = rbaNoMaxDao.getIdFromNoMax("TMRBABLRINCI");

                KomponenEntity komponen = komponenDao.getById(komponenBelanja.getIdBasKomponen());
                DataPenggunaLogin penggunaLogin = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName()); // Get Id Pengguna By Principal

                komponenBelanja.setId(id);
                komponenBelanja.setIdKegiatan(idKegiatan);
                komponenBelanja.setTahunAnggaran(tahunAnggaran);
                komponenBelanja.setIdAnggaranNoUrut(noUrut);
                komponenBelanja.setIdSkpd(idSkpd);
                komponenBelanja.setKodeKegiatan(kodeKegiatan);

                komponenBelanja.setKodeKomponen(komponen.getKodeKomponen());
                komponenBelanja.setNamaKomponen(komponen.getNamaKomponen());

                komponenBelanja.setSwakelola("1");

                komponenBelanja.setAnggaranDpa(komponenBelanja.getKomponenHarga());
                komponenBelanja.setAnggaranTapd(komponenBelanja.getKomponenHarga());

                komponenBelanja.setIdPenggunaRekam(penggunaLogin.getId());
                komponenBelanja.setTglPenggunaRekam(new Timestamp(System.currentTimeMillis()));

                rbaNoMaxDao.updateIdNoMax(id, "TMRBABLRINCI");
                dao.saveKomponenBelanja(komponenBelanja);
            }

            BelanjaLangsungEntity belanjaLangsung = new BelanjaLangsungEntity();
            belanjaLangsung.setId(idBelanjaLangsung);
            belanjaLangsung.setIdKegiatan(idKegiatan);
            belanjaLangsung.setTahunAnggaran(tahunAnggaran);
            belanjaLangsung.setIdSkpd(idSkpd);

        }

        return dao.listByParamater(idKegiatan, tahunAnggaran, tipeKomponen);
    }

    public List<KomponenBelanjaGetDto> loadKomponen(Integer idKegiatan, String tahunAngg, String tipeKomponen) {
        return dao.listByParamater(idKegiatan, tahunAngg, tipeKomponen);
    }

    public KomponenBelanjaEditDto getById(Integer id) {
        return dao.getKomponenEditById(id);
    }

    public void editVolume(KomponenBelanjaEditDto komponenEdit) {

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
        komponenGet.setEntryAnggaranRinci(komponenEdit.getKeterangan());

        dao.update(komponenGet);
    }


}
