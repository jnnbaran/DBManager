package dbservlet.dao;

import dbservlet.model.Category;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {


    @Resource(name = "jdbc/Knowledgebase")
    private static DataSource dataSource;

    public CategoryDAO(DataSource theDataSource) {

        dataSource = theDataSource;
    }

    public static List<Category> getCategory() throws Exception {

        List<Category> categories = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();

            // create sql statement
            String sql = "select * from Category";

            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {

                // retrieve data from result set row
                int categoryId = myRs.getInt("CategoryId");
                String category = myRs.getString("Category");

                // create new student object
                Category tempCategory = new Category(categoryId, category);

                // add it to the list of students
                categories.add(tempCategory);
            }

            return categories;
        } finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }


}

    private static void close(Connection myConn, Statement myStmt, ResultSet myRs) {

        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();   // doesn't really close it ... just puts back in connection pool
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}