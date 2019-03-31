package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBC_Opgaver {
    private UserDAOImpls185020 DBConn;
    private DatabaseMetaData metaData;
    Connection conn;

    public JDBC_Opgaver() throws IUserDAO.DALException, SQLException {
        DBConn = new UserDAOImpls185020();

        conn = DBConn.createConnection();
        metaData = conn.getMetaData();
    }

    /*7.1 JDBC
     * Brug JDBC's metadata og skriv et Java-program der henter en
     * liste over alle tabeller i din database og udskriver
     * navne på tabellerne og deres kolonner.*/
    public List printMetaData() throws SQLException {
        List<String> listen = new ArrayList<>();
        String next;
        ResultSet res = metaData.getTables(null, null, null, new String[]{"TABLE"});
        while (res.next()) {
            next = res.getString("TABLE_NAME");
            listen.add(next);
        }
        kolonner(listen);
        return listen;

    }

    public void kolonner(List<String> listen) throws SQLException {
        for (String tabel : listen) {
            System.out.println(tabel + " har følgende kolonner: ");
            ResultSet columns = metaData.getColumns(null, null, tabel, null);
            while (columns.next()) {
                System.out.println(columns.getString("COLUMN_NAME"));
            }
        }
    }

    public List<String> budget() throws SQLException {
        List<String> listen = new ArrayList<>();

        CallableStatement cstmt = conn.prepareCall(
                "{? = call avg_budget()}");

        cstmt.registerOutParameter(1, Types.INTEGER);
        cstmt.execute();
        int budget2 = cstmt.getInt(1);

        PreparedStatement prep = conn.prepareStatement(
                "select dept_name " +
                    "from department " +
                        "where budget > ?"
        );
        prep.setInt(1, budget2);
        ResultSet rs = prep.executeQuery();
        while (rs.next()){
            listen.add(rs.getString(1));
        }
        return listen;
    }
}
    /*7.2 Functions
    Skriv en SQL Function der returnerer alle departments der har et budget,
    der er højere end gennemsnittet - uden at bruge subqueries og Aggregation.


    }*/

/*
7.3 Stored procedure
    Skriv en stored procedure der arkiverer sections, der er ældre end
    et givent årstal i en old_sections tabel. Proceduren skal tage et årstal
    som argument, og returnere et tal for hvor mange sections der blev arkiveret
*/





/*
 *




7.4 Triggers

5.4 i Database System Concepts

5.6

 */
