package com.gxj.elsdemo.dao;

import com.gxj.elsdemo.pojo.SysConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Repository
public interface SysConfigDao extends JpaRepository<SysConfig,String> {

    @Override
    <S extends SysConfig> List<S> findAll(Example<S> example);

    @Override
    void deleteById(String s);


    //clearAutomatically = true
    @Modifying()
    @Transactional
    @Query("update SysConfig s set s.variable =?2 where s.value=?1")
    void updateByid(@Param("value") String s, @Param("variable") String s1);


    //    @Override
//    List<SysConfig> findAllById(Iterable<String> iterable);

    @Override
    <S extends SysConfig> Optional<S> findOne(Example<S> example);

    @Override
    <S extends SysConfig> S saveAndFlush(S entity);


}
