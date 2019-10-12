package com.qiuxk.service.impl;

import com.qiuxk.service.ParseFile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ParseFileImpl implements ParseFile {
    private final static String  A ="INSERT INTO `tbl_phone_order`(`id`, `member_id`, `user_id`, `wallet_id`, `order_no`, `pay_trans_no`, `recharge_trans_no`, `order_type`, `phone_number`, `operator_id`, `channel_id`, `package_id`, `order_money`, `discount_money`, `sell_money`, `real_money`, `discount_type`, `discount_name`, `discount_rule`, `pay_type`, `order_status`, `push_status`, `is_push`, `tm_stamp`, `pay_apply_time`, `recharge_apply_time`, `pay_success_time`, `recharge_success_time`, `pay_desc`, `recharge_desc`, `lock_flg`, `run_count`, `lock_tm`, `remarks`, `create_time`, `up_time`, `phone_lock_flag`, `phone_run_count`, `phone_lock_tm`) VALUES ";


    @Override
    public String run(String line) {
           int begin = line.lastIndexOf("(")+1;
           int end = line.lastIndexOf(")");
         line=   line.substring(begin,end);

        String  returnStr = "";
        if(null !=line){


            String[] oldArray =line.split(",");
            String createTime =parseTime(oldArray[23]) ;
            String payTime = parseTime(oldArray[24]);
            String rechargeTime = parseTime(oldArray[25]);

            for(int i=0;i<oldArray.length;i++){
                if(oldArray[i].contains("'")){
                    oldArray[i]=oldArray[i].replace("'","");
                }
                if(oldArray[i].contains(" '")){
                    oldArray[i] = oldArray[i].replace(" '","");
                }
                if(oldArray[i].contains(" ")){
                    oldArray[i]=oldArray[i].replace(" ","");
                }

            }


            String[] newArray = new String[39];

            newArray[0]=oldArray[0];
            //todo member_id
            newArray[1]=oldArray[1];
            newArray[2]=oldArray[1];
            newArray[3]=oldArray[2];
            newArray[4]=oldArray[4];
            //todo pay_trans_no 支付交易流水号
            newArray[5]=oldArray[5];
            //todo 充值交易流水号
            newArray[6]=oldArray[7];

            newArray[7]="010";
            newArray[8]=oldArray[9];
            newArray[9]=oldArray[10];
            newArray[10]=oldArray[11];
            newArray[11]=oldArray[12];
            newArray[12]=parseMoney(oldArray[13]);
            newArray[13]="0";
            newArray[14]=parseMoney(oldArray[15]);
            newArray[15]=parseMoney(oldArray[15]);
            newArray[16]=oldArray[16];
            newArray[17]=oldArray[18];
            newArray[18]=oldArray[19];
            newArray[19]=oldArray[20];
            newArray[20]=parseOrderStatus(oldArray[21]);
            newArray[21]="020";
            newArray[22]="020";
            //todo 
            newArray[23]= "";
            //todo pay_apply_time 支付申请时间
            newArray[24]=payTime;
            //todo recharge_apply_time 充值申请时间
            newArray[25]=rechargeTime;
            //todo pay_success_time 支付成功时间
            newArray[26]=payTime;
            //todo recharge_susscess_time 充值成功时间
            newArray[27]=rechargeTime;
            newArray[28]="";
            newArray[29]="";
            newArray[30]="01";
            newArray[31]="0";
            newArray[32]="";
            newArray[33]="迁移数据";
            newArray[34]=createTime;
            newArray[35]=createTime;
            newArray[36]="02";
            newArray[37]="0";
            newArray[38]="";


            for(int i =0;i<39;i++){
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


        return returnStr;
    }

    private String parseOrderStatus(String oldOrderStatus){
        String newOrderStatus = oldOrderStatus;
        if(oldOrderStatus.equals("1")){
            newOrderStatus ="060";
        }else if(oldOrderStatus.equals("2")){
            newOrderStatus ="040";
        }else if(oldOrderStatus.equals("3")){
            newOrderStatus="050";
        }

        return  newOrderStatus;
    }

    private String parseMoney(String oldMoney){
        String newMoney =oldMoney;
        if(null !=oldMoney&& !"".equals(oldMoney)&&!"0".equals(oldMoney)){
           newMoney = new BigDecimal(oldMoney).multiply(new BigDecimal("100")).toString();
        }

        return  newMoney;
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
