package com.tabeldata.service;

import com.tabeldata.dao.RbaNoMaxDao;
import com.tabeldata.dao.RicianPendapatanDao;
import com.tabeldata.dto.DataPenggunaLogin;
import com.tabeldata.entity.PendapatanDptEntity;
import com.tabeldata.entity.RincianPendapatanEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RincianPendapatanService {

    @Autowired
    private RicianPendapatanDao dao;

    @Autowired
    private RbaNoMaxDao rbaNoMaxDao;

    @Autowired
    private DataPenggunaLoginService dataPenggunaLoginService;

    @Autowired
    private PendapatanDptService pendapatanDptService;

    public List<RincianPendapatanEntity> getListRincianPendapatanBydptId(Integer idDpt) {
        List<RincianPendapatanEntity> value = dao.getRincianPendapatanByDptId(idDpt);
        return value;
    }

    /**
     * Method Untuk Save Ataupun Update Rincian Pendapatan Berdasarkan ID == null (Save) Atau ID != null (Update)
     */
    public List<RincianPendapatanEntity> saveOrUpdateRincianPendapatan(List<RincianPendapatanEntity> listRincian, List<Integer> listIdRincianDelete, Integer idPendapatan, Principal principal) {
        PendapatanDptEntity pendapatan = pendapatanDptService.getPendapatanById(idPendapatan);
        DataPenggunaLogin penggunaLogin = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName());
        BigDecimal anggaran = new BigDecimal(0.0); // Inisialisasi 0 Variable untuk menampung total Anggaran
        for (RincianPendapatanEntity data : listRincian) {
            if (data.getId() == null) {
                RincianPendapatanEntity value = rincianPendapatanLocalMapper(data); // Mapper For Set Value DPA = TAPD
                Integer idRincianPendapatan = rbaNoMaxDao.getIdFromNoMax("TMRBADPTRINCI");
                value.setId(idRincianPendapatan); // Set Id Rincian Get From Table TRRBANOMAX
                value.setIdRekamPengguna(penggunaLogin.getId()); // Set Id Pengguna Rekam (CreatedUser)
                value.setTanggalRekamPengguna(new Timestamp(System.currentTimeMillis())); // Set Tanggal Rekam (CreatedDate)
                value.setIdBas(pendapatan.getIdBas());
                value.setIdSkpd(pendapatan.getIdSkpd());
                value.setIdDpt(pendapatan.getId());
                value.setTahunAnggaran(pendapatan.getTahunAnggaran());
                dao.saveRincianPendapatan(value);
                rbaNoMaxDao.updateIdNoMax(idRincianPendapatan, "TMRBADPTRINCI");
            } else {
                RincianPendapatanEntity value = rincianPendapatanLocalMapper(data); // Mapper For Set Value DPA = TAPD
                value.setIdUbahPengguna(penggunaLogin.getId()); // Set Id Pengguna Ubah (CreatedUser)
                value.setTanggalUbahPengguna(new Timestamp(System.currentTimeMillis())); // Set Tanggal Ubah (CreatedDate)
                value.setIdBas(pendapatan.getIdBas());
                value.setIdSkpd(pendapatan.getIdSkpd());
                value.setIdDpt(pendapatan.getId());
                value.setTahunAnggaran(pendapatan.getTahunAnggaran());
                dao.updateRincianPendapatanByIdDanIdDpt(value);
            }
            anggaran = anggaran.add(data.getAnggaranTapd()); // Value Anggaran Dijumlahkan dari semua Total Anggaran di TAPD / DPA
        }
        pendapatan.setAnggaranTapd(anggaran); // Anggaran TAPD pada Pendapatan di Update sesusai Jumlah semua anggaran di Rincian
        pendapatan.setAnggaranDpa(anggaran); // Anggaran DPA pada Pendapatan di Update sesusai Jumlah semua anggaran di Rincian
        if (!listIdRincianDelete.isEmpty()) {
            for (Integer i : listIdRincianDelete) {
                dao.deleteRincianPendapatanByIdDptAndIdRincian(i, idPendapatan);
            }
        }
        pendapatanDptService.updatePendapatan(pendapatan, principal); // Update Anggaran Pendapatan
        List<RincianPendapatanEntity> listRincianDpt = dao.getRincianPendapatanByDptId(idPendapatan); // Get Rincian Pendapatan
        return listRincianDpt;
    }

    /**
     * Method Untuk Save Ataupun Update Rincian Pendapatan Berdasarkan ID == null (Save) Atau ID != null (Update)
     */
    public List<RincianPendapatanEntity> savOnly(List<RincianPendapatanEntity> listRincian, Integer idPendapatan, Principal principal) {
        PendapatanDptEntity pendapatan = pendapatanDptService.getPendapatanById(idPendapatan);
        DataPenggunaLogin penggunaLogin = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName());
        BigDecimal anggaran = new BigDecimal(0.0); // Inisialisasi 0 Variable untuk menampung total Anggaran
        dao.deleteRincianPendapatanByIdDpt(idPendapatan);
        for (RincianPendapatanEntity data : listRincian) {
            if (data.getId() == null) {
                log.info("data Rincian Insert: {}", data);
                RincianPendapatanEntity value = rincianPendapatanLocalMapper(data); // Mapper For Set Value DPA = TAPD
                Integer idRincianPendapatan = rbaNoMaxDao.getIdFromNoMax("TMRBADPTRINCI");
                value.setId(idRincianPendapatan); // Set Id Rincian Get From Table TRRBANOMAX
                value.setIdRekamPengguna(penggunaLogin.getId()); // Set Id Pengguna Rekam (CreatedUser)
                value.setTanggalRekamPengguna(new Timestamp(System.currentTimeMillis())); // Set Tanggal Rekam (CreatedDate)
                value.setIdBas(pendapatan.getIdBas());
                value.setIdSkpd(pendapatan.getIdSkpd());
                value.setIdDpt(pendapatan.getId());
                value.setTahunAnggaran(pendapatan.getTahunAnggaran());
                dao.saveRincianPendapatan(value);
                rbaNoMaxDao.updateIdNoMax(idRincianPendapatan, "TMRBADPTRINCI");
            } else {
                log.info("data Rincian Update: {}", data);
                RincianPendapatanEntity value = rincianPendapatanLocalMapper(data); // Mapper For Set Value DPA = TAPD
                value.setIdUbahPengguna(penggunaLogin.getId()); // Set Id Pengguna Ubah (CreatedUser)
                value.setTanggalUbahPengguna(new Timestamp(System.currentTimeMillis())); // Set Tanggal Ubah (CreatedDate)
                value.setIdBas(pendapatan.getIdBas());
                value.setIdSkpd(pendapatan.getIdSkpd());
                value.setIdDpt(pendapatan.getId());
                value.setTahunAnggaran(pendapatan.getTahunAnggaran());
                dao.saveRincianPendapatan(value);
            }
            anggaran = anggaran.add(data.getAnggaranTapd()); // Value Anggaran Dijumlahkan dari semua Total Anggaran di TAPD / DPA
        }
        pendapatan.setAnggaranTapd(anggaran); // Anggaran TAPD pada Pendapatan di Update sesusai Jumlah semua anggaran di Rincian
        pendapatan.setAnggaranDpa(anggaran); // Anggaran DPA pada Pendapatan di Update sesusai Jumlah semua anggaran di Rincian
        pendapatanDptService.updatePendapatan(pendapatan, principal); // Update Anggaran Pendapatan
        List<RincianPendapatanEntity> listRincianDpt = dao.getRincianPendapatanByDptId(idPendapatan); // Get Rincian Pendapatan
        return listRincianDpt;
    }


    private RincianPendapatanEntity rincianPendapatanLocalMapper(RincianPendapatanEntity r) {
        RincianPendapatanEntity value = r;
        value.setAnggaranDpa(r.getAnggaranTapd());
        value.setJumlahBarangDpa(r.getJumlahBarangTapd());
        value.setHargaBarangSatuanDpa(r.getHargaBarangSatuanTapd());
        value.setJumlahBarang1Dpa(r.getJumlahBarang1Tapd());
        value.setJumlahBarang2Dpa(r.getJumlahBarang2Tapd());
        value.setJumlahBarang3Dpa(r.getJumlahBarang3Tapd());
        value.setNamaBarangSatuanDpa(r.getNamaBarangSatuanTapd());
        value.setNamaBarangSatuan1Dpa(r.getNamaBarangSatuan1Tapd());
        value.setNamaBarangSatuan2Dpa(r.getNamaBarangSatuan2Tapd());
        value.setNamaBarangSatuan3Dpa(r.getNamaBarangSatuan3Tapd());
        return value;
    }

}
