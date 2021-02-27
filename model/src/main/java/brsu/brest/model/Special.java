package brsu.brest.model;

public class Special {

    private Integer specialId;

    private String specialName;

    public Special() {
    }

    public Special(String specialName) { this.specialName = specialName; }

    public Integer getSpecialId() { return specialId; }

    public void setSpecialId(Integer specialId) { this.specialId = specialId; }

    public String getSpecialName() { return specialName; }

    public void setSpecialName(String specialName) { this.specialName = specialName;  }

    @Override
    public String toString() {
        return "Special{" +
                "specialId=" + specialId +
                ", specialName='" + specialName + '\'' +
                '}';
    }
}
