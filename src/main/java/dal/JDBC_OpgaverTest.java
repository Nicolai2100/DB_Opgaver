package dal;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBC_OpgaverTest {
    UserDAOImpls185020 dbCon;
    List<String> roller;
    Connection connection;
    JDBC_Opgaver opgaver;

    @Before
    public void initialize() throws IUserDAO.DALException, SQLException {
        dbCon = new UserDAOImpls185020();
        roller = new ArrayList<>();
        opgaver = new JDBC_Opgaver();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185020",
                    "s185020", "iEFSqK2BFP60YWMPlw77I");
        } catch (SQLException e) {
            throw new IUserDAO.DALException(e.getMessage());

        }
    }

    @Test
    public void printMetaData() throws SQLException {
        List<String> tableNames = opgaver.printMetaData();
    }

    @Test
    public void kolonner() throws SQLException {
/*
        opgaver.kolonner();
*/
    }

    @Test
    public void budget() throws SQLException {
        List<String> listen = opgaver.budget();
        for (String navn : listen) {
            System.out.println(navn);
        }
    }
}