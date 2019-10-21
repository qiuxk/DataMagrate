package com.qiuxk.dao;

import com.qiuxk.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface PersonDao {

    List<Person> query(Map<String, Object> map);
}
