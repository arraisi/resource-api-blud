package com.tabeldata.dao;

import com.tabeldata.dto.LoadKomponenDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class LoadKomponenDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    String QUERY_BELANJA_PEGAWAI = "SELECT TRB.I_ID             AS idBas\n" +
            "    ,TRB.C_AKUN             AS kodeAkun\n" +
            "    ,TRB.N_AKUN             AS namaAkun\n" +
            "    ,TRB.C_AKUN_LRA         AS kodeAkunLra\n" +
            "    ,TRB.N_AKUN_LRA         AS namaAkunLra\n" +
            "     , TRK.I_ID             AS idBasKomponen\n" +
            "     , TRK.C_KOMPONEN       AS kodeKomponen\n" +
            "     , TRK.N_KOMPONEN       AS namaKomponen\n" +
            "     , TRK.N_SATUAN         AS namaSatuan\n" +
            "     , TRK.V_KOMPONEN_HARGA AS komponenHarga\n" +
            "     , TRK.N_KOMPONEN_MERK  AS komponenMerk\n" +
            "     , TRK.N_KOMPONEN_SPEK  AS komponenSpek\n" +
            "FROM TRBAS \"TRB\"\n" +
            "         INNER JOIN TRBASKOMPONEN \"TRK\" ON TRK.I_IDBAS = TRB.I_ID\n" +
            "         INNER JOIN TRRBASKPDBAS \"TBS\" ON TBS.I_IDBAS = TRK.I_IDBAS\n" +
            "         INNER JOIN TRRBASKPD \"TBP\" ON TBP.I_ID = TBS.I_IDSKPD\n" +
            "WHERE (\n" +
            "        TO_NUMBER(:vTahunAnggaran) BETWEEN TO_NUMBER(TRB.C_TAHUN_BERLAKU) AND TO_NUMBER(TRB.C_TAHUN_BERAKHIR)\n" +
            "        AND TRB.C_AKTIF = '1'\n" +
            "        AND TRB.C_DETAIL = '1'\n" +
            "        AND TRB.C_AKUN LIKE '5.2.1%'\n" +
            "    )\n" +
            "  AND (\n" +
            "        TRK.C_AKTIF = '1'\n" +
            "        AND TRK.C_APPROVAL = '1'\n" +
            "        AND TRK.C_AKUN LIKE '5.2.1%'\n" +
            "    )\n" +
            "  AND (\n" +
            "        TBS.C_AKTIF = '1'\n" +
            "        AND TO_NUMBER(:vTahunAnggaran) BETWEEN TO_NUMBER(TBS.C_TAHUN_BERLAKU) AND TO_NUMBER(TBS.C_TAHUN_BERAKHIR)\n" +
            "    )\n" +
            "  AND (\n" +
            "        TBP.C_AKTIF = '1'\n" +
            "        AND TBP.C_BLUD = '1'\n" +
            "        AND TBP.I_ID = :vIdSkpd\n" +
            "    ) ";
    String QUERY_BELANJA_BARANG_JASA = "SELECT TRB.I_ID             AS idBas\n" +
            "    ,TRB.C_AKUN             AS kodeAkun\n" +
            "    ,TRB.N_AKUN             AS namaAkun\n" +
            "    ,TRB.C_AKUN_LRA         AS kodeAkunLra\n" +
            "    ,TRB.N_AKUN_LRA         AS namaAkunLra\n" +
            "     , TRK.I_ID             AS idBasKomponen\n" +
            "     , TRK.C_KOMPONEN       AS kodeKomponen\n" +
            "     , TRK.N_KOMPONEN       AS namaKomponen\n" +
            "     , TRK.N_SATUAN         AS namaSatuan\n" +
            "     , TRK.V_KOMPONEN_HARGA AS komponenHarga\n" +
            "     , TRK.N_KOMPONEN_MERK  AS komponenMerk\n" +
            "     , TRK.N_KOMPONEN_SPEK  AS komponenSpek\n" +
            "FROM TRBAS \"TRB\"\n" +
            "         INNER JOIN TRBASKOMPONEN \"TRK\" ON TRK.I_IDBAS = TRB.I_ID\n" +
            "         INNER JOIN TRRBASKPDBAS \"TBS\" ON TBS.I_IDBAS = TRK.I_IDBAS\n" +
            "         INNER JOIN TRRBASKPD \"TBP\" ON TBP.I_ID = TBS.I_IDSKPD\n" +
            "WHERE (\n" +
            "        TO_NUMBER(:vTahunAnggaran) BETWEEN TO_NUMBER(TRB.C_TAHUN_BERLAKU) AND TO_NUMBER(TRB.C_TAHUN_BERAKHIR)\n" +
            "        AND TRB.C_AKTIF = '1'\n" +
            "        AND TRB.C_DETAIL = '1'\n" +
            "        AND TRB.C_AKUN LIKE '5.2.2%'\n" +
            "    )\n" +
            "  AND (\n" +
            "        TRK.C_AKTIF = '1'\n" +
            "        AND TRK.C_APPROVAL = '1'\n" +
            "        AND TRK.C_AKUN LIKE '5.2.2%'\n" +
            "    )\n" +
            "  AND (\n" +
            "        TBS.C_AKTIF = '1'\n" +
            "        AND TO_NUMBER(:vTahunAnggaran) BETWEEN TO_NUMBER(TBS.C_TAHUN_BERLAKU) AND TO_NUMBER(TBS.C_TAHUN_BERAKHIR)\n" +
            "    )\n" +
            "  AND (\n" +
            "        TBP.C_AKTIF = '1'\n" +
            "        AND TBP.C_BLUD = '1'\n" +
            "        AND TBP.I_ID = :vIdSkpd\n" +
            "    ) ";
    //language=Oracle
    String QUERY_BELANJA_MODAL = "SELECT TRB.I_ID             AS idBas\n" +
            "    ,TRB.C_AKUN             AS kodeAkun\n" +
            "    ,TRB.N_AKUN             AS namaAkun\n" +
            "    ,TRB.C_AKUN_LRA         AS kodeAkunLra\n" +
            "    ,TRB.N_AKUN_LRA         AS namaAkunLra\n" +
            "     , TRK.I_ID             AS idBasKomponen\n" +
            "     , TRK.C_KOMPONEN       AS kodeKomponen\n" +
            "     , TRK.N_KOMPONEN       AS namaKomponen\n" +
            "     , TRK.N_SATUAN         AS namaSatuan\n" +
            "     , TRK.V_KOMPONEN_HARGA AS komponenHarga\n" +
            "     , TRK.N_KOMPONEN_MERK  AS komponenMerk\n" +
            "     , TRK.N_KOMPONEN_SPEK  AS komponenSpek\n" +
            "FROM TRBAS \"TRB\"\n" +
            "         INNER JOIN TRBASKOMPONEN \"TRK\" ON TRK.I_IDBAS = TRB.I_ID\n" +
            "         INNER JOIN TRRBASKPDBAS \"TBS\" ON TBS.I_IDBAS = TRK.I_IDBAS\n" +
            "         INNER JOIN TRRBASKPD \"TBP\" ON TBP.I_ID = TBS.I_IDSKPD\n" +
            "WHERE (\n" +
            "        TO_NUMBER(:vTahunAnggaran) BETWEEN TO_NUMBER(TRB.C_TAHUN_BERLAKU) AND TO_NUMBER(TRB.C_TAHUN_BERAKHIR)\n" +
            "        AND TRB.C_AKTIF = '1'\n" +
            "        AND TRB.C_DETAIL = '1'\n" +
            "        AND TRB.C_AKUN LIKE '5.2.3%'\n" +
            "    )\n" +
            "  AND (\n" +
            "        TRK.C_AKTIF = '1'\n" +
            "        AND TRK.C_APPROVAL = '1'\n" +
            "        AND TRK.C_AKUN LIKE '5.2.3%'\n" +
            "    )\n" +
            "  AND (\n" +
            "        TBS.C_AKTIF = '1'\n" +
            "        AND TO_NUMBER(:vTahunAnggaran) BETWEEN TO_NUMBER(TBS.C_TAHUN_BERLAKU) AND TO_NUMBER(TBS.C_TAHUN_BERAKHIR)\n" +
            "    )\n" +
            "  AND (\n" +
            "        TBP.C_AKTIF = '1'\n" +
            "        AND TBP.C_BLUD = '1'\n" +
            "        AND TBP.I_ID = :vIdSkpd\n" +
            "    ) ";


    public List<LoadKomponenDto> loadKomponen(String type, String tahunAnggaran, Integer idSkpd) {
        String sql = "";
        switch (type) {
            case "PEGAWAI":
                sql = QUERY_BELANJA_PEGAWAI; // Digunakan Jika Tab Pada Komponen Nya Adalah Tab Belanja Pegawai
                break;
            case "BARANG":
                sql = QUERY_BELANJA_BARANG_JASA; // Digunakan Jika Tab Pada Komponen Nya Adalah Tab Belanja Barang & Jasa
                break;
            case "MODAL":
                sql = QUERY_BELANJA_MODAL; // Digunakan Jika Tab Pada Komponen Nya Adalah Tab Belanja Modal
                break;
        }
        Map<String, Object> params = new HashedMap<>();
        params.put("vTahunAnggaran", tahunAnggaran);
        params.put("vIdSkpd", idSkpd);

        return this.namedParameterJdbcTemplate.query(sql, params, (rs, i) -> {
            LoadKomponenDto data = new LoadKomponenDto();
            data.setIdBas(rs.getInt("idBas"));
            data.setKodeAkun(rs.getString("kodeAkun"));
            data.setNamaAkun(rs.getString("namaAkun"));
            data.setKodeAkunLra(rs.getString("kodeAkunLra"));
            data.setNamaAkunLra(rs.getString("namaAkunLra"));
            data.setIdBasKomponen(rs.getInt("idBasKomponen"));
            data.setKodeKomponen(rs.getString("kodeKomponen"));
            data.setKomponenHarga(rs.getBigDecimal("komponenHarga"));
            data.setKomponenMerk(rs.getString("komponenMerk"));
            data.setKomponenSpek(rs.getString("komponenSpek"));
            data.setNamaSatuan(rs.getString("namaSatuan"));
            return data;
        });
    }
}
