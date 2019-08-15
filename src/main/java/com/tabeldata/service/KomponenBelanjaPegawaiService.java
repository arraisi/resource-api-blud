package com.tabeldata.service;

import com.tabeldata.dao.KomponenBelanjaPegawaiDao;
import com.tabeldata.dao.KomponenDao;
import com.tabeldata.dao.RbaNoMaxDao;
import com.tabeldata.dto.DataPenggunaLogin;
import com.tabeldata.entity.KomponenBelanjaPegawaiEntity;
import com.tabeldata.entity.KomponenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class KomponenBelanjaPegawaiService {

    @Autowired
    KomponenBelanjaPegawaiDao dao;

    @Autowired
    KomponenDao komponenDao;

    @Autowired
    DataPenggunaLoginService dataPenggunaLoginService;

    @Autowired
    RbaNoMaxDao rbaNoMaxDao;

    @Transactional
    public void  saveBelanjaPegawai(List<KomponenBelanjaPegawaiEntity> komponenList, Integer idKegiatan, Integer idSkpd, String tahunAnggaran, String kodeKegiatan, Principal principal) {
        List<KomponenBelanjaPegawaiEntity> listKomponenBelanja = new ArrayList<>();
        for (KomponenBelanjaPegawaiEntity komponenBelanja : komponenList) {

            Integer noUrut = this.getNoUrut(idKegiatan, tahunAnggaran, komponenBelanja.getIdBas()) + 1;
            this.updateNoUrut(noUrut, idKegiatan, tahunAnggaran, komponenBelanja.getIdBas());

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

            komponenBelanja.setAnggaranDpa(new BigDecimal(900000));
            komponenBelanja.setAnggaranTapd(new BigDecimal(900000));

            komponenBelanja.setIdPenggunaRekam(penggunaLogin.getId());
            komponenBelanja.setTglPenggunaRekam(new Timestamp(System.currentTimeMillis()));

            rbaNoMaxDao.updateIdNoMax(id, "TMRBABLRINCI");
            listKomponenBelanja.add(komponenBelanja);
        }

        dao.saveKomponenBelanjaPegawai(listKomponenBelanja);
    }

    public Integer getNoUrut(Integer idKegiatan, String tahunAnggaran, Integer idBas) {
        return dao.getNoUrut(idKegiatan, tahunAnggaran, idBas);
    }

    @Transactional
    public void updateNoUrut(Integer updateId, Integer idKegiatan, String tahunAnggaran, Integer idBas) {

        dao.updateNoUrut(updateId, idKegiatan, tahunAnggaran, idBas);

    }


}
