package wan.wanmarcos.models;

/**
 * Created by javier on 27/09/15.
 */
public class Event {

    private String name;
    private String imgUrl;
    private String referencePlace;
    private String startDate;
    private String startTime;
    private String finishDate;
    private String finishTime;
    private String description;
    private int iconId;

    public Event()
    {

    }

    public Event(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public Event(String name, String startDate, String startTime, int iconId) {
        this.name = name;
        this.startDate = startDate;
        this.startTime = startTime;
        this.iconId = iconId;
    }

    public Event(String name, String referencePlace, String startDate, String startTime, String finishDate, String finishTime, String description, int iconId) {
        this.name = name;
        this.referencePlace = referencePlace;
        this.startDate = startDate;
        this.startTime = startTime;
        this.finishDate = finishDate;
        this.finishTime = finishTime;
        this.description = description;
        this.iconId = iconId;
    }

    public Event(String name, String referencePlace, String startDate, String startTime, String finishDate, String finishTime, String description, String imgUrl) {
        this.name = name;
        this.referencePlace = referencePlace;
        this.startDate = startDate;
        this.startTime = startTime;
        this.finishDate = finishDate;
        this.finishTime = finishTime;
        this.description = description;
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

    public String getReferencePlace() {

        return referencePlace;
    }

    public void setReferencePlace(String referencePlace) {

        this.referencePlace = referencePlace;
    }

    public String getStartDate() {

        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {

        return startTime;
    }

    public void setStartTime(String startTime) {

        this.startTime = startTime;
    }

    public String getFinishDate() {

        return finishDate;
    }

    public void setFinishDate(String finishDate) {

        this.finishDate = finishDate;
    }

    public String getFinishTime() {

        return finishTime;
    }

    public void setFinishTime(String finishTime) {

        this.finishTime = finishTime;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
