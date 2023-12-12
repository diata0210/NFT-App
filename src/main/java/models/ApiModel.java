package models;

public abstract class ApiModel {
    int id;
    int niftyType;
    String creatorName;
    String niftyTitle;
    public int getId() {
        return id;
    }

    public int getNiftyType() {
        return niftyType;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNiftyType(int niftyType) {
        this.niftyType = niftyType;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getNiftyTitle() {
        return niftyTitle;
    }

    public void setNiftyTitle(String niftyTitle) {
        this.niftyTitle = niftyTitle;
    }

}
