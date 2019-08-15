package com.tabeldata.service;

import com.tabeldata.dao.KomponenBelanjaPegawaiDao;
import com.tabeldata.dao.KomponenDao;
import com.tabeldata.dao.RbaNoMaxDao;
import com.tabeldata.dto.DataPenggunaLogin;
import com.tabeldata.entity.KomponenBelanjaEntity;
import com.tabeldata.entity.KomponenEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class KomponenBelanjaService {

    @Autowired
    KomponenBelanjaPegawaiDao dao;

    @Autowired
    KomponenDao komponenDao;

    @Autowired
    DataPenggunaLoginService dataPenggunaLoginService;

    @Autowired
    RbaNoMaxDao rbaNoMaxDao;

    @Transactional
    public List<KomponenBelanjaEntity> saveBelanjaPegawai(List<KomponenBelanjaEntity> komponenList,
                                                          Integer idKegiatan,
                                                          Integer idSkpd,
                                                          String tahunAnggaran,
                                                          String kodeKegiatan,
                                                          Principal principal) {


        List<Integer> ids = new ArrayList<>();
        for (KomponenBelanjaEntity komponenBelanja : komponenList) {
            Integer id = rbaNoMaxDao.getIdFromNoMax("TMRBABLRINCI");
            ids.add(id);
            Integer noUrut = dao.getNoUrut(idKegiatan, tahunAnggaran, komponenBelanja.getIdBas());

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

            dao.updateNoUrut(noUrut, id);
            rbaNoMaxDao.updateIdNoMax(id, "TMRBABLRINCI");
            dao.saveKomponenBelanjaPegawai(komponenBelanja);
        }

        return dao.listByParamater(ids);
    }


}
