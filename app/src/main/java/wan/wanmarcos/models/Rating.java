package wan.wanmarcos.models;

/**
 * Created by carlos-pc on 09/10/15.
 */
public class Rating {
    private Double rating;
    private String type;

    public Rating(Double rating, String type) {
        this.rating = rating;
        this.type = type;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
