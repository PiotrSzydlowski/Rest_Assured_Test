package dataGenerator.pets;

public enum PetsCategory {

    DOGS(1, "dogs"),
    CATS(2, "cats"),
    OTHER(3, "other");

    private int id;
    private String categoryName;

    PetsCategory(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }


    @Override
    public String toString() {
        return "PetsCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
