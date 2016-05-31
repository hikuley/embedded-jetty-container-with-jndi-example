package com.qualist;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.eclipse.jetty.plus.jndi.Resource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by hikuley on 29.05.2016.
 */

public class Main {

    public static void main(String[] args) throws Exception {

        //1. we are creating the service
        Server server = new Server(8080);

        //2. we are enabling  jetty-plus-configuration
        Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
        classlist
                .addAfter(
                        "org.eclipse.jetty.webapp.FragmentConfiguration",
                        "org.eclipse.jetty.plus.webapp.EnvConfiguration",
                        "org.eclipse.jetty.plus.webapp.PlusConfiguration"
                );


        //3. we are creating the web-application context
        String webappDirLocation = "src/main/webapp/";
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setResourceBase(webappDirLocation);
        webAppContext.setContextPath("/");
        webAppContext.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");

        //4. we are creating datasource with manual programmatically.

//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        dataSource.setDriverClass("com.mysql.jdbc.Driver");
//        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/testdb");
//        dataSource.setUser("root");
//        dataSource.setPassword("");
//        dataSource.setInitialPoolSize(5);

        //5. we are regitering the datasource for our server
//        new Resource(server, "jdbc/DSTest", dataSource);


        // we are starting server
        server.setHandler(webAppContext);
        server.start();
        server.join();
    }

}
