package com.qiuxk.service;

import org.springframework.stereotype.Service;

@Service
public interface ReadFile {

    public void readFile(String path,String type);
}
