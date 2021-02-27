package brsu.brest.model;

public class Applicant {

    private Integer applicantId;

    private String firstName;

    private String lastName;

    private String email;

    private Integer specialId;

    public Integer getApplicantId() { return applicantId; }

    public void setApplicantId(Integer applicantId) { this.applicantId = applicantId; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email;  }

    public void setEmail(String email) { this.email = email; }

    public Integer getSpecialId() { return specialId; }

    public void setSpecialId(Integer specialId) { this.specialId = specialId; }

    @Override
    public String toString() {
        return "Applicant{" +
                "applicantId=" + applicantId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", specialId=" + specialId +
                '}';
    }
}
