package com.remotelabs.hire;

import org.testcontainers.containers.MySQLContainer;

public class DbContainer extends MySQLContainer<DbContainer> {

    private static DbContainer dbContainer;

    private DbContainer(){

        super("mysql:8.0");
    }

    public static DbContainer getInstance(){
        if (dbContainer==null){
            dbContainer = new DbContainer();
        }

        return dbContainer;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", dbContainer.getJdbcUrl());
        System.setProperty("DB_USERNAME", dbContainer.getUsername());
        System.setProperty("DB_PASSWORD", dbContainer.getPassword());
    }

    @Override
    public void stop() {
        super.stop();
    }
}
