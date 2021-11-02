package person;

import java.time.LocalDate;

/**
 * @author almtn
 */
public class Person {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private int[] passportData;

    /**
     * @param id Person's ID
     * @param name Person's full name
     * @param dateOfBirth Person's date of birth
     * @param gender Person's gender
     * @param passportData Person's series and number of passport
     */
    public Person(int id, String name, LocalDate dateOfBirth, Gender gender, int[] passportData) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.passportData = passportData;
    }

    /**
     * @return Person's ID {@link Person#id}
     */
    public int getId() {
        return id;
    }

    /**
     * Sets Person's ID {@link Person#id}
     * @param id Person's ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Person's full name {@link Person#name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets Person's full name {@link Person#name}
     * @param name Person's full name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Person's date of birth {@link Person#dateOfBirth}
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets Person's date of birth {@link Person#dateOfBirth}
     * @param dateOfBirth Person's date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return Person's gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets Person's gender
     * @param gender Person's gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * @return Person's passport data {@link Person#passportData}
     */
    public int[] getPassportData() {
        return passportData;
    }

    /**
     * Sets Person's passport data {@link Person#passportData}
     * @param passportData Person's series and number of passport
     */
    public void setPassportData(int[] passportData) {
        this.passportData = passportData;
    }

    /**
     * @return Person's age
     */
    public int getAge() {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }
}