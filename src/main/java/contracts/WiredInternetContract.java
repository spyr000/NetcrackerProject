package contracts;

import person.Person;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * @author almtn
 */
public class WiredInternetContract extends Contract {

    private double connectionSpeed;

    /**
     * @return Connection speed
     */
    public double getConnectionSpeed() {
        return connectionSpeed;
    }

    /**
     * Sets connection speed
     * @param connectionSpeed Connection speed
     */
    public void setConnectionSpeed(double connectionSpeed) {
        this.connectionSpeed = connectionSpeed;
    }
    /**
     * @param id Contract ID
     * @param startTime Start date of the contract
     * @param finishTime Contract expiration date
     * @param number Contract number
     * @param owner Contract owner
     * @param connectionSpeed Connection speed
     */
    public WiredInternetContract(int id, LocalDate startTime, LocalDate finishTime, String number, Person owner, double connectionSpeed) {
        super(id, startTime, finishTime, number, owner);
        this.connectionSpeed = connectionSpeed;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getConnectionSpeed();
    }
}
