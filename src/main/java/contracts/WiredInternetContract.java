package contracts;

import person.Person;

import java.time.LocalDate;
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
    public WiredInternetContract(int id, LocalDate startTime, LocalDate finishTime, int number, Person owner, double connectionSpeed) {
        super(id, startTime, finishTime, number, owner);
        this.connectionSpeed = connectionSpeed;
    }
}