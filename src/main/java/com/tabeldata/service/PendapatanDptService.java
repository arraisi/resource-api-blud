package com.tabeldata.service;

import com.tabeldata.dao.PendapatanDptDao;
import com.tabeldata.dao.RbaNoMaxDao;
import com.tabeldata.dto.DataPenggunaLogin;
import com.tabeldata.entity.LoadDptEntity;
import com.tabeldata.entity.PendapatanDptEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.sql.Timestamp;

@Service
@Transactional(readOnly = true)
public class PendapatanDptService {

    @Autowired
    private PendapatanDptDao dao;

    @Autowired
    private RbaNoMaxDao rbaNoMaxDao;

    @Autowired
    private DataPenggunaLoginService dataPenggunaLoginService;

    /**
     * Method untuk save data Pendapatan
     */
    public PendapatanDptEntity savePendapatan(LoadDptEntity loadDptEntity, Principal principal) throws EmptyResultDataAccessException {
        DataPenggunaLogin penggunaLogin = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName());
        Integer idPendapatanDpt = rbaNoMaxDao.getIdFromNoMax("TMRBADPT");
        PendapatanDptEntity value = pendapatanMapperLocal(loadDptEntity);
        value.setId(idPendapatanDpt);
        value.setIdRekamPengguna(penggunaLogin.getId());
        value.setTanggalRekamPengguna(new Timestamp(System.currentTimeMillis()));
        dao.savePendapatan(value);
        rbaNoMaxDao.updateIdNoMax(idPendapatanDpt, "TMRBADPT");
        PendapatanDptEntity pendapatanDptEntity = dao.getPendapatanDpt(idPendapatanDpt);
        return pendapatanDptEntity;
    }

    /**
     * Method untuk update Pendapatan from Rincian Pendapatan
     */
    public PendapatanDptEntity updateAnggaranPendapatanFromRincian(PendapatanDptEntity value, Principal principal) {
        DataPenggunaLogin penggunaLogin = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName());
        value.setIdUbahPengguna(penggunaLogin.getId());
        value.setTanggalUbahPengguna(new Timestamp(System.currentTimeMillis()));
        dao.updateAllDataPendapatanById(value);
        PendapatanDptEntity pendapatanDptEntity = dao.getPendapatanDpt(value.getId());
        return pendapatanDptEntity;
    }

    /**
     * Method untuk update Semua Pendapatan By AKB
     */
    public PendapatanDptEntity updateAkbPendapatan(PendapatanDptEntity pendapatan, Principal principal) {
        DataPenggunaLogin penggunaLogin = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName());
        PendapatanDptEntity value = pendapatanAkbMapperLocal(pendapatan);
        value.setIdUbahPengguna(penggunaLogin.getId());
        value.setTanggalUbahPengguna(new Timestamp(System.currentTimeMillis()));
        dao.updateAllDataPendapatanById(value);
        PendapatanDptEntity pendapatanDptEntity = dao.getPendapatanDpt(value.getId());
        return pendapatanDptEntity;
    }

    /**
     * Method get pendapatan By ID Pendapatan
     */
    public PendapatanDptEntity getPendapatanById(Integer idPendapatanDpt) {
        return dao.getPendapatanDpt(idPendapatanDpt);
    }


    /**
     * Method Untuk Local Mapper
     */
    private PendapatanDptEntity pendapatanMapperLocal(LoadDptEntity loadDptEntity) {
        PendapatanDptEntity value = new PendapatanDptEntity();
        value.setId(loadDptEntity.getIdTrx());
        value.setTahunAnggaran(loadDptEntity.getTahunAnggaran());
        value.setIdSkpd(loadDptEntity.getIdSkpd());
        value.setIdBas(loadDptEntity.getIdBas());
        value.setAnggaranDpa(loadDptEntity.getAnggaranDpa());
        value.setAnggaranTapd(loadDptEntity.getAnggaranTapd());
        return value;
    }

    /**
     * Method Untuk Local Mapper AKB
     */
    private PendapatanDptEntity pendapatanAkbMapperLocal(PendapatanDptEntity p) {
        PendapatanDptEntity value = getPendapatanById(p.getId());
        value.setAnggaranDpa(p.getAnggaranDpa());
        value.setAnggaranTapd(p.getAnggaranTapd());
        value.setJenis(p.getJenis());
        value.setRpaBulan1(p.getRpaBulan1());
        value.setRpaBulan2(p.getRpaBulan2());
        value.setRpaBulan3(p.getRpaBulan3());
        value.setRpaBulan4(p.getRpaBulan4());
        value.setRpaBulan5(p.getRpaBulan5());
        value.setRpaBulan6(p.getRpaBulan6());
        value.setRpaBulan7(p.getRpaBulan7());
        value.setRpaBulan8(p.getRpaBulan8());
        value.setRpaBulan9(p.getRpaBulan9());
        value.setRpaBulan10(p.getRpaBulan10());
        value.setRpaBulan11(p.getRpaBulan11());
        value.setRpaBulan12(p.getRpaBulan12());
        return value;
    }
}
