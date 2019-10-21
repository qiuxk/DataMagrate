package com.qiuxk.datamagrate;


import com.qiuxk.dao.PersonDao;
import com.qiuxk.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMybatis {

    @Resource
    private PersonDao personDao;

    @Test
    public void test(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","123");
        List<Person> list= personDao.query(map);
        System.out.println(list.size());
    }
}
