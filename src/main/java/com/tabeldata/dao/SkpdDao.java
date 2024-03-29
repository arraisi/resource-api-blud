package com.tabeldata.dao;

import com.tabeldata.dto.SkpdPersetujuanDto;
import com.tabeldata.entity.SkpdEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
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

    public List<SkpdPersetujuanDto> getListSkpdPersetujuan(String tahunAnggaran) throws DataAccessException {
        String sql = "select skpd.I_ID                       AS id,\n" +
                "       skpd.I_IDINDUK                  AS idInduk,\n" +
                "       skpd.C_SKPD                     AS kodeSkpd,\n" +
                "       skpd.C_UNITKERJA                AS kodeUnitKerja,\n" +
                "       skpd.N_SKPD                     AS namaSkpd,\n" +
                "       skpd.N_SKPD_PENDEK              AS namaSingkatSkpd,\n" +
                "       skpd.C_BLUD                     AS blud,\n" +
                "       skpd.C_AKTIF                    AS statusAktif,\n" +
                "       skpd.C_TAHUN_BERLAKU            AS tahunBerlaku,\n" +
                "       skpd.C_TAHUN_BERAKHIR           AS tahunBerakhir,\n" +
                "       skpd.I_PGUN_REKAM               AS idRekamPengguna,\n" +
                "       skpd.D_PGUN_REKAM               AS tanggalRekamPengguna,\n" +
                "       skpd.I_PGUN_UBAH                AS idUbahPengguna,\n" +
                "       skpd.D_REKAM_UBAH               AS tanggalUbahPengguna,\n" +
                "       (select SUM(NVL(kegiatan.V_ANGG_TAPD, 0))\n" +
                "        from TRRBASKPD sk\n" +
                "                 join TMRBAKEGIATAN kegiatan on sk.I_ID = kegiatan.I_IDSKPD\n" +
                "        where sk.I_ID = skpd.I_ID\n" +
                "          and C_ANGG_TAHUN = :tahun)   AS totalAnggaran,\n" +
                "       (select r.C_STATDINAS_APPV\n" +
                "        from TMRBA r\n" +
                "        where r.I_IDSKPD = skpd.I_ID\n" +
                "          and r.C_ANGG_TAHUN = :tahun) AS statusDinas\n" +
                "from TRRBASKPD skpd\n" +
                "where skpd.C_AKTIF = '1'\n" +
                "  and skpd.C_BLUD = '1'";
        Map<String, Object> param = new HashMap<>();
        param.put("tahun", tahunAnggaran);
        return this.namedParameterJdbcTemplate.query(sql, param, (rs, i) -> {
            SkpdPersetujuanDto skpdEntity = new SkpdPersetujuanDto();
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
            skpdEntity.setTotalAnggaran(rs.getBigDecimal("totalAnggaran"));
            skpdEntity.setStatusDinasId(rs.getString("statusDinas"));
            skpdEntity.setStatusDinasName(mapperStatusAppv(rs.getString("statusDinas") != null ? rs.getString("statusDinas") : "-"));
            skpdEntity.setStatusDinasBadge(mapperStatusAppvColorBadge(rs.getString("statusDinas") != null ? rs.getString("statusDinas") : "-"));
            return skpdEntity;
        });
    }

    public List<SkpdPersetujuanDto> getListSkpdPersetujuanByIdSkpd(String tahunAnggaran, Integer skpdId) throws DataAccessException {
        String sql = "select skpd.I_ID                       AS id,\n" +
                "       skpd.I_IDINDUK                  AS idInduk,\n" +
                "       skpd.C_SKPD                     AS kodeSkpd,\n" +
                "       skpd.C_UNITKERJA                AS kodeUnitKerja,\n" +
                "       skpd.N_SKPD                     AS namaSkpd,\n" +
                "       skpd.N_SKPD_PENDEK              AS namaSingkatSkpd,\n" +
                "       skpd.C_BLUD                     AS blud,\n" +
                "       skpd.C_AKTIF                    AS statusAktif,\n" +
                "       skpd.C_TAHUN_BERLAKU            AS tahunBerlaku,\n" +
                "       skpd.C_TAHUN_BERAKHIR           AS tahunBerakhir,\n" +
                "       skpd.I_PGUN_REKAM               AS idRekamPengguna,\n" +
                "       skpd.D_PGUN_REKAM               AS tanggalRekamPengguna,\n" +
                "       skpd.I_PGUN_UBAH                AS idUbahPengguna,\n" +
                "       skpd.D_REKAM_UBAH               AS tanggalUbahPengguna,\n" +
                "       (select SUM(NVL(kegiatan.V_ANGG_TAPD, 0))\n" +
                "        from TRRBASKPD sk\n" +
                "                 join TMRBAKEGIATAN kegiatan on sk.I_ID = kegiatan.I_IDSKPD\n" +
                "        where sk.I_ID = skpd.I_ID\n" +
                "          and C_ANGG_TAHUN = :tahun)   AS totalAnggaran,\n" +
                "       (select r.C_STATDINAS_APPV\n" +
                "        from TMRBA r\n" +
                "        where r.I_IDSKPD = skpd.I_ID\n" +
                "          and r.C_ANGG_TAHUN = :tahun) AS statusDinas\n" +
                "from TRRBASKPD skpd\n" +
                "where skpd.C_AKTIF = '1'\n" +
                "  and skpd.C_BLUD = '1'\n" +
                "  and skpd.I_ID = :vSkpdId";
        Map<String, Object> param = new HashMap<>();
        param.put("tahun", tahunAnggaran);
        param.put("vSkpdId", skpdId);
        return this.namedParameterJdbcTemplate.query(sql, param, (rs, i) -> {
            SkpdPersetujuanDto skpdEntity = new SkpdPersetujuanDto();
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
            skpdEntity.setTotalAnggaran(rs.getBigDecimal("totalAnggaran"));
            skpdEntity.setStatusDinasId(rs.getString("statusDinas"));
            skpdEntity.setStatusDinasName(mapperStatusAppv(rs.getString("statusDinas") != null ? rs.getString("statusDinas") : "-"));
            skpdEntity.setStatusDinasBadge(mapperStatusAppvColorBadge(rs.getString("statusDinas") != null ? rs.getString("statusDinas") : "-"));
            return skpdEntity;
        });
    }


    public String mapperStatusAppv(String id) {
        String status;
        switch (id) {
            case "0":
                status = "Baru";
                break;
            case "1":
                status = "Dikirim";
                break;
            case "2":
                status = "Diterima";
                break;
            case "3":
                status = "Ditolak";
                break;
            case "4":
                status = "Revisi";
                break;
            default:
                status = "Tidak Ditemukan";
        }
        return status;
    }

    public String mapperStatusAppvColorBadge(String id) {
        String statusColor;
        switch (id) {
            case "0":
                statusColor = "badge-secondary";
                break;
            case "1":
                statusColor = "badge-green";
                break;
            case "2":
                statusColor = "badge-blue";
                break;
            case "3":
                statusColor = "badge-red";
                break;
            case "4":
                statusColor = "badge-yellow";
                break;
            default:
                statusColor = "badge-secondary";
        }
        return statusColor;
    }
}
