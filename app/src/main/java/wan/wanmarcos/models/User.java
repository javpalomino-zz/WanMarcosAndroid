package wan.wanmarcos.models;

/**
 * Created by javier on 26/09/15.
 */
public class User {
    private int id;

    private String first_name;
    private String last_name;

    private String device_token;
    private String platform;

    @Override
    public String toString(){
        return  "User[ first_name: "+first_name+" last_name: "+last_name+"]";
    }
}
