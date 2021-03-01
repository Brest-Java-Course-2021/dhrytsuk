package brsu.brest.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Special special = (Special) o;
        return Objects.equals(specialId, special.specialId) && Objects.equals(specialName, special.specialName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialId, specialName);
    }
}
