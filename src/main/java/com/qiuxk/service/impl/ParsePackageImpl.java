package com.qiuxk.service.impl;

import com.qiuxk.service.ParseFile;

import java.math.BigDecimal;

/**
 *
 * @author qiuxk
 * @Description: 话费包转化
 * @create 2019/10/15
 **/
public class ParsePackageImpl implements ParseFile {
    private final static String  A ="INSERT INTO `tbl_phone_package_info`(`package_id`, `channel_id`, `operator_id`, `province`, `city`, `package_type`, `package_money`, `package_price`, `channel_supply_scale`, `channel_supply_money`, `shelves_status`, `package_status`, `up_person`, `create_time`, `up_time`) VALUES";



    @Override
    public String run(String line) {


        int begin = line.lastIndexOf("(")+1;
        int end = line.lastIndexOf(")");
        line=   line.substring(begin,end);

        String  returnStr = "";
        if(null !=line) {


            String[] oldArray = line.split(",");
            String createTime  = parseTime(oldArray[12]);

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

            String[] newArray = new String[15];

            newArray[0]=oldArray[0];
            newArray[1]=oldArray[1];
            newArray[2]=oldArray[2];
            newArray[3]=oldArray[3];
            newArray[4]=oldArray[4];
            newArray[5]=oldArray[5];

            newArray[6]=parseMoney(oldArray[6]);
            newArray[7]=parseMoney(oldArray[7]);
            newArray[8]=oldArray[9];
            newArray[9]=parseMoney(oldArray[9]);
            newArray[10] =oldArray[10];
            newArray[11]=oldArray[11];
            newArray[12]="admin";
            newArray[13]=createTime;
            newArray[14]=createTime;

            for(int i =0;i<15;i++){
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

    private String parseMoney(String oldMoney){
        String newMoney =oldMoney;
        if(null !=oldMoney&& !"".equals(oldMoney)&&!"0".equals(oldMoney)){
            newMoney = new BigDecimal(oldMoney).multiply(new BigDecimal("100")).toString();
        }

        return  newMoney;
    }

}
