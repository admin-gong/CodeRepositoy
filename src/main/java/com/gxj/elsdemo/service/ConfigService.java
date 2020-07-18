package com.gxj.elsdemo.service;

import com.gxj.elsdemo.pojo.SysConfig;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface ConfigService {
        List<SysConfig> findAll();
        <S extends SysConfig> Optional<S> findOne(Example<S> example);
//        List<SysConfig> findAllById(String variable);
        void deleteById(String s);

        void save(SysConfig sysConfig);
        void updateById(String s,String s1);


}
