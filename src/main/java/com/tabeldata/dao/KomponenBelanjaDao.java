package com.tabeldata.dao;

import com.tabeldata.dto.KomponenBelanjaEditDto;
import com.tabeldata.dto.KomponenBelanjaGetDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections15.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class KomponenBelanjaDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public void saveKomponenBelanja(KomponenBelanjaGetDto komponen) {
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
                ":komponenHarga,\n" +
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
        parameterSource.addValue("komponenHarga", komponen.getKomponenHarga());
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

    public void update(KomponenBelanjaGetDto data) {
        String sql = "UPDATE TMRBABLRINCI " +
                "SET    I_IDKEGIATAN        = :idKegiatan,\n" +
                "       C_ANGG_TAHUN        = :tahunAngg,\n" +
                "       I_IDSKPD            = :idSkpd,\n" +
                "       C_KEGIATAN          = :kodeKegiatan,\n" +
                "       I_IDBAS             = :idBas,\n" +
                "       I_IDBASKOMPONEN     = :idBasKomponen,\n" +
                "       I_ANGG_NOURUT       = :noUrut,\n" +
                "       E_ANGG_RINCI        = :anggaranRinci,\n" +
                "       E_ANGG_SPESIFIKASI  = :spesifikasi,\n" +
                "       C_KOMPONEN          = :kodeKomponen,\n" +
                "       N_KOMPONEN          = :namaKomponen,\n" +
                "       N_KOMPONEN_MERK     = :merk,\n" +
                "       N_KOMPONEN_SPEK     = :spek,\n" +
                "       V_KOMPONEN_HARGA    = :komponenHarga,\n" +
                "       P_KOMPONEN_PAJAK    = :pajak,\n" +
                "       Q_BRG_VOLUME        = :volume,\n" +
                "       N_BRG_KOEFISIEN     = :koefisien,\n" +
                "       Q_BRG_VOLUME1       = :volume1,\n" +
                "       N_BRG_SATUAN1       = :satuan1,\n" +
                "       Q_BRG_VOLUME2       = :volume2,\n" +
                "       N_BRG_SATUAN2       = :satuan2,\n" +
                "       Q_BRG_VOLUME3       = :volume3,\n" +
                "       N_BRG_SATUAN3       = :satuan3,\n" +
                "       Q_BRG_VOLUME4       = :volume4,\n" +
                "       N_BRG_SATUAN4       = :satuan4,\n" +
                "       E_RMKS_SUBRINCIAN   = :rmksSubrincian,\n" +
                "       E_RMKS              = :rmks,\n" +
                "       C_SWAKELOLA         = :swakelola,\n" +
                "       V_ANGG_DPA          = :anggaranDpa,\n" +
                "       V_ANGG_TAPD         = :anggaranTapd,\n" +
                "       I_PGUN_REKAM        = :idPenggunaRekam,\n" +
                "       D_PGUN_REKAM        = :tglPenggunaRekam,\n" +
                "       I_PGUN_UBAH         = :idPenggunaUbah,\n" +
                "       D_REKAM_UBAH        = :tglPenggunaUbah,\n" +
                "       V_RPA_BULAN01       = :rpaBulan1,\n" +
                "       V_RPA_BULAN02       = :rpaBulan2,\n" +
                "       V_RPA_BULAN03       = :rpaBulan3,\n" +
                "       V_RPA_BULAN04       = :rpaBulan4,\n" +
                "       V_RPA_BULAN05       = :rpaBulan5,\n" +
                "       V_RPA_BULAN06       = :rpaBulan6,\n" +
                "       V_RPA_BULAN07       = :rpaBulan7,\n" +
                "       V_RPA_BULAN08       = :rpaBulan8,\n" +
                "       V_RPA_BULAN09       = :rpaBulan9,\n" +
                "       V_RPA_BULAN10       = :rpaBulan10,\n" +
                "       V_RPA_BULAN11       = :rpaBulan11,\n" +
                "       V_RPA_BULAN12       = :rpaBulan12 " +
                "WHERE I_ID = :id";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", data.getId());
        parameterSource.addValue("idKegiatan", data.getIdKegiatan());
        parameterSource.addValue("tahunAngg", data.getTahunAnggaran());
        parameterSource.addValue("idSkpd", data.getIdSkpd());
        parameterSource.addValue("kodeKegiatan", data.getKodeKegiatan());
        parameterSource.addValue("idBas", data.getIdBas());
        parameterSource.addValue("idBasKomponen", data.getIdBasKomponen());
        parameterSource.addValue("noUrut", data.getIdAnggaranNoUrut());
        parameterSource.addValue("anggaranRinci", data.getEntryAnggaranRinci());
        parameterSource.addValue("spesifikasi", data.getEntryAnggaranSpesifikasi());
        parameterSource.addValue("kodeKomponen", data.getKodeKomponen());
        parameterSource.addValue("namaKomponen", data.getNamaKomponen());
        parameterSource.addValue("merk", data.getMerk());
        parameterSource.addValue("spek", data.getSpek());
        parameterSource.addValue("komponenHarga", data.getKomponenHarga());
        parameterSource.addValue("pajak", data.getPajak());
        parameterSource.addValue("volume", data.getVolume());
        parameterSource.addValue("koefisien", data.getKoefisien());
        parameterSource.addValue("volume1", data.getVolume1());
        parameterSource.addValue("satuan1", data.getSatuan1());
        parameterSource.addValue("volume2", data.getVolume2());
        parameterSource.addValue("satuan2", data.getSatuan2());
        parameterSource.addValue("volume3", data.getVolume3());
        parameterSource.addValue("satuan3", data.getSatuan3());
        parameterSource.addValue("volume4", data.getVolume4());
        parameterSource.addValue("satuan4", data.getSatuan4());
        parameterSource.addValue("rmksSubrincian", data.getRmksSubrincian());
        parameterSource.addValue("rmks", data.getRmks());
        parameterSource.addValue("swakelola", data.getSwakelola());
        parameterSource.addValue("anggaranDpa", data.getAnggaranDpa());
        parameterSource.addValue("anggaranTapd", data.getAnggaranTapd());
        parameterSource.addValue("idPenggunaRekam", data.getIdPenggunaRekam());
        parameterSource.addValue("tglPenggunaRekam", data.getTglPenggunaRekam());
        parameterSource.addValue("idPenggunaUbah", data.getIdPenggunaUbah());
        parameterSource.addValue("tglPenggunaUbah", data.getTglPenggunaUbah());
        parameterSource.addValue("rpaBulan1", data.getRpaBulan1());
        parameterSource.addValue("rpaBulan2", data.getRpaBulan2());
        parameterSource.addValue("rpaBulan3", data.getRpaBulan3());
        parameterSource.addValue("rpaBulan4", data.getRpaBulan4());
        parameterSource.addValue("rpaBulan5", data.getRpaBulan5());
        parameterSource.addValue("rpaBulan6", data.getRpaBulan6());
        parameterSource.addValue("rpaBulan7", data.getRpaBulan7());
        parameterSource.addValue("rpaBulan8", data.getRpaBulan8());
        parameterSource.addValue("rpaBulan9", data.getRpaBulan9());
        parameterSource.addValue("rpaBulan10", data.getRpaBulan10());
        parameterSource.addValue("rpaBulan11", data.getRpaBulan11());
        parameterSource.addValue("rpaBulan12", data.getRpaBulan12());

        jdbcTemplate.update(sql, parameterSource);
    }

    public List<KomponenBelanjaGetDto> listByParamater(Integer idKegiatan, String tahungAngg, String tipeKomponen) {
        String query = "SELECT\n" +
                "RINCI.I_ID                   AS idRinci, \n" +
                "RINCI.I_IDKEGIATAN           AS idKegiatan,\n" +
                "RINCI.C_ANGG_TAHUN           AS tahunAngg,\n" +
                "RINCI.I_IDSKPD               AS idSkpd,\n" +
                "RINCI.C_KEGIATAN             AS kodeKegiatan,\n" +
                "RINCI.I_IDBAS                AS idBas,\n" +
                "RINCI.I_IDBASKOMPONEN        AS idBasKomponen,\n" +
                "RINCI.I_ANGG_NOURUT          AS noUrut,\n" +
                "RINCI.E_ANGG_RINCI           AS anggaranRinci,\n" +
                "RINCI.E_ANGG_SPESIFIKASI     AS anggaranSpesifikasi,\n" +
                "RINCI.C_KOMPONEN             AS kodeKomponen,\n" +
                "RINCI.N_KOMPONEN             AS namaKomponen,\n" +
                "RINCI.N_KOMPONEN_MERK        AS merk,\n" +
                "RINCI.N_KOMPONEN_SPEK        AS spek,\n" +
                "RINCI.V_KOMPONEN_HARGA       AS komponenHarga,\n" +
                "RINCI.P_KOMPONEN_PAJAK       AS pajak,\n" +
                "RINCI.Q_BRG_VOLUME           AS volume,\n" +
                "RINCI.N_BRG_KOEFISIEN        AS koefisien,\n" +
                "RINCI.Q_BRG_VOLUME1          AS volume1,\n" +
                "RINCI.N_BRG_SATUAN1          AS satuan1,\n" +
                "RINCI.Q_BRG_VOLUME2          AS volume2,\n" +
                "RINCI.N_BRG_SATUAN2          AS satuan2,\n" +
                "RINCI.Q_BRG_VOLUME3          AS volume3,\n" +
                "RINCI.N_BRG_SATUAN3          AS satuan3,\n" +
                "RINCI.Q_BRG_VOLUME4          AS volume4,\n" +
                "RINCI.N_BRG_SATUAN4          AS satuan4,\n" +
                "RINCI.E_RMKS_SUBRINCIAN      AS rmksSubrincian,\n" +
                "RINCI.E_RMKS                 AS rmks,\n" +
                "RINCI.C_SWAKELOLA            AS swakelola,\n" +
                "RINCI.V_ANGG_DPA             AS anggaranDpa,\n" +
                "RINCI.V_ANGG_TAPD            AS anggaranTapd,\n" +
                "RINCI.I_PGUN_REKAM           AS idPenggunaRekam,\n" +
                "RINCI.D_PGUN_REKAM           AS tglPenggunaRekam,\n" +
                "RINCI.I_PGUN_UBAH            AS idPenggunaUbah,\n" +
                "RINCI.D_REKAM_UBAH           AS tglPenggunaUbah,\n" +
                "RINCI.V_RPA_BULAN01          AS rpaBulan1,\n" +
                "RINCI.V_RPA_BULAN02          AS rpaBulan2,\n" +
                "RINCI.V_RPA_BULAN03          AS rpaBulan3,\n" +
                "RINCI.V_RPA_BULAN04          AS rpaBulan4,\n" +
                "RINCI.V_RPA_BULAN05          AS rpaBulan5,\n" +
                "RINCI.V_RPA_BULAN06          AS rpaBulan6,\n" +
                "RINCI.V_RPA_BULAN07          AS rpaBulan7,\n" +
                "RINCI.V_RPA_BULAN08          AS rpaBulan8,\n" +
                "RINCI.V_RPA_BULAN09          AS rpaBulan9,\n" +
                "RINCI.V_RPA_BULAN10          AS rpaBulan10,\n" +
                "RINCI.V_RPA_BULAN11          AS rpaBulan11,\n" +
                "RINCI.V_RPA_BULAN12          AS rpaBulan12,\n" +
                "TRB.C_AKUN                   AS kodeAkun\n" +
                "FROM TMRBABLRINCI RINCI JOIN TRBAS TRB ON TRB.I_ID = RINCI.I_IDBAS " +
                "WHERE I_IDKEGIATAN = :idKegiatan AND C_ANGG_TAHUN = :tahunAngg AND C_AKUN LIKE :kodeAkun";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idKegiatan", idKegiatan);
        parameterSource.addValue("tahunAngg", tahungAngg);
//        parameterSource.addValue("idBas", idBasList);
        switch (tipeKomponen) {
            case "pegawai": {
                parameterSource.addValue("kodeAkun", "5.2.1%");
                break;
            }
            case "barang": {
                parameterSource.addValue("kodeAkun", "5.2.2%");
                break;
            }
            case "modal": {
                parameterSource.addValue("kodeAkun", "5.2.3%");
                break;
            }
            default:
                break;
        }

        return jdbcTemplate.query(query, parameterSource, (resultSet, i) -> {
            KomponenBelanjaGetDto komponenBelanja = new KomponenBelanjaGetDto();
            komponenBelanja.setId(resultSet.getInt("idRinci"));
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
            komponenBelanja.setKomponenHarga(resultSet.getBigDecimal("komponenHarga"));
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
            komponenBelanja.setKodeAkun(resultSet.getString("kodeAkun"));
            return komponenBelanja;
        });

    }

    public KomponenBelanjaGetDto getKomponenGetById(Integer id) {
        String sql = "SELECT\n" +
                "TRB.N_AKUN                   AS namaAkun, \n" +
                "TRB.C_AKUN                   AS kodeAkun, \n" +
                "TRK.C_KOMPONEN               AS kodeKomponen, \n" +
                "TRK.N_KOMPONEN               AS namaKomponen, \n" +
                "TRK.N_SATUAN                 AS satuan, \n" +
                "RINCI.I_ID                   AS idRinci, \n" +
                "RINCI.I_IDKEGIATAN           AS idKegiatan,\n" +
                "RINCI.C_ANGG_TAHUN           AS tahunAngg,\n" +
                "RINCI.I_IDSKPD               AS idSkpd,\n" +
                "RINCI.C_KEGIATAN             AS kodeKegiatan,\n" +
                "RINCI.I_IDBAS                AS idBas,\n" +
                "RINCI.I_IDBASKOMPONEN        AS idBasKomponen,\n" +
                "RINCI.I_ANGG_NOURUT          AS noUrut,\n" +
                "RINCI.E_ANGG_RINCI           AS anggaranRinci,\n" +
                "RINCI.E_ANGG_SPESIFIKASI     AS anggaranSpesifikasi,\n" +
                "RINCI.C_KOMPONEN             AS kodeKomponen,\n" +
                "RINCI.N_KOMPONEN             AS namaKomponen,\n" +
                "RINCI.N_KOMPONEN_MERK        AS merk,\n" +
                "RINCI.N_KOMPONEN_SPEK        AS spek,\n" +
                "RINCI.V_KOMPONEN_HARGA       AS komponenHarga,\n" +
                "RINCI.P_KOMPONEN_PAJAK       AS pajak,\n" +
                "RINCI.Q_BRG_VOLUME           AS volume,\n" +
                "RINCI.N_BRG_KOEFISIEN        AS koefisien,\n" +
                "RINCI.Q_BRG_VOLUME1          AS volume1,\n" +
                "RINCI.N_BRG_SATUAN1          AS satuan1,\n" +
                "RINCI.Q_BRG_VOLUME2          AS volume2,\n" +
                "RINCI.N_BRG_SATUAN2          AS satuan2,\n" +
                "RINCI.Q_BRG_VOLUME3          AS volume3,\n" +
                "RINCI.N_BRG_SATUAN3          AS satuan3,\n" +
                "RINCI.Q_BRG_VOLUME4          AS volume4,\n" +
                "RINCI.N_BRG_SATUAN4          AS satuan4,\n" +
                "RINCI.E_RMKS_SUBRINCIAN      AS rmksSubrincian,\n" +
                "RINCI.E_RMKS                 AS rmks,\n" +
                "RINCI.C_SWAKELOLA            AS swakelola,\n" +
                "RINCI.V_ANGG_DPA             AS anggaranDpa,\n" +
                "RINCI.V_ANGG_TAPD            AS anggaranTapd,\n" +
                "RINCI.I_PGUN_REKAM           AS idPenggunaRekam,\n" +
                "RINCI.D_PGUN_REKAM           AS tglPenggunaRekam,\n" +
                "RINCI.I_PGUN_UBAH            AS idPenggunaUbah,\n" +
                "RINCI.D_REKAM_UBAH           AS tglPenggunaUbah,\n" +
                "RINCI.V_RPA_BULAN01          AS rpaBulan1,\n" +
                "RINCI.V_RPA_BULAN02          AS rpaBulan2,\n" +
                "RINCI.V_RPA_BULAN03          AS rpaBulan3,\n" +
                "RINCI.V_RPA_BULAN04          AS rpaBulan4,\n" +
                "RINCI.V_RPA_BULAN05          AS rpaBulan5,\n" +
                "RINCI.V_RPA_BULAN06          AS rpaBulan6,\n" +
                "RINCI.V_RPA_BULAN07          AS rpaBulan7,\n" +
                "RINCI.V_RPA_BULAN08          AS rpaBulan8,\n" +
                "RINCI.V_RPA_BULAN09          AS rpaBulan9,\n" +
                "RINCI.V_RPA_BULAN10          AS rpaBulan10,\n" +
                "RINCI.V_RPA_BULAN11          AS rpaBulan11,\n" +
                "RINCI.V_RPA_BULAN12          AS rpaBulan12\n" +
                "FROM TMRBABLRINCI RINCI \n" +
                "JOIN TRBAS TRB ON RINCI.I_IDBAS = TRB.I_ID\n" +
                "JOIN TRBASKOMPONEN TRK ON RINCI.I_IDBASKOMPONEN = TRK.I_ID\n" +
                "WHERE RINCI.I_ID = :idRinci";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idRinci", id);

        return jdbcTemplate.queryForObject(sql, parameterSource, (resultSet, i) -> {
            KomponenBelanjaGetDto komponenBelanja = new KomponenBelanjaGetDto();
            komponenBelanja.setId(resultSet.getInt("idRinci"));
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
            komponenBelanja.setKomponenHarga(resultSet.getBigDecimal("komponenHarga"));
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
            komponenBelanja.setKodeAkun(resultSet.getString("kodeAkun"));
            komponenBelanja.setNamaAkun(resultSet.getString("namaAkun"));
            komponenBelanja.setKodeKomponen(resultSet.getString("kodeKomponen"));
            komponenBelanja.setNamaKomponen(resultSet.getString("namaKomponen"));
            return komponenBelanja;
        });
    }

    public KomponenBelanjaEditDto getKomponenEditById(Integer id) {
        String sql = "SELECT " +
                "RINCI.I_ID                 AS id, \n" +
                "TRB.N_AKUN                 AS namaAkun, \n" +
                "TRB.C_AKUN                 AS kodeAkun, \n" +
                "TRK.C_KOMPONEN             AS kodeKomponen, \n" +
                "TRK.N_KOMPONEN             AS namaKomponen, \n" +
                "TRK.N_SATUAN               AS satuan, \n" +
                "RINCI.N_KOMPONEN_SPEK      AS spek, \n" +
                "RINCI.N_KOMPONEN_MERK      AS merk,\n" +
                "RINCI.V_KOMPONEN_HARGA     AS komponenHarga,\n" +
                "RINCI.Q_BRG_VOLUME         AS volume, \n" +
                "RINCI.N_BRG_KOEFISIEN      AS koefisien,\n" +
                "RINCI.Q_BRG_VOLUME1        AS volume1,\n" +
                "RINCI.N_BRG_SATUAN1        AS satuan1,\n" +
                "RINCI.Q_BRG_VOLUME2        AS volume2,\n" +
                "RINCI.N_BRG_SATUAN2        AS satuan2,\n" +
                "RINCI.Q_BRG_VOLUME3        AS volume3,\n" +
                "RINCI.N_BRG_SATUAN3        AS satuan3,\n" +
                "RINCI.Q_BRG_VOLUME4        AS volume4,\n" +
                "RINCI.N_BRG_SATUAN4        AS satuan4,\n" +
                "RINCI.P_KOMPONEN_PAJAK     AS pajak \n" +
                "FROM TMRBABLRINCI RINCI \n" +
                "JOIN TRBAS TRB ON RINCI.I_IDBAS = TRB.I_ID\n" +
                "JOIN TRBASKOMPONEN TRK ON RINCI.I_IDBASKOMPONEN = TRK.I_ID\n" +
                "WHERE RINCI.I_ID = :id";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

        return jdbcTemplate.queryForObject(sql, parameterSource, new RowMapper<KomponenBelanjaEditDto>() {
            @Override
            public KomponenBelanjaEditDto mapRow(ResultSet resultSet, int i) throws SQLException {
                KomponenBelanjaEditDto komponen = new KomponenBelanjaEditDto();
                komponen.setId(resultSet.getInt("id"));
                komponen.setNamaAkun(resultSet.getString("namaAkun"));
                komponen.setKodeAkun(resultSet.getString("kodeAkun"));
                komponen.setKodeKomponen(resultSet.getString("kodeKomponen"));
                komponen.setNamaKomponen(resultSet.getString("namaKomponen"));
                komponen.setSatuan(resultSet.getString("satuan"));
                komponen.setSpek(resultSet.getString("spek"));
                komponen.setMerk(resultSet.getString("merk"));
                komponen.setKomponenHarga(resultSet.getBigDecimal("komponenHarga"));
                komponen.setVolume(resultSet.getInt("volume"));
                komponen.setKoefisien(resultSet.getString("koefisien"));
                komponen.setVolume1(resultSet.getInt("volume1"));
                komponen.setSatuan1(resultSet.getString("satuan1"));
                komponen.setVolume2(resultSet.getInt("volume2"));
                komponen.setSatuan2(resultSet.getString("satuan2"));
                komponen.setVolume3(resultSet.getInt("volume3"));
                komponen.setSatuan3(resultSet.getString("satuan3"));
                komponen.setVolume4(resultSet.getInt("volume4"));
                komponen.setSatuan4(resultSet.getString("satuan4"));
                komponen.setPajak(resultSet.getInt("pajak"));
                return komponen;
            }
        });
    }

    public int updateRinci(KomponenBelanjaGetDto data, Integer createdBy, Timestamp createdDate) throws DataAccessException {
        String sql = "UPDATE TMRBABLRINCI\n" +
                "SET V_RPA_BULAN01 = :rpaBulan1,\n" +
                "    V_RPA_BULAN02 = :rpaBulan2,\n" +
                "    V_RPA_BULAN03 = :rpaBulan3,\n" +
                "    V_RPA_BULAN04 = :rpaBulan4,\n" +
                "    V_RPA_BULAN05 = :rpaBulan5,\n" +
                "    V_RPA_BULAN06 = :rpaBulan6,\n" +
                "    V_RPA_BULAN07 = :rpaBulan7,\n" +
                "    V_RPA_BULAN08 = :rpaBulan8,\n" +
                "    V_RPA_BULAN09 = :rpaBulan9,\n" +
                "    V_RPA_BULAN10 = :rpaBulan10,\n" +
                "    V_RPA_BULAN11 = :rpaBulan11,\n" +
                "    V_RPA_BULAN12 = :rpaBulan12,\n" +
                "    I_PGUN_UBAH   = :createdBy,\n" +
                "    D_REKAM_UBAH  = :createdDate\n" +
                "WHERE I_ID = :vId";
        Map<String, Object> params = new HashedMap<>();
        params.put("rpaBulan1", data.getRpaBulan1());
        params.put("rpaBulan2", data.getRpaBulan2());
        params.put("rpaBulan3", data.getRpaBulan3());
        params.put("rpaBulan4", data.getRpaBulan4());
        params.put("rpaBulan5", data.getRpaBulan5());
        params.put("rpaBulan6", data.getRpaBulan6());
        params.put("rpaBulan7", data.getRpaBulan7());
        params.put("rpaBulan8", data.getRpaBulan8());
        params.put("rpaBulan9", data.getRpaBulan9());
        params.put("rpaBulan10", data.getRpaBulan10());
        params.put("rpaBulan11", data.getRpaBulan11());
        params.put("rpaBulan12", data.getRpaBulan12());
        params.put("createdBy", createdBy);
        params.put("createdDate", createdDate);
        params.put("vId", data.getId());
        return this.jdbcTemplate.update(sql, params);
    }


    public void updateAnggaranKegiatan(Integer id, BigDecimal anggaran) {
        String sql = "UPDATE TMRBAKEGIATAN SET V_ANGG_TAPD = :anggaran, V_ANGG_DPA = :anggaran WHERE I_ID = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        parameterSource.addValue("anggaran", anggaran);
        jdbcTemplate.update(sql, parameterSource);
    }



}
