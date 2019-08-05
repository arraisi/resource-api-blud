package com.tabeldata.service;

import com.tabeldata.dao.ProgramDao;
import com.tabeldata.entity.ProgramEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProgramService {

    @Autowired
    private ProgramDao dao;

    public List<ProgramEntity> getListProgram() {
        List<ProgramEntity> program = dao.getListProgram();
        return program;
    }
}
