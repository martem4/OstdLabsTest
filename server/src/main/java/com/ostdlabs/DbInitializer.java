package com.ostdlabs;

import org.apache.commons.dbcp.BasicDataSource;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.InputStream;

/**
 * Created by Yoghurt_92 on 21.11.2015.
 */
public class DbInitializer {
    @Autowired
    private BasicDataSource dataSource;

    @Autowired(required = false)
    private String schema;

    private String dataset;

    public DbInitializer(String dataset) {
        this.dataset = dataset;
    }

    public IDataSet readDataSet() throws Exception {
        InputStream stream = this.getClass().getResourceAsStream(dataset);
        return new FlatXmlDataSetBuilder().build(stream);
    }

    @PostConstruct
    public void initDb() throws Exception {
        DatabaseConnection databaseConnection = null;

        try {
            IDataSet iDataSet = readDataSet();
            ReplacementDataSet replacementDataSet = new ReplacementDataSet(iDataSet);

            replacementDataSet.addReplacementObject("[NULL]", null);
            databaseConnection = new DatabaseConnection(dataSource.getConnection(), schema);
            DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, replacementDataSet);
            databaseConnection.close();
        }
        finally {
            if(databaseConnection!= null){
                databaseConnection.close();
            }
        }
    }
}
