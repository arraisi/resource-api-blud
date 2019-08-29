package com.tabeldata.service;

import com.tabeldata.dao.SkpdDao;
import com.tabeldata.dto.DataPenggunaLogin;
import com.tabeldata.dto.SkpdPersetujuanDto;
import com.tabeldata.entity.SkpdEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Slf4j
@Service
public class SkpdService {

    @Autowired
    private SkpdDao dao;

    @Autowired
    private DataPenggunaLoginService dataPenggunaLoginService;

    public SkpdEntity getSkpdById(Integer skpdId) throws EmptyResultDataAccessException {
        SkpdEntity value = dao.getSkpdById(skpdId);
        return value;
    }

    public List<SkpdPersetujuanDto> getListSkpdPersetujuan(String tahunAnggaran, Principal principal) throws DataAccessException {
        DataPenggunaLogin pengguna = dataPenggunaLoginService.getDataPenggunaLogin(principal.getName());
        List<SkpdPersetujuanDto> value;
        if (pengguna.getOtor().equals("3")) {
            value = dao.getListSkpdPersetujuan(tahunAnggaran);
        } else {
            value = dao.getListSkpdPersetujuanByIdSkpd(tahunAnggaran, pengguna.getIdSkpd());
        }
        return value;
    }

}
