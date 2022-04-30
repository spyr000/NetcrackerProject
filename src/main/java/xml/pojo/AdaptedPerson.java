package xml.pojo;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import person.Gender;
import xml.adapters.LocalDateAdapter;

import java.time.LocalDate;

public class AdaptedPerson {
    private LocalDate dateOfBirth;
    private Gender gender;
    private int id;
    private String name;
    private int[] passportData;

    @XmlAttribute(name = "dateOfBirth")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @XmlAttribute(name = "gender")
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "passportData")
    public int[] getPassportData() {
        return passportData;
    }

    public void setPassportData(int[] passportData) {
        this.passportData = passportData;
    }
}