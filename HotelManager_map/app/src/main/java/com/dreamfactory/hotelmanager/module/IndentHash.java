package com.dreamfactory.hotelmanager.module;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yangpeidong on 16/3/25.
 */
enum indent_status {
    WILL_PAY,PAYED,CANCELED,WORKING,FINISHED
}


public class IndentHash {
    public static HashMap<String,List<Indent>> getData(List<Indent> list){
        HashMap<String, List<Indent>> result = new HashMap<String, List<Indent>>();
        List<Indent> will_pay = new ArrayList<>();
        List<Indent> payed = new ArrayList<>();
        List<Indent> canceled = new ArrayList<>();
        List<Indent> working = new ArrayList<>();
        List<Indent> finished = new ArrayList<>();
        for (Indent var:list) {
            indent_status status = indent_status.values()[var.getStatus()];
            switch (status){
                case WILL_PAY:
                    will_pay.add(var);
                    break;
                case PAYED:
                    payed.add(var);
                    break;
                case CANCELED:
                    canceled.add(var);
                    break;
                case WORKING:
                    working.add(var);
                    break;
                case FINISHED:
                    finished.add(var);
                    break;
                default:break;
            }
        }
        result.put("未支付",will_pay);
        result.put("已支付",payed);
        result.put("已取消",canceled);
        result.put("生效中",working);
        result.put("已完成",finished);
        return result;
    }
}
