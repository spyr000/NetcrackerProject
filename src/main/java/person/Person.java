package person;

import java.util.Date;

public class Person {
    private int id;
    private String name;
    private Date dateOfBirth;
    private Gender gender;
    private int[] passportData;

    public Person(int id, String name, Date dateOfBirth, Gender gender, int[] passportData) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.passportData = passportData;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int[] getPassportData() {
        return passportData;
    }

    public void setPassportData(int[] passportData) {
        this.passportData = passportData;
    }

    public int getAge() {
        return (new Date()).getYear() - dateOfBirth.getYear();
    }
}