package contracts;

import person.Person;

import java.time.LocalDate;

public abstract class Contract {
    private int id;
    private LocalDate startTime;
    private LocalDate finishTime;
    private int number;
    private Person owner;

    public Contract(int id, LocalDate startTime, LocalDate finishTime, int number, Person owner) {
        this.id = id;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.number = number;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDate finishTime) {
        this.finishTime = finishTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}

