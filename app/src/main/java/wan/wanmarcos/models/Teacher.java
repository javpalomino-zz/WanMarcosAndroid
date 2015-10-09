package wan.wanmarcos.models;

import java.util.List;

/**
 * Created by carlos-pc on 06/10/15.
 */
public class Teacher {
    private String name;
    private int raiting;
    private List<String> faculties;
    private String description;

    public Teacher(String name, int raiting, List<String> faculties, String description) {
        this.name = name;
        this.raiting = raiting;
        this.faculties = faculties;
        this.description = description;
    }
    public Teacher(Teacher old) {
        this.name = old.getName();
        this.raiting = old.getRaiting();
        this.faculties = old.faculties;
        this.description = old.getDescription();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }

    public String getFaculties() {
        String fac="";
        int i=faculties.size();
        if(i>0){
            fac=fac+faculties.get(0).toString();
            for(int j=1;j<i;j++){
                fac=fac+", "+faculties.get(j).toString();
            }
        }
        return fac;
    }

    public void setFaculties(List<String> faculties) {
        this.faculties = faculties;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
