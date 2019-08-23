package com.tabeldata.service;

import com.tabeldata.dao.KegiatanDao;
import com.tabeldata.dao.ProgramDao;
import com.tabeldata.entity.ProgramEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProgramService {

    @Autowired
    private ProgramDao dao;

    /**
     * Get List ALL Program
     */
    public List<ProgramEntity> getListProgram() {
        List<ProgramEntity> program = dao.getListProgram();
        return program;
    }

    /**
     * Get List Program By ID URUSAN
     */
    public List<ProgramEntity> getListProgramByIdUrusan(Integer idUrusan, String tahunAnggaran, Integer idSkpd) {
        List<Integer> idProgram = dao.getIDProgramByIdSkpdDanTahunAnggaran(idSkpd, tahunAnggaran);
        List<ProgramEntity> program = dao.getListProgramByIdUrusan(idUrusan, tahunAnggaran, idProgram);
        return program;
    }

    /**
     * Get Program By ID
     */
    public ProgramEntity getProgramByID(Integer id) throws EmptyResultDataAccessException {
        ProgramEntity program = dao.getProgramByID(id);
        return program;
    }
}
