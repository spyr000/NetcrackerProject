package contracts;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import person.Person;
import xml.adapters.MobileContractAdapter;
import xml.adapters.WiredInternetContractAdapter;
import xml.pojo.AdaptedWiredInternetContract;

import java.time.LocalDate;

/**
 * @author almtn
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlJavaTypeAdapter(WiredInternetContractAdapter.class)
public class WiredInternetContract extends Contract {

    private double connectionSpeed;

    /**
     * @param id              Contract ID
     * @param startTime       Start date of the contract
     * @param finishTime      Contract expiration date
     * @param number          Contract number
     * @param owner           Contract owner
     * @param connectionSpeed Connection speed
     */
    public WiredInternetContract(int id, LocalDate startTime, LocalDate finishTime, String number, Person owner, double connectionSpeed) {
        super(id, startTime, finishTime, number, owner);
        this.connectionSpeed = connectionSpeed;
    }

    private WiredInternetContract() {super();}

    /**
     * @return Connection speed
     */
    public double getConnectionSpeed() {
        return connectionSpeed;
    }

    /**
     * Sets connection speed
     *
     * @param connectionSpeed Connection speed
     */
    public void setConnectionSpeed(double connectionSpeed) {
        this.connectionSpeed = connectionSpeed;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getConnectionSpeed();
    }
}
