package wan.wanmarcos.models;

/**
 * Created by soporte on 25/11/15.
 */
public class Place {
    private String urlPlace;
    private int idPlace;
    private String placeName;
    private String referencePlace;
    private int distance;
    private float ratingPlace;
    private String references;

    public Place(String urlPlace, int idPlace, String placeName, String referencePlace, int distance, float ratingPlace, String references) {
        this.urlPlace = urlPlace;
        this.idPlace = idPlace;
        this.placeName = placeName;
        this.referencePlace = referencePlace;
        this.distance = distance;
        this.ratingPlace = ratingPlace;
        this.references = references;
    }

    public int getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getReferencePlace() {
        return referencePlace;
    }

    public void setReferencePlace(String referencePlace) {
        this.referencePlace = referencePlace;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public float getRatingPlace() {
        return ratingPlace;
    }

    public void setRatingPlace(float ratingPlace) {
        this.ratingPlace = ratingPlace;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }


    public String getUrlPlace() {
        return urlPlace;
    }

    public void setUrlPlace(String urlPlace) {
        this.urlPlace = urlPlace;
    }


}
