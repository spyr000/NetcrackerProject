package contracts;

import person.Person;

import java.util.Date;

public abstract class Contract {
    private int id;
    private Date startTime;
    private Date finishTime;
    private int number;
    private Person owner;

    public Contract(int id, Date startTime, Date finishTime, int number, Person owner) {
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
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

