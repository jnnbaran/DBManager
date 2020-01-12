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
            myConn = dataSource.getConnection();
            String sql = "select * from Category";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {

                int categoryId = myRs.getInt("CategoryId");
                String category = myRs.getString("Category");

                Category tempCategory = new Category(categoryId, category);

                categories.add(tempCategory);
            }

            return categories;
        } finally {
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
                myConn.close();
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}