package com.qualist;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by hikuley on 29.05.2016.
 */

public class Main {

    public static void main(String[] args) throws Exception {

        // we are initializing DbConnectionUtil
        DbConnectionUtil.init();

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
        WebAppContext webAppContext = new WebAppContext();
        String webappDirLocation = "src/main/webapp/";
        webAppContext.setResourceBase(webappDirLocation);
        webAppContext.setContextPath("/");
        webAppContext.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");


        //4 we are starting server
        server.setHandler(webAppContext);
        server.start();
        server.join();
    }

}
