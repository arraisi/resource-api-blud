package com.tabeldata.service;

import com.tabeldata.dao.KinerjaDao;
import com.tabeldata.dao.RbaNoMaxDao;
import com.tabeldata.dto.DataPenggunaLogin;
import com.tabeldata.dto.KinerjaSaveDto;
import com.tabeldata.dto.LoadKinerjaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
public class KinerjaService {

    @Autowired
    private KinerjaDao dao;

    @Autowired
    private DataPenggunaLoginService dataPenggunaLoginService;

    @Autowired
    private RbaNoMaxDao rbaNoMaxDao;

    public List<LoadKinerjaDto> loadKinerja(String tahunAnggaran, Integer idKegiatan, Integer idSkpd) {
        List<LoadKinerjaDto> data = dao.loadKinerja(tahunAnggaran, idKegiatan, idSkpd);
        return data;
    }

    /**
     * Save kinerja
     */
    public List<LoadKinerjaDto> saveKinerja(KinerjaSaveDto data, String tahunAnggaran, Integer idKegiatan, Integer idSkpd, Principal principal) throws DataAccessException {
        DataPenggunaLogin penggunaLogin = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName()); // Get Id Pengguna By Principal
        Integer idKinerja = rbaNoMaxDao.getIdFromNoMax("TMRBAKEGIATANKINERJA"); // get ID from TRRBANOMAX by nama Table "TMRBAKEGIATANKINERJA"
        List<KinerjaSaveDto> kinerjaKegiatanList = dao.getKinerjaByIdKegiatanSkpdTahun(tahunAnggaran, idKegiatan, idSkpd);
        kinerjaKegiatanList.add(data);
        Integer noUrut = 0;
        for (KinerjaSaveDto val : kinerjaKegiatanList) {
            noUrut += 1;
            if (val.getId() != null) {
                val.setNoUrut(noUrut);
                dao.updateKinerja(val);
            } else {
                val.setId(idKinerja);
                val.setIdPenggunaRekam(penggunaLogin.getId());
                val.setTanggalPenggunaRekam(new Timestamp(System.currentTimeMillis()));
                val.setTahunAnggaran(tahunAnggaran);
                val.setIdKegiatan(idKegiatan);
                val.setIdSkpd(idSkpd);
                val.setNoUrut(noUrut);
                dao.saveKinerja(val);
                rbaNoMaxDao.updateIdNoMax(idKinerja, "TMRBAKEGIATANKINERJA");
            }
        }
        List<LoadKinerjaDto> loadKinerjaDtoList = dao.loadKinerja(tahunAnggaran, idKegiatan, idSkpd);
        return loadKinerjaDtoList;
    }

    /**
     * Update kinerja
     */
    public List<LoadKinerjaDto> updateKinerja(KinerjaSaveDto data, String tahunAnggaran, Integer idKegiatan, Integer idSkpd, Principal principal) throws DataAccessException {
        DataPenggunaLogin penggunaLogin = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName()); // Get Id Pengguna By Principal
        data.setIdPenggunaUbah(penggunaLogin.getId());
        data.setTanggalPenggunaUbah(new Timestamp(System.currentTimeMillis()));
        dao.updateKinerja(data);
        // Untuk Mengurutkan kembali No Urut
        List<KinerjaSaveDto> kinerjaKegiatanList = dao.getKinerjaByIdKegiatanSkpdTahun(tahunAnggaran, idKegiatan, idSkpd);
        Integer noUrut = 0;
        for (KinerjaSaveDto val : kinerjaKegiatanList) {
            noUrut += 1;
            val.setNoUrut(noUrut);
            dao.updateKinerja(val);
        }
        List<LoadKinerjaDto> loadKinerjaDtoList = dao.loadKinerja(tahunAnggaran, idKegiatan, idSkpd);
        return loadKinerjaDtoList;
    }

    public List<LoadKinerjaDto> deleteKinerja(String tahunAnggaran, Integer idKegiatan, Integer idSkpd, Integer idKinerja) throws DataAccessException {
        dao.deleteKinerja(tahunAnggaran, idKegiatan, idSkpd, idKinerja);
        List<KinerjaSaveDto> kinerjaKegiatanList = dao.getKinerjaByIdKegiatanSkpdTahun(tahunAnggaran, idKegiatan, idSkpd);
        Integer noUrut = 0;
        for (KinerjaSaveDto val : kinerjaKegiatanList) {
            noUrut += 1;
            val.setNoUrut(noUrut);
            dao.updateKinerja(val);
        }
        List<LoadKinerjaDto> loadKinerjaDtoList = dao.loadKinerja(tahunAnggaran, idKegiatan, idSkpd);
        return loadKinerjaDtoList;
    }
}
