package com.gxj.elsdemo;

import com.gxj.elsdemo.dao.SysConfigDao;
import com.gxj.elsdemo.pojo.SysConfig;
import com.gxj.elsdemo.service.ConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ElsdemoApplicationTests {
@Autowired
private ConfigService configService;
@Autowired
private SysConfigDao sysConfigDao;


    @Test
    void contextLoads() {
//        List<SysConfig> all = configService.findAll();
//        System.out.println(all);

//        configService.findAllById(Iterable<variable>);
//        System.out.println(byId.toString());


    }

    @Test
    public void findall(){
        List<SysConfig> all = configService.findAll();
        System.out.println(all.toString());
    }
    @Test
    public void delete(){
        configService.deleteById("ps_thread_trx_info.max_length");

    }
    @Test
    public void update(){
        //jpa
        SysConfig sysConfig = new SysConfig();
        sysConfig.setVariable("150");
        Date date = new Date();
        sysConfig.setSetTime(date);
        sysConfig.setValue("2333345677");
//        sysConfig.setSetBy("12311233");
////        configService.save(sysConfig);

        Optional<SysConfig> old = sysConfigDao.findById(sysConfig.getVariable());
        SysConfig sysConfig1 = old.get();
        updateEntity(sysConfig,sysConfig1);
        sysConfigDao.saveAndFlush(sysConfig1);
        //QUERY
        //valis = 123455
       // sysConfigDao.updateByid("1","148");

    }

    public  <T> T updateEntity(T entity,T oldEntity){
        Class clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){  // 遍历属性
            try {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method readMethod = pd.getReadMethod(); //获取属性的get方法
                Method writeMethod= pd.getWriteMethod(); //获取属性的set方法
                if (readMethod.invoke(entity)!=null){   // 如果这个属性不为null
                    // old对象set(entity.get())  old对象将属性值设置为entity的属性值
                    writeMethod.invoke(oldEntity,readMethod.invoke(entity));
                }
            }catch (Exception e){

            }
        }
        return oldEntity;
    }
}


