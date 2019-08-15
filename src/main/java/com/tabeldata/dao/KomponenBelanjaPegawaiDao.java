package com.tabeldata.dao;

import com.tabeldata.entity.KomponenBelanjaPegawaiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class KomponenBelanjaPegawaiDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public void saveKomponenBelanjaPegawai(List<KomponenBelanjaPegawaiEntity> komponen) {
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

        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(komponen);

        this.jdbcTemplate.batchUpdate(query, batch);
    }

    public Integer getNoUrut(Integer idKegiatan, String tahunAnggaran, Integer idBas) {
        String query = "SELECT I_ANGG_NOURUT AS noUrut FROM TMRBABLRINCI WHERE I_IDKEGIATAN = :idKegiatan " +
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
            return noUrut.get(0);
        } else {
            return 1;
        }

    }

    public void updateNoUrut(Integer updateId, Integer idKegiatan, String tahunAnggaran, Integer idBas) {
        String query = "UPDATE TMRBABLRINCI SET I_ANGG_NOURUT = :updateId  WHERE I_IDKEGIATAN = :idKegiatan " +
                "AND C_ANGG_TAHUN = :tahunAngg AND I_IDBAS = :idBas";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("updateId", updateId);
        parameterSource.addValue("idKegiatan", idKegiatan);
        parameterSource.addValue("tahunAngg", tahunAnggaran);
        parameterSource.addValue("idBas", idBas);

        jdbcTemplate.update(query, parameterSource);

    }


}
