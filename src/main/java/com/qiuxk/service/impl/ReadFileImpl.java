package com.qiuxk.service.impl;

import com.qiuxk.entity.SystemConfigure;
import com.qiuxk.service.ParseFile;
import com.qiuxk.service.ReadFile;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadFileImpl implements ReadFile {

    @Resource
    private SystemConfigure systemConfigure;




    @Override
    public void readFile(String path,String type)  {

        BufferedReader reader = null;
        List<String> list = new ArrayList<String>();
        ParseFile parseFile =null;
        if("order".equals(type)){
            parseFile = new ParseFileImpl();
        }else if("channel".equals(type)){
            parseFile = new ParseChannelImpl();
        }else if("operator".equals(type)){
            parseFile = new ParseOperatorImpl();
        }else if("package".equals(type)){
            parseFile = new ParsePackageImpl();
        }


        try {
            File file = new File(path);
            reader = new BufferedReader(new FileReader(file));
            String lineString = null;
            int line = 0;
            int textLine = 0;
            while ((lineString = reader.readLine()) != null) {
                // 显示行号
                //根据文件名对不同的文件进行解析,存储；

                if (line >= 0) {
                    //调用解析存储方法  传入lineString textName
                    String parsedStr = parseFile.run(lineString);
                    list.add(parsedStr);
                    textLine++;
                }
                line++;
            }
            line = 0;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }


        /**输出sql语句**/
        FileWriter fw =null;
        BufferedWriter  bw= null;
        try{
            fw = new FileWriter(new File(systemConfigure.getOutPath()+type+".sql"));
              bw=new BufferedWriter(fw);

            //BufferedWriter  bw=new BufferedWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8"))
            for (String str : list){
                bw.write(str+"\t\n");
            }
            bw.close();
            fw.close();
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
