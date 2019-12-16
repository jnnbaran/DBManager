package dbservlet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    String databaseURL = "jdbc:mysql://localhost:3306/Knowledgebase";
    String user = "root";
    String password = "mysql";

    public List<Category> list() throws SQLException {
        List<Category> listCategory = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            String sql = "SELECT * FROM Category ORDER BY Category";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                int CategoryId = result.getInt("CategoryId");
                String Category = result.getString("Category");
                Category category = new Category(CategoryId, Category);

                listCategory.add(category);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return listCategory;
    }
}
