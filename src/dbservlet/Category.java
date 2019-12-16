package dbservlet;

public class Category {

    private int CategoryId;
    private String Category;

    public Category(int CategoryId, String Category) {
        super();
        this.CategoryId = CategoryId;
        this.Category = Category;
    }

    public int getId() {
        return CategoryId;
    }

    public void setId(int CategoryId) {
        this.CategoryId = CategoryId;
    }

    public String getName() {
        return Category;
    }

    public void setName(String name) {
        this.Category = name;
    }
}
