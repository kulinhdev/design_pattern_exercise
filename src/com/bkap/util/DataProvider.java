package com.bkap.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

// Má mày lười vừa thôi code điiii

public class DataProvider {
    private static Connection connect = BkConnection.GetConnection();

    public static ResultSet executeQuery(String sql, Object... params) {
        CallableStatement cs = null;
        ResultSet rs = null;

        if (connect != null) {
            try {
                cs = connect.prepareCall(sql);

                if (params != null) {
                    for (int i = 0; i < params.length; i++) {
                        cs.setObject(i + 1, params[i]);
                    }
                }
                rs = cs.executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Connect to database failed !!!");
        }

        return rs;
    }

    // Execute Update
    public static boolean executeUpdate(String sql, Object... params) {
        CallableStatement cs = null;
        ResultSet rs = null;
        int rows = 0;

        if (connect != null) {
            try {
                cs = connect.prepareCall(sql);
                if (params != null) {
                    for (int i = 0; i < params.length; i++) {
                        cs.setObject(i + 1, params[i]);
                    }
                }

                rows = cs.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Connect to database failed !!!");
        }

        return rows > 0;
    }
}
