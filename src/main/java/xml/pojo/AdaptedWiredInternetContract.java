package xml.pojo;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import person.Person;
import xml.adapters.LocalDateAdapter;

import java.time.LocalDate;

@XmlSeeAlso({AdaptedPerson.class})
public class AdaptedWiredInternetContract {
    private LocalDate finishDate;
    private int id;
    private String number;
    private LocalDate startDate;
    private Person owner;
    private double connectionSpeed;

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

    @XmlAttribute(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @XmlAttribute(name = "startDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @XmlAttribute(name = "connectionSpeed")
    public double getConnectionSpeed() {
        return connectionSpeed;
    }

    public void setConnectionSpeed(double connectionSpeed) {
        this.connectionSpeed = connectionSpeed;
    }

    @XmlElement(name = "owner")
    public Person getOwner() {
        return owner;
    }

    public void setOwnerBean(Person owner) {
        this.owner = owner;
    }

}