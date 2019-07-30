package com.tabeldata.service;

import com.tabeldata.dao.SkpdDao;
import com.tabeldata.entity.SkpdEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SkpdService {

    @Autowired
    private SkpdDao dao;

    public SkpdEntity getSkpdById(Integer skpdId) throws EmptyResultDataAccessException {
        SkpdEntity value = dao.getSkpdById(skpdId);
        return value;
    }

}
