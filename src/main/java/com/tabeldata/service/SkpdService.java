package com.tabeldata.service;

import com.tabeldata.dao.SkpdDao;
import com.tabeldata.dto.SkpdPersetujuanDto;
import com.tabeldata.entity.SkpdEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SkpdService {

    @Autowired
    private SkpdDao dao;

    public SkpdEntity getSkpdById(Integer skpdId) throws EmptyResultDataAccessException {
        SkpdEntity value = dao.getSkpdById(skpdId);
        return value;
    }

    public List<SkpdPersetujuanDto> getListSkpdPersetujuan(String tahunAnggaran) throws DataAccessException {
        List<SkpdPersetujuanDto> value = dao.getListSkpdPersetujuan(tahunAnggaran);
        return value;
    }

}
