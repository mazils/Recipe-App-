package remoteDataSource;

public class Recipe {
    private String title;
    private String image;
    private String imageType;
    private int id;

    public Recipe(String title, String image, String imageType, int id) {
        this.title = title;
        this.image = image;
        this.imageType = imageType;
        this.id = id;
    }
    //array of the recipies and the recipe

    /**"id": 716426,
     "title": "Cauliflower, Brown Rice, and Vegetable Fried Rice",
     "image": "https://spoonacular.com/recipeImages/716426-312x231.jpg",
     "imageType": "jpg"*/




    public Recipe getRecipes()
    {
        return new Recipe(title,image,imageType,id);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", imageType='" + imageType + '\'' +
                ", id=" + id +
                '}';
    }
}
