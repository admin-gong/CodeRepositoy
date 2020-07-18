package com.gxj.elsdemo.service.impl;

import com.gxj.elsdemo.dao.SysConfigDao;
import com.gxj.elsdemo.pojo.SysConfig;
import com.gxj.elsdemo.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private SysConfigDao sysConfigDao;


    @Override
    public List<SysConfig> findAll() {
        return sysConfigDao.findAll();
    }

    @Override
    public <S extends SysConfig> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public void deleteById(String s) {
        sysConfigDao.deleteById(s);
    }

    @Override
    public void save(SysConfig sysConfig) {
        sysConfigDao.save(sysConfig);
    }

    @Override
    public void updateById(String s,String s1) {
            sysConfigDao.updateByid(s,s1);
    }


}


