package Connection;


import org.postgresql.jdbc2.optional.PoolingDataSource;


import javax.sql.DataSource;
import javax.sql.PooledConnection;
import java.sql.*;


//Design pattern factory -> return objects
public class ConnectionFactory {

    private static final String URL = "jdbc:postgresql://batyr.db.elephantsql.com/prhpdsxb";
    private static final String USERNAME = "prhpdsxb";
    private static final String PASSWORD = "zrNl84odG7bWKI9iTr9puB1QxJZ7lQBL";

    private static DataSource datasource;

    static {
        //Pooling connections
        PoolingDataSource pds = new PoolingDataSource();
        pds.setUrl(URL);
        pds.setUser(USERNAME);
        pds.setPassword(PASSWORD);
        pds.setMaxConnections(5);

        datasource = pds;
    }

    //No webdriver!
    public static Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }

}
