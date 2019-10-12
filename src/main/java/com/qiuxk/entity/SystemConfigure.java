package com.qiuxk.entity;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class SystemConfigure {

    @Value("${system.inPath}")
    private String inPath;

    @Value("${system.outPath}")
    private String outPath;
}
