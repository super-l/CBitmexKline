package org.superl.exchange.CBitmexKline.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * ━━━━━━神兽出没━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 * 　　 ┏┓　　　 ┏┓
 * 　　┏┛┻━━━━━━┛┻┓
 * ┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃     @desc:          时间格式统一转换为13位戳
 * 　　┃　　 ━　　 ┃
 * 　　┃　┳┛　 ┗┳       @Copyright(C):  superl@nepenthes.cn  at  2018/4/20 下午10:16
 * 　　┃　　　　　 ┃
 * 　　┃　　 ┻　　       @author:        superl (qq:86717375)
 * 　　┃　　　　　 ┃     @team:          Nepenthes Security Team(忘忧草安全团队)
 * 　　┗━┓　　　┏━┛
 * 　　　┃　　  ┃        神兽保佑,代码无bug
 * 　　　┃　　  ┃
 * 　　　┃　　  ┗━━━┓    @link:          http://www.superl.org  https://github.com/super-l
 * 　　　┃　　　　   ┣┓
 * 　　　┃　　　　   ┏┛   Code is far away from bug with the animal protecting
 * 　　　┗┓┓┏━ ┳┓━━━┛
 * 　　　 ┃┫┫ ┃┫┫
 * 　　　 ┗┻┛ ┗┻┛
 * <p>
 * ━━━━━━感觉萌萌哒━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 */
public class UnixTime {

    /**
     * 时间转时间戳 ok、huobi不用做处理
     *
     * @param orderTime         订单发起时间
     * @return                  时间戳
     */
    public static String getUnixTime(String orderTime,String type) throws ParseException {
        SimpleDateFormat sdf;
        Date date;
        String mydata;

        switch (type){
            case "bitmex":
                sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                sdf.setTimeZone(TimeZone.getTimeZone("GTC"));
                date = sdf.parse(orderTime);
                mydata = String.valueOf(date.getTime());
                break;
            case "bittrex":
                sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                date = sdf.parse(orderTime);
                mydata = String.valueOf(date.getTime());
                break;

            case "coinegg":
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = sdf.parse(orderTime);
                mydata = String.valueOf(date.getTime());
                break;

            case "hitbtc":
                sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                sdf.setTimeZone(TimeZone.getTimeZone("GTC"));
                date = sdf.parse(orderTime);
                mydata = String.valueOf(date.getTime());
                break;

            case "gateio":
                long theOrderTime = Long.parseLong(orderTime)*1000;
                mydata = String.valueOf(theOrderTime);
                break;

            default:
                mydata = orderTime;
                break;
        }
        return mydata;
    }

    public static void main(String[] args) throws ParseException {

//        System.out.print(getUnixTime("2018-04-20T14:47:54.77","bittrex")+"\n");
//
//        System.out.print(getUnixTime("2018-04-20 14:47:54","coinegg")+"\n");
//
//        System.out.print(getUnixTime("2018-04-20T14:47:54.437Z","hitbtc")+"\n");

//        System.out.print(getUnixTime("1407828913","gateio")+"\n");


        System.out.print(getUnixTime("2018-05-08T12:08:04.185Z","bitmex")+"\n");
        long currentTime = System.currentTimeMillis();
        System.out.print(currentTime+"\n");

    }
}
