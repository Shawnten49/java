package com.shuffle.db;

import com.shuffle.db.pool.ConnectionManager;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shawn.xu on 16/5/8.
 * 数据库操作工具封装
 */
public class DBhelper {

    public static Map<String, Object> findSimpleResult(String poolName, String sql, List<Object> list) {
        Connection connection = ConnectionManager.getInstance().getConnection(poolName);

        Map<String, Object> map = null;
        try {
           map = findSimpleResult(connection, sql, list);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ConnectionManager.getInstance().getPool(poolName).printf("before");
        ConnectionManager.getInstance().release(poolName, connection);
        ConnectionManager.getInstance().getPool(poolName).printf("end");

        return  map;
    }

    public static Map<String, Object> findSimpleResult(Connection connection, String sql, List<Object> params) throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        int index  = 1;
        PreparedStatement pstmt = connection.prepareStatement(sql);
        if(params != null && !params.isEmpty()){
            for(int i=0; i<params.size(); i++){
                pstmt.setObject(index++, params.get(i));
            }
        }
        ResultSet resultSet = pstmt.executeQuery();//返回查询结果
        ResultSetMetaData metaData = resultSet.getMetaData();
        int col_len = metaData.getColumnCount();
        while(resultSet.next()){
            for(int i=0; i<col_len; i++ ){
                String cols_name = metaData.getColumnName(i+1);
                Object cols_value = resultSet.getObject(cols_name);
                if(cols_value == null){
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
        }

        return map;
    }
}
