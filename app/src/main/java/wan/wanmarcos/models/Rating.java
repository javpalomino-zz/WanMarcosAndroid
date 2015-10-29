package wan.wanmarcos.models;

/**
 * Created by carlos-pc on 09/10/15.
 */
public class Rating {
    private float rating;
    private String type;

    public Rating(float rating, String type) {
        this.rating = rating;
        this.type = type;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
