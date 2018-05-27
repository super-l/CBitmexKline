package org.superl.exchange.CBitmexKline.utils;

import lombok.extern.log4j.Log4j;
import org.superl.exchange.CBitmexKline.beans.Ticket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * ━━━━━━神兽出没━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 * 　　 ┏┓　　　 ┏┓　
 * 　　┏┛┻━━━━━━┛┻┓
 *   ┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃   @desc:          MYSQL数据库操作类
 * 　　┃　　 ━　　 ┃
 * 　　┃　┳┛　 ┗┳       @Copyright(C):  superl@nepenthes.cn  at  2018/4/16 上午1:40
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
 * 　　　　┗┻┛ ┗┻┛
 *
 * ━━━━━━感觉萌萌哒━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
 */
@Log4j
public class MysqlDB {

    private static final String MYSQL_URL = "jdbc:mysql://127.0.0.1:3306/testb?serverTimezone=UTC&characterEncoding=utf8&useSSL=false&autoReconnect=true&failOverReadOnly=false";
    private static final String MYSQL_NAME = "superl";
    private static final String MYSQL_PASSWORD = "123456";
    private static Connection conn = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(MYSQL_URL,MYSQL_NAME, MYSQL_PASSWORD);
            log.debug("Start Connect Mysql Database...");
        } catch (ClassNotFoundException e) {
            log.debug("Connect Mysql Database Error..."+e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            log.debug("Connect Mysql Database Error..."+e.getMessage());
            e.printStackTrace();
        }
    }

    //构造方法
    public MysqlDB() {
        log.debug("初始化连接数据库...");
    }

    //对外提供一个方法来获取数据库连接
    public static Connection getConnection() {
        return conn;
    }

    /**
     * 添加数据库记录
     */
    public static void insertTicket(Ticket data)  {
        String symbol = data.getSymbol();
        String insert_sql = "insert into TICKET_"+symbol+"(`open`,`close`,`high`,`low`,`trades`,`volume`,`timestamp`,`timestampl`)values(?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pstmt = conn.prepareStatement(insert_sql);

            pstmt.setDouble(1, data.getOpen());
            pstmt.setDouble(2, data.getClose());
            pstmt.setDouble(3, data.getHigh());
            pstmt.setDouble(4, data.getLow());
            pstmt.setInt(5, data.getTrades());
            pstmt.setDouble(6, data.getVolume());
            pstmt.setLong(7, data.getTimestampl());
            pstmt.setString(8, data.getTimestamp());
            pstmt.execute();
        }catch (Exception e){
            log.error(e.getMessage());
            //e.printStackTrace();
        }

    }

}
