package wan.wanmarcos.models;

/**
 * Created by javier on 27/09/15.
 */
public class Event {
    private String name;
    private String imgUrl;

    public Event(){

    }

    public Event(String name, String imgUrl){
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
