package com.qualist;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by halil_000 on 5/31/2016.
 */

public class DbConnectionUtil {

    protected static DbConnectionUtil instance = null;
    protected static Connection initialConnection = null;
    protected static ComboPooledDataSource dataSource = null;

    public DbConnectionUtil() {
    }

    public static void init() {
        instance = new DbConnectionUtil();
        try {
            System.out.println(" --------------------- creating initial connection --------------------- ");
            dataSource = new ComboPooledDataSource();
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/testdb");
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setUser("newuser");
            dataSource.setPassword("");


            dataSource.setInitialPoolSize(2);
            dataSource.setMinPoolSize(2);
            dataSource.setMaxPoolSize(10);
            dataSource.setAcquireIncrement(1);
            //dataSource.setMaxIdleTime(10);
            dataSource.setMaxIdleTimeExcessConnections(5);

//            dataSource.setUnreturnedConnectionTimeout(5);

            initialConnection = dataSource.getConnection();
            System.out.println(" --------------------- creating initial connection --------------------- ");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DbConnectionUtil getInstance() {
        return instance;
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
