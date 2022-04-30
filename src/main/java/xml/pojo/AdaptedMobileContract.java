package xml.pojo;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import person.Person;
import xml.adapters.LocalDateAdapter;

import java.time.LocalDate;

@XmlSeeAlso({AdaptedPerson.class})
public class AdaptedMobileContract {
    private LocalDate finishDate;
    private int id;
    private double minutesAmount;
    private String number;
    private int smsAmount;
    private LocalDate startDate;
    private double trafficGbAmount;
    private Person owner;

    @XmlAttribute(name = "finishDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlAttribute(name = "minutesAmount")
    public double getMinutesAmount() {
        return minutesAmount;
    }

    public void setMinutesAmount(double minutesAmount) {
        this.minutesAmount = minutesAmount;
    }

    @XmlAttribute(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @XmlAttribute(name = "smsAmount")
    public int getSmsAmount() {
        return smsAmount;
    }

    public void setSmsAmount(int smsAmount) {
        this.smsAmount = smsAmount;
    }

    @XmlAttribute(name = "startDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @XmlAttribute(name = "trafficGbAmount")
    public double getTrafficGbAmount() {
        return trafficGbAmount;
    }

    public void setTrafficGbAmount(double trafficGbAmount) {
        this.trafficGbAmount = trafficGbAmount;
    }

    @XmlElement(name = "owner")
    public Person getOwner() {
        return owner;
    }

    public void setOwnerBean(Person owner) {
        this.owner = owner;
    }

}