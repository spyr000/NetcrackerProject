package contracts;

import person.Person;

import java.time.LocalDate;

/**
 * @author almtn
 */
public abstract class Contract {
    private int id;
    private LocalDate startDate;
    private LocalDate finishDate;
    private int number;
    private Person owner;

    /**
     * @param id Contract ID
     * @param startDate Сontract start date
     * @param finishDate Сontract expiration date
     * @param number Contract number
     * @param owner Contract owner
     */
    public Contract(int id, LocalDate startDate, LocalDate finishDate, int number, Person owner) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.number = number;
        this.owner = owner;
    }

    /**
     * @return Contract ID {@link Contract#id}
     */
    public int getId() {
        return id;
    }

    /**
     * Sets Contract ID {@link Contract#id}
     * @param id Contract ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Сontract start date {@link Contract#startDate}
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets Сontract start date {@link Contract#startDate}
     * @param startDate Сontract start date
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * @return Сontract expiration date {@link Contract#finishDate}
     */
    public LocalDate getFinishDate() {
        return finishDate;
    }

    /**
     * Sets Сontract expiration date {@link Contract#finishDate}
     * @param finishDate Сontract expiration date
     */
    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    /**
     * @return Contract number {@link Contract#number}
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets Contract number {@link Contract#number}
     * @param number Contract number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return Contract owner {@link Contract#owner}
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * Sets Contract owner {@link Contract#owner}
     * @param owner Contract owner
     */
    public void setOwner(Person owner) {
        this.owner = owner;
    }
}

