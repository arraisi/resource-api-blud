package com.tabeldata.dao;

import com.tabeldata.entity.RincianPendapatanEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class RicianPendapatanDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Method Untuk Get Rincian Berdasarkan ID DPT
     */
    public List<RincianPendapatanEntity> getRincianPendapatanByDptId(Integer idDpt) {
        String sql = "select I_ID               AS id,\n" +
                "       I_IDDPT            AS idDpt,\n" +
                "       I_IDSKPD           AS idSkpd,\n" +
                "       I_IDBAS            AS idBas,\n" +
                "       C_ANGG_TAHUN       AS tahunAnggaran,\n" +
                "       I_ANGG_NOURUT      AS anggaranNoUrut,\n" +
                "       E_ANGG_RINCI       AS rincianAnggaran,\n" +
                "       E_ANGG_SPESIFIKASI AS spesifikasiAnggaran,\n" +
                "       Q_BRG_VOLUMEDPA    AS jumlahBarangDpa,\n" +
                "       N_BRG_SATUANDPA    AS namaBarangSatuanDpa,\n" +
                "       V_BRG_HARGADPA     AS hargaBarangSatuanDpa,\n" +
                "       V_ANGG_DPA         AS anggaranDpa,\n" +
                "       Q_BRG_VOLUMETAPD   AS jumlahBarangTapd,\n" +
                "       N_BRG_SATUANTAPD   AS namaBarangSatuanTapd,\n" +
                "       V_BRG_HARGATAPD    AS hargaBarangSatuanTapd,\n" +
                "       V_ANGG_TAPD        AS anggaranTapd,\n" +
                "       Q_BRG1_VOLUMEDPA   AS jumlahBarang1Dpa,\n" +
                "       N_BRG1_SATUANDPA   AS namaBarangSatuan1Dpa,\n" +
                "       Q_BRG2_VOLUMEDPA   AS jumlahBarang2Dpa,\n" +
                "       N_BRG2_SATUANDPA   AS namaBarangSatuan2Dpa,\n" +
                "       Q_BRG3_VOLUMEDPA   AS jumlahBarang3Dpa,\n" +
                "       N_BRG3_SATUANDPA   AS namaBarangSatuan3Dpa,\n" +
                "       Q_BRG1_VOLUMETAPD  AS jumlahBarang1Tapd,\n" +
                "       N_BRG1_SATUANTAPD  AS namaBarangSatuan1Tapd,\n" +
                "       Q_BRG2_VOLUMETAPD  AS jumlahBarang2Tapd,\n" +
                "       N_BRG2_SATUANTAPD  AS namaBarangSatuan2Tapd,\n" +
                "       Q_BRG3_VOLUMETAPD  AS jumlahBarang3Tapd,\n" +
                "       N_BRG3_SATUANTAPD  AS namaBarangSatuan3Tapd,\n" +
                "       I_PGUN_REKAM       AS idRekamPengguna,\n" +
                "       D_PGUN_REKAM       AS tanggalRekamPengguna,\n" +
                "       I_PGUN_UBAH        AS idUbahPengguna,\n" +
                "       D_REKAM_UBAH       AS tanggalUbahPengguna\n" +
                "from TMRBADPTRINCI\n" +
                "where I_IDDPT = :vIdDpt order by I_ANGG_NOURUT asc ";

        Map<String, Object> param = new HashMap<>();
        param.put("vIdDpt", idDpt);

        return this.namedParameterJdbcTemplate.query(sql, param, (rs, i) -> {
            RincianPendapatanEntity value = new RincianPendapatanEntity();
            value.setId(rs.getInt("id"));
            value.setIdDpt(rs.getInt("idDpt"));
            value.setIdSkpd(rs.getInt("idSkpd"));
            value.setIdBas(rs.getInt("idBas"));
            value.setTahunAnggaran(rs.getString("tahunAnggaran"));
            value.setAnggaranNoUrut(rs.getInt("anggaranNoUrut"));
            value.setRincianAnggaran(rs.getString("rincianAnggaran"));
            value.setSpesifikasiAnggaran(rs.getString("spesifikasiAnggaran"));
            value.setJumlahBarangDpa(rs.getInt("jumlahBarangDpa"));
            value.setNamaBarangSatuanDpa(rs.getString("namaBarangSatuanDpa"));
            value.setJumlahBarang1Dpa(rs.getInt("jumlahBarang1Dpa"));
            value.setNamaBarangSatuan1Dpa(rs.getString("namaBarangSatuan1Dpa"));
            value.setJumlahBarang2Dpa(rs.getInt("jumlahBarang2Dpa"));
            value.setNamaBarangSatuan2Dpa(rs.getString("namaBarangSatuan2Dpa"));
            value.setJumlahBarang3Dpa(rs.getInt("jumlahBarang3Dpa"));
            value.setNamaBarangSatuan3Dpa(rs.getString("namaBarangSatuan3Dpa"));
            value.setHargaBarangSatuanDpa(rs.getBigDecimal("hargaBarangSatuanDpa"));
            value.setAnggaranDpa(rs.getBigDecimal("anggaranDpa"));
            value.setJumlahBarangTapd(rs.getInt("jumlahBarangTapd"));
            value.setNamaBarangSatuanTapd(rs.getString("namaBarangSatuanTapd"));
            value.setJumlahBarang1Tapd(rs.getInt("jumlahBarang1Tapd"));
            value.setNamaBarangSatuan1Tapd(rs.getString("namaBarangSatuan1Tapd"));
            value.setJumlahBarang2Tapd(rs.getInt("jumlahBarang2Tapd"));
            value.setNamaBarangSatuan2Tapd(rs.getString("namaBarangSatuan2Tapd"));
            value.setJumlahBarang3Tapd(rs.getInt("jumlahBarang3Tapd"));
            value.setNamaBarangSatuan3Tapd(rs.getString("namaBarangSatuan3Tapd"));
            value.setHargaBarangSatuanTapd(rs.getBigDecimal("hargaBarangSatuanTapd"));
            value.setAnggaranTapd(rs.getBigDecimal("anggaranTapd"));
            value.setIdRekamPengguna(rs.getInt("idRekamPengguna"));
            value.setTanggalRekamPengguna(rs.getTimestamp("tanggalRekamPengguna"));
            value.setIdUbahPengguna(rs.getInt("idUbahPengguna"));
            value.setTanggalUbahPengguna(rs.getTimestamp("tanggalUbahPengguna"));
            return value;
        });

    }


    /**
     * Method untuk delete rincian berdasarkan id DPT dan ID Rincian
     */
    public int deleteRincianPendapatanById(Integer idDpt, Integer idRincian) {
        //language=Oracle
        String sql = "delete from  TMRBADPTRINCI where I_ID = :vIdRincian and I_IDDPT = :vIdDpt";
        Map<String, Object> param = new HashMap<>();
        param.put("vIdRincian", idRincian);
        param.put("vIdDpt", idDpt);

        return this.namedParameterJdbcTemplate.update(sql, param);
    }

    /**
     * Method untuk delete rincian berdasarkan List ID DPT dan ID Rincian
     */
    public int deleteRincianPendapatanByListIdAndIdDpt(List<Integer> idDpt, Integer idRincian) {
        //language=Oracle
        String sql = "DELETE FROM TMRBADPTRINCI WHERE I_ID IN (:vListId) AND I_IDDPT = :vIdDpt";
        Map<String, Object> param = new HashMap<>();
        param.put("vListId", idRincian);
        param.put("vIdDpt", idDpt);
        return this.namedParameterJdbcTemplate.update(sql, param);
    }

    /**
     * Method untuk delete rincian berdasarkan ID DPT
     */
    public int deleteRincianPendapatanByIdDpt(Integer idDpt) {
        //language=Oracle
        String sql = "DELETE FROM TMRBADPTRINCI WHERE I_IDDPT = :vIdDpt";
        Map<String, Object> param = new HashMap<>();
        param.put("vIdDpt", idDpt);
        return this.namedParameterJdbcTemplate.update(sql, param);
    }

    /**
     * Method untuk delete rincian berdasarkan ID Rincian dan ID DPT
     */
    public int deleteRincianPendapatanByIdDptAndIdRincian(Integer idDpt, Integer idRincian) {
        //language=Oracle
        String sql = "DELETE FROM TMRBADPTRINCI WHERE I_IDDPT = :vIdDpt AND I_ID = :vIdRinci";
        Map<String, Object> param = new HashMap<>();
        param.put("vIdRinci", idRincian);
        param.put("vIdDpt", idDpt);
        return this.namedParameterJdbcTemplate.update(sql, param);
    }

    /**
     * Method untuk Save Rincian Pendapatan
     */
    public void saveRincianPendapatan(RincianPendapatanEntity value) {
        //language=Oracle
        String sql = "INSERT INTO TMRBADPTRINCI (I_ID,\n" +
                "                           I_IDDPT,\n" +
                "                           I_IDSKPD,\n" +
                "                           I_IDBAS,\n" +
                "                           C_ANGG_TAHUN,\n" +
                "                           I_ANGG_NOURUT,\n" +
                "                           E_ANGG_RINCI,\n" +
                "                           E_ANGG_SPESIFIKASI,\n" +
                "                           V_ANGG_DPA,\n" +
                "                           V_ANGG_TAPD,\n" +
                "                           Q_BRG_VOLUMEDPA,\n" +
                "                           N_BRG_SATUANDPA,\n" +
                "                           Q_BRG1_VOLUMEDPA,\n" +
                "                           N_BRG1_SATUANDPA,\n" +
                "                           Q_BRG2_VOLUMEDPA,\n" +
                "                           N_BRG2_SATUANDPA,\n" +
                "                           Q_BRG3_VOLUMEDPA,\n" +
                "                           N_BRG3_SATUANDPA,\n" +
                "                           V_BRG_HARGADPA,\n" +
                "                           Q_BRG_VOLUMETAPD,\n" +
                "                           N_BRG_SATUANTAPD,\n" +
                "                           Q_BRG1_VOLUMETAPD,\n" +
                "                           N_BRG1_SATUANTAPD,\n" +
                "                           Q_BRG2_VOLUMETAPD,\n" +
                "                           N_BRG2_SATUANTAPD,\n" +
                "                           Q_BRG3_VOLUMETAPD,\n" +
                "                           N_BRG3_SATUANTAPD,\n" +
                "                           V_BRG_HARGATAPD,\n" +
                "                           I_PGUN_REKAM,\n" +
                "                           D_PGUN_REKAM,\n" +
                "                           I_PGUN_UBAH,\n" +
                "                           D_REKAM_UBAH)\n" +
                "VALUES (:id,\n" +
                "        :idDpt,\n" +
                "        :idSkpd,\n" +
                "        :idBas,\n" +
                "        :tahunAnggaran,\n" +
                "        :anggaranNoUrut,\n" +
                "        :rincianAnggaran,\n" +
                "        :spesifikasiAnggaran,\n" +
                "        :anggaranDpa,\n" +
                "        :anggaranTapd,\n" +
                "        :jumlahBarangDpa,\n" +
                "        :namaBarangSatuanDpa,\n" +
                "        :jumlahBarang1Dpa,\n" +
                "        :namaBarangSatuan1Dpa,\n" +
                "        :jumlahBarang2Dpa,\n" +
                "        :namaBarangSatuan2Dpa,\n" +
                "        :jumlahBarang3Dpa,\n" +
                "        :namaBarangSatuan3Dpa,\n" +
                "        :hargaBarangSatuanDpa,\n" +
                "        :jumlahBarangTapd,\n" +
                "        :namaBarangSatuanTapd,\n" +
                "        :jumlahBarang1Tapd,\n" +
                "        :namaBarangSatuan1Tapd,\n" +
                "        :jumlahBarang2Tapd,\n" +
                "        :namaBarangSatuan2Tapd,\n" +
                "        :jumlahBarang3Tapd,\n" +
                "        :namaBarangSatuan3Tapd,\n" +
                "        :hargaBarangSatuanTapd,\n" +
                "        :idRekamPengguna,\n" +
                "        :tanggalRekamPengguna,\n" +
                "        :idUbahPengguna,\n" +
                "        :tanggalUbahPengguna)";
        Map<String, Object> params = new HashMap<>();
        params.put("id", value.getId());
        params.put("idDpt", value.getIdDpt());
        params.put("idSkpd", value.getIdSkpd());
        params.put("idBas", value.getIdBas());
        params.put("tahunAnggaran", value.getTahunAnggaran());
        params.put("anggaranNoUrut", value.getAnggaranNoUrut());
        params.put("rincianAnggaran", value.getRincianAnggaran());
        params.put("spesifikasiAnggaran", value.getSpesifikasiAnggaran());
        params.put("anggaranDpa", value.getAnggaranDpa());
        params.put("anggaranTapd", value.getAnggaranTapd());
        params.put("jumlahBarangDpa", value.getJumlahBarangDpa());
        params.put("namaBarangSatuanDpa", value.getNamaBarangSatuanDpa());
        params.put("jumlahBarang1Dpa", value.getJumlahBarang1Dpa());
        params.put("namaBarangSatuan1Dpa", value.getNamaBarangSatuan1Dpa());
        params.put("jumlahBarang2Dpa", value.getJumlahBarang2Dpa());
        params.put("namaBarangSatuan2Dpa", value.getNamaBarangSatuan2Dpa());
        params.put("jumlahBarang3Dpa", value.getJumlahBarang3Dpa());
        params.put("namaBarangSatuan3Dpa", value.getNamaBarangSatuan3Dpa());
        params.put("hargaBarangSatuanDpa", value.getHargaBarangSatuanDpa());
        params.put("jumlahBarangTapd", value.getJumlahBarangTapd());
        params.put("namaBarangSatuanTapd", value.getNamaBarangSatuanTapd());
        params.put("jumlahBarang1Tapd", value.getJumlahBarang1Tapd());
        params.put("namaBarangSatuan1Tapd", value.getNamaBarangSatuan1Tapd());
        params.put("jumlahBarang2Tapd", value.getJumlahBarang2Tapd());
        params.put("namaBarangSatuan2Tapd", value.getNamaBarangSatuan2Tapd());
        params.put("jumlahBarang3Tapd", value.getJumlahBarang3Tapd());
        params.put("namaBarangSatuan3Tapd", value.getNamaBarangSatuan3Tapd());
        params.put("hargaBarangSatuanTapd", value.getHargaBarangSatuanTapd());
        params.put("idRekamPengguna", value.getIdRekamPengguna());
        params.put("tanggalRekamPengguna", value.getTanggalRekamPengguna());
        params.put("idUbahPengguna", value.getIdUbahPengguna());
        params.put("tanggalUbahPengguna", value.getTanggalUbahPengguna());
        this.namedParameterJdbcTemplate.update(sql, params);
    }

    /**
     * Method untuk update rincian pendapatan By ID dan By ID DPT
     */
    public void updateRincianPendapatanByIdDanIdDpt(RincianPendapatanEntity value) {
        String sql = "UPDATE\n" +
                "    TMRBADPTRINCI\n" +
                "SET " +
                "    I_IDDPT            = :idDpt,\n" +
                "    I_IDSKPD           = :idSkpd,\n" +
                "    I_IDBAS            = :idBas,\n" +
                "    C_ANGG_TAHUN       = :tahunAnggaran,\n" +
                "    I_ANGG_NOURUT      = :anggaranNoUrut,\n" +
                "    E_ANGG_RINCI       = :rincianAnggaran,\n" +
                "    E_ANGG_SPESIFIKASI = :spesifikasiAnggaran,\n" +
                "    V_ANGG_DPA         = :anggaranDpa,\n" +
                "    V_ANGG_TAPD        = :anggaranTapd,\n" +
                "    Q_BRG_VOLUMEDPA    = :jumlahBarangDpa,\n" +
                "    N_BRG_SATUANDPA    = :namaBarangSatuanDpa,\n" +
                "    Q_BRG1_VOLUMEDPA   = :jumlahBarang1Dpa,\n" +
                "    N_BRG1_SATUANDPA   = :namaBarangSatuan1Dpa,\n" +
                "    Q_BRG2_VOLUMEDPA   = :jumlahBarang2Dpa,\n" +
                "    N_BRG2_SATUANDPA   = :namaBarangSatuan2Dpa,\n" +
                "    Q_BRG3_VOLUMEDPA   = :jumlahBarang3Dpa,\n" +
                "    N_BRG3_SATUANDPA   = :namaBarangSatuan3Dpa,\n" +
                "    V_BRG_HARGADPA     = :hargaBarangSatuanDpa,\n" +
                "    Q_BRG_VOLUMETAPD   = :jumlahBarangTapd,\n" +
                "    N_BRG_SATUANTAPD   = :namaBarangSatuanTapd,\n" +
                "    Q_BRG1_VOLUMETAPD  = :jumlahBarang1Tapd,\n" +
                "    N_BRG1_SATUANTAPD  = :namaBarangSatuan1Tapd,\n" +
                "    Q_BRG2_VOLUMETAPD  = :jumlahBarang2Tapd,\n" +
                "    N_BRG2_SATUANTAPD  = :namaBarangSatuan2Tapd,\n" +
                "    Q_BRG3_VOLUMETAPD  = :jumlahBarang3Tapd,\n" +
                "    N_BRG3_SATUANTAPD  = :namaBarangSatuan3Tapd,\n" +
                "    V_BRG_HARGATAPD    = :hargaBarangSatuanTapd,\n" +
                "    I_PGUN_REKAM       = :idRekamPengguna,\n" +
                "    D_PGUN_REKAM       = :tanggalRekamPengguna,\n" +
                "    I_PGUN_UBAH        = :idUbahPengguna,\n" +
                "    D_REKAM_UBAH       = :tanggalUbahPengguna\n" +
                "WHERE I_ID = :id\n" +
                "  " +
                "AND I_IDDPT = :idDpt";
        Map<String, Object> params = new HashMap<>();
        params.put("id", value.getId());
        params.put("idDpt", value.getIdDpt());
        params.put("idSkpd", value.getIdSkpd());
        params.put("idBas", value.getIdBas());
        params.put("tahunAnggaran", value.getTahunAnggaran());
        params.put("anggaranNoUrut", value.getAnggaranNoUrut());
        params.put("rincianAnggaran", value.getRincianAnggaran());
        params.put("spesifikasiAnggaran", value.getSpesifikasiAnggaran());
        params.put("anggaranDpa", value.getAnggaranDpa());
        params.put("anggaranTapd", value.getAnggaranTapd());
        params.put("jumlahBarangDpa", value.getJumlahBarangDpa());
        params.put("namaBarangSatuanDpa", value.getNamaBarangSatuanDpa());
        params.put("jumlahBarang1Dpa", value.getJumlahBarang1Dpa());
        params.put("namaBarangSatuan1Dpa", value.getNamaBarangSatuan1Dpa());
        params.put("jumlahBarang2Dpa", value.getJumlahBarang2Dpa());
        params.put("namaBarangSatuan2Dpa", value.getNamaBarangSatuan2Dpa());
        params.put("jumlahBarang3Dpa", value.getJumlahBarang3Dpa());
        params.put("namaBarangSatuan3Dpa", value.getNamaBarangSatuan3Dpa());
        params.put("hargaBarangSatuanDpa", value.getHargaBarangSatuanDpa());
        params.put("jumlahBarangTapd", value.getJumlahBarangTapd());
        params.put("namaBarangSatuanTapd", value.getNamaBarangSatuanTapd());
        params.put("jumlahBarang1Tapd", value.getJumlahBarang1Tapd());
        params.put("namaBarangSatuan1Tapd", value.getNamaBarangSatuan1Tapd());
        params.put("jumlahBarang2Tapd", value.getJumlahBarang2Tapd());
        params.put("namaBarangSatuan2Tapd", value.getNamaBarangSatuan2Tapd());
        params.put("jumlahBarang3Tapd", value.getJumlahBarang3Tapd());
        params.put("namaBarangSatuan3Tapd", value.getNamaBarangSatuan3Tapd());
        params.put("hargaBarangSatuanTapd", value.getHargaBarangSatuanTapd());
        params.put("idRekamPengguna", value.getIdRekamPengguna());
        params.put("tanggalRekamPengguna", value.getTanggalRekamPengguna());
        params.put("idUbahPengguna", value.getIdUbahPengguna());
        params.put("tanggalUbahPengguna", value.getTanggalUbahPengguna());
        this.namedParameterJdbcTemplate.update(sql, params);
    }
}
