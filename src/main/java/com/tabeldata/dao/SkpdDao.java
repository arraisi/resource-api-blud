package com.tabeldata.dao;

import com.tabeldata.entity.SkpdEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class SkpdDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SkpdEntity getSkpdById(Integer skpdId) throws EmptyResultDataAccessException {
        String sql = "select I_ID             AS id,\n" +
                "       I_IDINDUK        AS idInduk,\n" +
                "       C_SKPD           AS kodeSkpd,\n" +
                "       C_UNITKERJA      AS kodeUnitKerja,\n" +
                "       N_SKPD           AS namaSkpd,\n" +
                "       N_SKPD_PENDEK    AS namaSingkatSkpd,\n" +
                "       C_BLUD           AS blud,\n" +
                "       C_AKTIF          AS statusAktif,\n" +
                "       C_TAHUN_BERLAKU  AS tahunBerlaku,\n" +
                "       C_TAHUN_BERAKHIR AS tahunBerakhir,\n" +
                "       I_PGUN_REKAM     AS idRekamPengguna,\n" +
                "       D_PGUN_REKAM     AS tanggalRekamPengguna,\n" +
                "       I_PGUN_UBAH      AS idUbahPengguna,\n" +
                "       D_REKAM_UBAH     AS tanggalUbahPengguna\n" +
                "from TRRBASKPD\n" +
                "WHERE I_ID = :vId";
        Map<String, Object> param = new HashMap<>();
        param.put("vId", skpdId);
        return this.namedParameterJdbcTemplate.queryForObject(sql, param, (rs, i) -> {
            SkpdEntity skpdEntity = new SkpdEntity();
            skpdEntity.setId(rs.getInt("id"));
            skpdEntity.setIdInduk(rs.getInt("idInduk"));
            skpdEntity.setKodeSkpd(rs.getString("kodeSkpd"));
            skpdEntity.setKodeUnitKerja(rs.getString("kodeUnitKerja"));
            skpdEntity.setNamaSkpd(rs.getString("namaSkpd"));
            skpdEntity.setNamaSingkatSkpd(rs.getString("namaSingkatSkpd"));
            skpdEntity.setBlud(rs.getString("blud"));
            skpdEntity.setStatusAktif(rs.getString("statusAktif"));
            skpdEntity.setTahunBerlaku(rs.getString("tahunBerlaku"));
            skpdEntity.setTahunBerakhir(rs.getString("tahunBerakhir"));
            skpdEntity.setIdRekamPengguna(rs.getInt("idRekamPengguna"));
            skpdEntity.setTanggalRekamPengguna(rs.getTimestamp("tanggalRekamPengguna"));
            skpdEntity.setIdUbahPengguna(rs.getInt("idUbahPengguna"));
            skpdEntity.setTanggalUbahPengguna(rs.getTimestamp("tanggalUbahPengguna"));
            return skpdEntity;
        });
    }
}
