package com.gxj.elsdemo.controller;

import com.gxj.elsdemo.pojo.SysConfig;
import com.gxj.elsdemo.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigController {

@Autowired
    private ConfigService configService;

    @PostMapping("/select")
    public List<SysConfig> select(){

            return configService.findAll();
    }
}
