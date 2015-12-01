package wan.wanmarcos.models;

/**
 * Created by Francisco on 1/12/2015.
 */
public class Preference {
    private String preferenceName;
    private int preferenceId;

    public Preference(String name,int id)
    {
        preferenceName=name;
        preferenceId=id;
    }


    public String getPreferenceName() {
        return preferenceName;
    }

    public void setPreferenceName(String preferenceName) {
        this.preferenceName = preferenceName;
    }

    public int getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(int preferenceId) {
        this.preferenceId = preferenceId;
    }
}
