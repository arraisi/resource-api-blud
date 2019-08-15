package com.tabeldata.dao;

import com.tabeldata.entity.KomponenBelanjaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class KomponenBelanjaPegawaiDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public void saveKomponenBelanjaPegawai(KomponenBelanjaEntity komponen) {
        String query = "INSERT INTO TMRBABLRINCI (\n" +
                "I_ID, \n" +
                "I_IDKEGIATAN,\n" +
                "C_ANGG_TAHUN,\n" +
                "I_IDSKPD,\n" +
                "C_KEGIATAN,\n" +
                "I_IDBAS,\n" +
                "I_IDBASKOMPONEN,\n" +
                "I_ANGG_NOURUT,\n" +
                "E_ANGG_RINCI,\n" +
                "E_ANGG_SPESIFIKASI,\n" +
                "C_KOMPONEN,\n" +
                "N_KOMPONEN,\n" +
                "N_KOMPONEN_MERK,\n" +
                "N_KOMPONEN_SPEK,\n" +
                "V_KOMPONEN_HARGA,\n" +
                "P_KOMPONEN_PAJAK,\n" +
                "Q_BRG_VOLUME,\n" +
                "N_BRG_KOEFISIEN,\n" +
                "Q_BRG_VOLUME1,\n" +
                "N_BRG_SATUAN1,\n" +
                "Q_BRG_VOLUME2,\n" +
                "N_BRG_SATUAN2,\n" +
                "Q_BRG_VOLUME3,\n" +
                "N_BRG_SATUAN3,\n" +
                "Q_BRG_VOLUME4,\n" +
                "N_BRG_SATUAN4,\n" +
                "E_RMKS_SUBRINCIAN,\n" +
                "E_RMKS,\n" +
                "C_SWAKELOLA,\n" +
                "V_ANGG_DPA,\n" +
                "V_ANGG_TAPD,\n" +
                "I_PGUN_REKAM,\n" +
                "D_PGUN_REKAM,\n" +
                "I_PGUN_UBAH,\n" +
                "D_REKAM_UBAH,\n" +
                "V_RPA_BULAN01,\n" +
                "V_RPA_BULAN02,\n" +
                "V_RPA_BULAN03,\n" +
                "V_RPA_BULAN04,\n" +
                "V_RPA_BULAN05,\n" +
                "V_RPA_BULAN06,\n" +
                "V_RPA_BULAN07,\n" +
                "V_RPA_BULAN08,\n" +
                "V_RPA_BULAN09,\n" +
                "V_RPA_BULAN10,\n" +
                "V_RPA_BULAN11,\n" +
                "V_RPA_BULAN12)\n" +
                "VALUES\n" +
                "(:id,\n" +
                ":idKegiatan,\n" +
                ":tahunAnggaran,\n" +
                ":idSkpd,\n" +
                ":kodeKegiatan,\n" +
                ":idBas,\n" +
                ":idBasKomponen,\n" +
                ":idAnggaranNoUrut,\n" +
                ":entryAnggaranRinci,\n" +
                ":entryAnggaranSpesifikasi,\n" +
                ":kodeKomponen,\n" +
                ":namaKomponen,\n" +
                ":merk,\n" +
                ":spek,\n" +
                ":harga,\n" +
                ":pajak,\n" +
                ":volume,\n" +
                ":koefisien,\n" +
                ":volume1,\n" +
                ":satuan1,\n" +
                ":volume2,\n" +
                ":satuan2,\n" +
                ":volume3,\n" +
                ":satuan3,\n" +
                ":volume4,\n" +
                ":satuan4,\n" +
                ":rmksSubrincian,\n" +
                ":rmks,\n" +
                ":swakelola,\n" +
                ":anggaranDpa,\n" +
                ":anggaranTapd,\n" +
                ":idPenggunaRekam,\n" +
                ":tglPenggunaRekam,\n" +
                ":idPenggunaUbah,\n" +
                ":tglPenggunaUbah,\n" +
                ":rpaBulan1,\n" +
                ":rpaBulan2,\n" +
                ":rpaBulan3,\n" +
                ":rpaBulan4,\n" +
                ":rpaBulan5,\n" +
                ":rpaBulan6,\n" +
                ":rpaBulan7,\n" +
                ":rpaBulan8,\n" +
                ":rpaBulan9,\n" +
                ":rpaBulan10,\n" +
                ":rpaBulan11,\n" +
                ":rpaBulan12)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", komponen.getId());
        parameterSource.addValue("idKegiatan", komponen.getIdKegiatan());
        parameterSource.addValue("tahunAnggaran", komponen.getTahunAnggaran());
        parameterSource.addValue("idSkpd", komponen.getIdSkpd());
        parameterSource.addValue("kodeKegiatan", komponen.getKodeKegiatan());
        parameterSource.addValue("idBas", komponen.getIdBas());
        parameterSource.addValue("idBasKomponen", komponen.getIdBasKomponen());
        parameterSource.addValue("idAnggaranNoUrut", komponen.getIdAnggaranNoUrut());
        parameterSource.addValue("entryAnggaranRinci", komponen.getEntryAnggaranRinci());
        parameterSource.addValue("entryAnggaranSpesifikasi", komponen.getEntryAnggaranSpesifikasi());
        parameterSource.addValue("kodeKomponen", komponen.getKodeKomponen());
        parameterSource.addValue("namaKomponen", komponen.getNamaKomponen());
        parameterSource.addValue("merk", komponen.getMerk());
        parameterSource.addValue("spek", komponen.getSpek());
        parameterSource.addValue("harga", komponen.getHarga());
        parameterSource.addValue("pajak", komponen.getPajak());
        parameterSource.addValue("volume", komponen.getVolume());
        parameterSource.addValue("koefisien", komponen.getKoefisien());
        parameterSource.addValue("volume1", komponen.getVolume1());
        parameterSource.addValue("satuan1", komponen.getSatuan1());
        parameterSource.addValue("volume2", komponen.getVolume2());
        parameterSource.addValue("satuan2", komponen.getSatuan2());
        parameterSource.addValue("volume3", komponen.getVolume3());
        parameterSource.addValue("satuan3", komponen.getSatuan3());
        parameterSource.addValue("volume4", komponen.getVolume4());
        parameterSource.addValue("satuan4", komponen.getSatuan4());
        parameterSource.addValue("rmksSubrincian", komponen.getRmksSubrincian());
        parameterSource.addValue("rmks", komponen.getRmksSubrincian());
        parameterSource.addValue("swakelola", komponen.getSwakelola());
        parameterSource.addValue("anggaranDpa", komponen.getAnggaranDpa());
        parameterSource.addValue("anggaranTapd", komponen.getAnggaranTapd());
        parameterSource.addValue("idPenggunaRekam", komponen.getIdPenggunaRekam());
        parameterSource.addValue("tglPenggunaRekam", komponen.getTglPenggunaRekam());
        parameterSource.addValue("idPenggunaUbah", komponen.getIdPenggunaUbah());
        parameterSource.addValue("tglPenggunaUbah", komponen.getTglPenggunaUbah());
        parameterSource.addValue("rpaBulan1", komponen.getRpaBulan1());
        parameterSource.addValue("rpaBulan2", komponen.getRpaBulan2());
        parameterSource.addValue("rpaBulan3", komponen.getRpaBulan3());
        parameterSource.addValue("rpaBulan4", komponen.getRpaBulan4());
        parameterSource.addValue("rpaBulan5", komponen.getRpaBulan5());
        parameterSource.addValue("rpaBulan6", komponen.getRpaBulan6());
        parameterSource.addValue("rpaBulan7", komponen.getRpaBulan7());
        parameterSource.addValue("rpaBulan8", komponen.getRpaBulan8());
        parameterSource.addValue("rpaBulan9", komponen.getRpaBulan9());
        parameterSource.addValue("rpaBulan10", komponen.getRpaBulan10());
        parameterSource.addValue("rpaBulan11", komponen.getRpaBulan11());
        parameterSource.addValue("rpaBulan12", komponen.getRpaBulan12());

        this.jdbcTemplate.update(query, parameterSource);
    }

    public Integer getNoUrut(Integer idKegiatan, String tahunAnggaran, Integer idBas) {
        String query = "SELECT I_ANGG_NOURUT + 1 AS noUrut FROM TMRBABLRINCI WHERE I_IDKEGIATAN = :idKegiatan " +
                "AND C_ANGG_TAHUN = :tahunAngg AND I_IDBAS = :idBas";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idKegiatan", idKegiatan);
        parameterSource.addValue("tahunAngg", tahunAnggaran);
        parameterSource.addValue("idBas", idBas);


        List<Integer> noUrut = jdbcTemplate.query(query, parameterSource, new RowMapper<Integer>() {
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("noUrut");
            }
        });

        if (!noUrut.isEmpty()) {
            return noUrut.get(noUrut.size() - 1);
        } else {
            return 1;
        }

    }

    public void updateNoUrut(Integer updateId, Integer id) {
        String query = "UPDATE TMRBABLRINCI SET I_ANGG_NOURUT = :updateId  WHERE I_ID = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("updateId", updateId);
        parameterSource.addValue("id", id);

        jdbcTemplate.update(query, parameterSource);

    }

    public List<KomponenBelanjaEntity> listByParamater(List<Integer> idList) {
        String query = "SELECT\n" +
                "I_ID                   AS id, \n" +
                "I_IDKEGIATAN           AS idKegiatan,\n" +
                "C_ANGG_TAHUN           AS tahunAngg,\n" +
                "I_IDSKPD               AS idSkpd,\n" +
                "C_KEGIATAN             AS kodeKegiatan,\n" +
                "I_IDBAS                AS idBas,\n" +
                "I_IDBASKOMPONEN        AS idBasKomponen,\n" +
                "I_ANGG_NOURUT          AS noUrut,\n" +
                "E_ANGG_RINCI           AS anggaranRinci,\n" +
                "E_ANGG_SPESIFIKASI     AS anggaranSpesifikasi,\n" +
                "C_KOMPONEN             AS kodeKomponen,\n" +
                "N_KOMPONEN             AS namaKomponen,\n" +
                "N_KOMPONEN_MERK        AS merk,\n" +
                "N_KOMPONEN_SPEK        AS spek,\n" +
                "V_KOMPONEN_HARGA       AS harga,\n" +
                "P_KOMPONEN_PAJAK       AS pajak,\n" +
                "Q_BRG_VOLUME           AS volume,\n" +
                "N_BRG_KOEFISIEN        AS koefisien,\n" +
                "Q_BRG_VOLUME1          AS volume1,\n" +
                "N_BRG_SATUAN1          AS satuan1,\n" +
                "Q_BRG_VOLUME2          AS volume2,\n" +
                "N_BRG_SATUAN2          AS satuan2,\n" +
                "Q_BRG_VOLUME3          AS volume3,\n" +
                "N_BRG_SATUAN3          AS satuan3,\n" +
                "Q_BRG_VOLUME4          AS volume4,\n" +
                "N_BRG_SATUAN4          AS satuan4,\n" +
                "E_RMKS_SUBRINCIAN      AS rmksSubrincian,\n" +
                "E_RMKS                 AS rmks,\n" +
                "C_SWAKELOLA            AS swakelola,\n" +
                "V_ANGG_DPA             AS anggaranDpa,\n" +
                "V_ANGG_TAPD            AS anggaranTapd,\n" +
                "I_PGUN_REKAM           AS idPenggunaRekam,\n" +
                "D_PGUN_REKAM           AS tglPenggunaRekam,\n" +
                "I_PGUN_UBAH            AS idPenggunaUbah,\n" +
                "D_REKAM_UBAH           AS tglPenggunaUbah,\n" +
                "V_RPA_BULAN01          AS rpaBulan1,\n" +
                "V_RPA_BULAN02          AS rpaBulan2,\n" +
                "V_RPA_BULAN03          AS rpaBulan3,\n" +
                "V_RPA_BULAN04          AS rpaBulan4,\n" +
                "V_RPA_BULAN05          AS rpaBulan5,\n" +
                "V_RPA_BULAN06          AS rpaBulan6,\n" +
                "V_RPA_BULAN07          AS rpaBulan7,\n" +
                "V_RPA_BULAN08          AS rpaBulan8,\n" +
                "V_RPA_BULAN09          AS rpaBulan9,\n" +
                "V_RPA_BULAN10          AS rpaBulan10,\n" +
                "V_RPA_BULAN11          AS rpaBulan11,\n" +
                "V_RPA_BULAN12          AS rpaBulan12\n" +
                "FROM TMRBABLRINCI " +
                "WHERE I_ID IN (:ids)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("ids", idList);

        return jdbcTemplate.query(query, parameterSource, (resultSet, i) -> {
            KomponenBelanjaEntity komponenBelanja = new KomponenBelanjaEntity();
            komponenBelanja.setId(resultSet.getInt("id"));
            komponenBelanja.setIdKegiatan(resultSet.getInt("idKegiatan"));
            komponenBelanja.setTahunAnggaran(resultSet.getString("tahunAngg"));
            komponenBelanja.setIdSkpd(resultSet.getInt("idSkpd"));
            komponenBelanja.setKodeKegiatan(resultSet.getString("kodeKegiatan"));
            komponenBelanja.setIdBas(resultSet.getInt("idBas"));
            komponenBelanja.setIdBasKomponen(resultSet.getInt("idBasKomponen"));
            komponenBelanja.setIdAnggaranNoUrut(resultSet.getInt("noUrut"));
            komponenBelanja.setEntryAnggaranRinci(resultSet.getString("anggaranRinci"));
            komponenBelanja.setEntryAnggaranSpesifikasi(resultSet.getString("anggaranSpesifikasi"));
            komponenBelanja.setKodeKomponen(resultSet.getString("kodeKomponen"));
            komponenBelanja.setNamaKomponen(resultSet.getString("namaKomponen"));
            komponenBelanja.setMerk(resultSet.getString("merk"));
            komponenBelanja.setSpek(resultSet.getString("spek"));
            komponenBelanja.setHarga(resultSet.getBigDecimal("harga"));
            komponenBelanja.setPajak(resultSet.getInt("pajak"));
            komponenBelanja.setVolume(resultSet.getInt("volume"));
            komponenBelanja.setKoefisien(resultSet.getString("koefisien"));
            komponenBelanja.setVolume1(resultSet.getInt("volume1"));
            komponenBelanja.setSatuan1(resultSet.getString("satuan1"));
            komponenBelanja.setVolume2(resultSet.getInt("volume2"));
            komponenBelanja.setSatuan2(resultSet.getString("satuan2"));
            komponenBelanja.setVolume3(resultSet.getInt("volume3"));
            komponenBelanja.setSatuan3(resultSet.getString("satuan3"));
            komponenBelanja.setVolume4(resultSet.getInt("volume4"));
            komponenBelanja.setSatuan4(resultSet.getString("satuan4"));
            komponenBelanja.setRmksSubrincian(resultSet.getString("rmksSubrincian"));
            komponenBelanja.setRmks(resultSet.getString("rmks"));
            komponenBelanja.setSwakelola(resultSet.getString("swakelola"));
            komponenBelanja.setAnggaranDpa(resultSet.getBigDecimal("anggaranDpa"));
            komponenBelanja.setAnggaranTapd(resultSet.getBigDecimal("anggaranTapd"));
            komponenBelanja.setIdPenggunaRekam(resultSet.getInt("idPenggunaRekam"));
            komponenBelanja.setTglPenggunaRekam(resultSet.getTimestamp("tglPenggunaRekam"));
            komponenBelanja.setIdPenggunaUbah(resultSet.getInt("idPenggunaUbah"));
            komponenBelanja.setTglPenggunaUbah(resultSet.getTimestamp("tglPenggunaUbah"));
            komponenBelanja.setRpaBulan1(resultSet.getBigDecimal("rpaBulan1"));
            komponenBelanja.setRpaBulan2(resultSet.getBigDecimal("rpaBulan2"));
            komponenBelanja.setRpaBulan3(resultSet.getBigDecimal("rpaBulan3"));
            komponenBelanja.setRpaBulan4(resultSet.getBigDecimal("rpaBulan4"));
            komponenBelanja.setRpaBulan5(resultSet.getBigDecimal("rpaBulan5"));
            komponenBelanja.setRpaBulan6(resultSet.getBigDecimal("rpaBulan6"));
            komponenBelanja.setRpaBulan7(resultSet.getBigDecimal("rpaBulan7"));
            komponenBelanja.setRpaBulan8(resultSet.getBigDecimal("rpaBulan8"));
            komponenBelanja.setRpaBulan9(resultSet.getBigDecimal("rpaBulan9"));
            komponenBelanja.setRpaBulan10(resultSet.getBigDecimal("rpaBulan10"));
            komponenBelanja.setRpaBulan11(resultSet.getBigDecimal("rpaBulan11"));
            komponenBelanja.setRpaBulan12(resultSet.getBigDecimal("rpaBulan12"));
            return komponenBelanja;
        });

    }


}
