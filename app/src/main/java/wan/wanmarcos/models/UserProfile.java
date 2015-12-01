package wan.wanmarcos.models;

/**
 * Created by Francisco on 1/12/2015.
 */
public class UserProfile {
    private int id;
    private String name;
    private String email;
    private String faculty;
    private String carreer;
    private String imgURL;

    public UserProfile()
    {
    }


    public UserProfile(int id , String name , String email , String faculty , String carreer , String imgURL)
    {
        this.id=id;
        this.name=name;
        this. email=email;
        this.faculty=faculty;
        this.carreer=carreer;
        this.imgURL=imgURL;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getCarreer() {
        return carreer;
    }

    public void setCarreer(String carreer) {
        this.carreer = carreer;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
