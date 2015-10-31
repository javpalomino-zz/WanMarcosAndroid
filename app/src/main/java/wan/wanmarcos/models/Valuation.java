package wan.wanmarcos.models;

/**
 * Created by postgrado on 17/10/15.
 */
public class Valuation {
    private String userName;
    private String userImage;
    private String userComment;
    private float userTotalMark;

    public Valuation(String userName, String userImage, String userComment, float userTotalMark) {
        this.userName = userName;
        this.userImage = userImage;
        this.userComment = userComment;
        this.userTotalMark = userTotalMark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public float getUserTotalMark() {
        return userTotalMark;
    }

    public void setUserTotalMark(float userTotalMark) {
        this.userTotalMark = userTotalMark;
    }
}
