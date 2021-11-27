package person;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

/**
 * @author almtn
 */
public class Person implements Comparable<Person>{
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
     * @throws RuntimeException if you try to assign an integer array longer than 2 to the passport data array
     */
    public void setPassportData(int[] passportData) {
        if(passportData.length > 2)
            throw new RuntimeException("Passport data must be an integer array of length 2!");
        else
            this.passportData = passportData;
    }

    /**
     * @return Person's age
     */
    public int getAge() {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        else if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        else return ((Person) obj).id == this.id && ((Person) obj).name.equals(this.name) &&
                    ((Person) obj).dateOfBirth.equals(dateOfBirth) && ((Person) obj).gender == this.gender &&
                    ((Person) obj).passportData[0] == this.passportData[0] && ((Person) obj).passportData[1] == this.passportData[1];
    }

    @Override
    public int compareTo(@NotNull Person o) {
        if(this.equals(o))
            return 0;
        if(!this.name.equals(o.name))
            return this.name.compareTo(o.name);
        else
            return dateOfBirth.compareTo(o.dateOfBirth);
    }
}