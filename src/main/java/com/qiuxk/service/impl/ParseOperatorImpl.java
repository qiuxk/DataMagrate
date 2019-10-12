package com.qiuxk.service.impl;

import com.qiuxk.service.ParseFile;

public class ParseOperatorImpl implements ParseFile {
    private final static String  A ="INSERT INTO `tbl_phone_operator`(`operator_id`, `operator_name`, `operator_code`, `shelves_status`, `operator_status`, `channel_id`, `up_person`, `create_time`, `up_time`) VALUES";



    @Override
    public String run(String line) {


        int begin = line.lastIndexOf("(")+1;
        int end = line.lastIndexOf(")");
        line=   line.substring(begin,end);

        String  returnStr = "";
        if(null !=line) {


            String[] oldArray = line.split(",");
            String createTime  = parseTime(oldArray[6]);

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

            String[] newArray = new String[9];

            newArray[0]=oldArray[0];
            newArray[1]=oldArray[1];
            newArray[2]=oldArray[2];
            newArray[3]=parseShelvesStatus(oldArray[3]);
            newArray[4]="0";
            newArray[5]=oldArray[5];
            newArray[6]="admin";
            newArray[7]=createTime;
            newArray[8]=createTime;

            for(int i =0;i<9;i++){
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

    private String parseShelvesStatus(String oldStatus){
        String newStatus = oldStatus;
        if(oldStatus.equals("1")){
            newStatus="00";
        }else if(oldStatus.equals("2")){
            newStatus="01";
        }
        return  newStatus;
    }

}
