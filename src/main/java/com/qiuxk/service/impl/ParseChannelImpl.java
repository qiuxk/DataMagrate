package com.qiuxk.service.impl;

import com.qiuxk.service.ParseFile;

/**
 *
 * @author qiuxk
 * @Description: 渠道表转化
 * @create 2019/10/15
 **/
public class ParseChannelImpl implements ParseFile {
    private final static String  A ="INSERT INTO `tbl_phone_channel`(`channel_id`, `channel_name`, `channel_code`, `shelves_status`, `channel_status`, `up_person`, `create_time`, `up_time`) VALUES";

    @Override
    public String run(String line) {

        int begin = line.lastIndexOf("(")+1;
        int end = line.lastIndexOf(")");
        line=   line.substring(begin,end);

        String  returnStr = "";
        if(null !=line) {


            String[] oldArray = line.split(",");
            String createTime  = parseTime(oldArray[5]);

            for (int i = 0; i < oldArray.length; i++) {
                if (oldArray[i].contains("'")) {
                    oldArray[i] = oldArray[i].replace("'", "");
                }
                if (oldArray[i].contains(" '")) {
                    oldArray[i] = oldArray[i].replace(" '", "");
                }
                if (oldArray[i].contains(" ")) {
                    oldArray[i] = oldArray[i].replace(" ", "");
                }

            }

            String[] newArray = new String[8];

            newArray[0]=oldArray[0];
            newArray[1]=oldArray[1];
            newArray[2]=oldArray[2];
            newArray[3]=oldArray[3];
            newArray[4]=oldArray[4];
            newArray[5]="admin";
            newArray[6]=createTime;
            newArray[7]=createTime;

            for(int i =0;i<8;i++){
                returnStr+="'"+newArray[i]+"',";
            }

            returnStr =A+"("+returnStr+");";

            if(returnStr.contains("' null'")){
                returnStr =  returnStr.replace("' null'","null");
            }

            int offset = returnStr.lastIndexOf(",");
            StringBuilder sb = new StringBuilder(returnStr);
            returnStr = sb.replace(offset,offset+1,"").toString();

        }
             return   returnStr;
    }


    private String parseTime(String oldTime){
        String newTime =oldTime;
        if(oldTime.contains(" '")){
            newTime = oldTime.replace(" '","");
        }
        if (oldTime.contains("'")){
            newTime = oldTime.replace("'","");
        }

        return  newTime;
    }
}
