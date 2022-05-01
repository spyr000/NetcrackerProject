package repository;

import contracts.Contract;
import contracts.DigitalTelevisionContract;
import contracts.MobileContract;
import contracts.WiredInternetContract;
import person.Gender;
import person.Person;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Class for JDBC management
 *
 * @author almtn
 */
public class JDBCManager {
    public static final String JDBC_DRIVER = "org.h2.Driver";
    public static final String USER = "sa";
    public static final String PASS = "ыф";
    public static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
    private static final String DTV_CONTRACTS = "DTV_CONTRACTS";
    private static final String MOBILE_CONTRACTS = "MOBILE_CONTRACTS";
    private static final String WI_CONTRACTS = "WI_CONTRACTS";
    private static final String CONTRACTS = "CONTRACTS";
    private static final String PERSONS = "PERSONS";
    private static final String[] INITIAL_STATEMENTS = {
            """
                    DROP TABLE IF EXISTS %s;""".formatted(DTV_CONTRACTS),
            """
                    DROP TABLE IF EXISTS %s;""".formatted(MOBILE_CONTRACTS),
            """
                    DROP TABLE IF EXISTS %s;""".formatted(WI_CONTRACTS),
            """
                    DROP TABLE IF EXISTS %s;""".formatted(CONTRACTS),
            """
                    DROP TABLE IF EXISTS %s;""".formatted(PERSONS),
            """
                    CREATE TABLE %s(
                    PERSON_ID INT PRIMARY KEY,
                    NAME VARCHAR(255),
                    DATE_OF_BIRTH DATE,
                    IS_FEMALE BOOLEAN,
                    PASSPORT_DATA INTEGER ARRAY[2]
                    )""".formatted(PERSONS),
            """
                    CREATE TABLE %s(
                    CONTRACT_ID INT PRIMARY KEY,
                    TYPE VARCHAR(255),
                    START_DATE DATE,
                    FINISH_DATE DATE,
                    NUMBER VARCHAR(255),
                    OWNER_ID INT,
                    FOREIGN KEY (OWNER_ID) REFERENCES %s(PERSON_ID)
                    );""".formatted(CONTRACTS, PERSONS),
            """
                    CREATE TABLE %s(
                    CONTRACT_ID INT PRIMARY KEY,
                    CONNECTION_SPEED DOUBLE PRECISION,
                    FOREIGN KEY (CONTRACT_ID) REFERENCES %s(CONTRACT_ID)
                    );""".formatted(WI_CONTRACTS, CONTRACTS),
            """
                    CREATE TABLE %s(
                    CONTRACT_ID INT PRIMARY KEY,
                    MINUTES_AMT DOUBLE PRECISION,
                    SMS_AMT INT,
                    TRAFFIC_GB_AMT DOUBLE PRECISION,
                    FOREIGN KEY (CONTRACT_ID) REFERENCES %s(CONTRACT_ID)
                    );""".formatted(MOBILE_CONTRACTS, CONTRACTS),
            """
                    CREATE TABLE %s(
                    DTV_CONTRACT_ID INT PRIMARY KEY,
                    CONTRACT_ID INT,
                    CHANNEL VARCHAR(255),
                    FOREIGN KEY (CONTRACT_ID) REFERENCES %s(CONTRACT_ID)
                    );""".formatted(DTV_CONTRACTS, CONTRACTS)
    };
    private static final String INSERT_PREPARED_STATEMENT_BEGINNING = """
            INSERT INTO %s VALUES(%s);
            """;
    private static final String INSERT_PREPARED_STATEMENT_FOR_DTV = """
            INSERT INTO %s VALUES((SELECT COUNT(*)
                                  FROM DTV_CONTRACTS),%s);
            """;
    private static final String SELECT_PREPARED_SINGLE_PERSON = """
            SELECT COUNT(*)
            FROM %s
            WHERE PERSON_ID = ?;
            """.formatted(PERSONS);
    private static final String SELECT_BEGINNING = """
            SELECT *
            FROM %s;
            """;
    private static final String SELECT_PERSON_BEGINNING_WHERE = """
            SELECT *
            FROM %s
            WHERE PERSON_ID = ?;
            """;
    private static final String SELECT_CONTRACT_BEGINNING_WHERE = """
            SELECT *
            FROM %s
            WHERE CONTRACT_ID = ?;
            """;
    private String jdbcDriver;
    private String user;
    private String pass;
    private String url;

    /**
     * @param jdbcDriver JDBC driver
     * @param user       Username
     * @param pass       Password
     * @param url        Database url
     */
    private JDBCManager(String jdbcDriver, String user, String pass, String url) {
        this.jdbcDriver = jdbcDriver;
        this.user = user;
        this.pass = pass;
        this.url = url;
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Factory method for creating new JDBCManager class object
     * and accordingly the new database with empty necessary tables
     *
     * @param jdbcDriver JDBC driver
     * @param user       Username
     * @param pass       Password
     * @param url        Database url
     * @return JDBCManager object
     */
    public static JDBCManager newEmptyDB(String jdbcDriver, String user, String pass, String url) {
        JDBCManager jdbcManager = new JDBCManager(jdbcDriver, user, pass, url);
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
            statement = connection.createStatement();
            for (String initialStatement : INITIAL_STATEMENTS) {
                statement.executeUpdate(initialStatement);
            }
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
        return jdbcManager;
    }

    /**
     * Factory method for creating new JDBCManager class object
     *
     * @param jdbcDriver JDBC driver
     * @param user       Username
     * @param pass       Password
     * @param url        Database url
     * @return JDBCManager object
     */
    public static JDBCManager newDB(String jdbcDriver, String user, String pass, String url) {
        return new JDBCManager(jdbcDriver, user, pass, url);
    }

    /**
     * Inserts contracts from contract repository to the database
     *
     * @param contractRepository Contract repository
     */
    public void insertContracts(ContractRepository contractRepository) {
        int length = contractRepository.getLength();
        Contract[] contracts = new Contract[length];
        for (int i = 0; i < length; i++) {
            contracts[i] = contractRepository.getContractByIndex(i);
        }

        String sqlContracts = INSERT_PREPARED_STATEMENT_BEGINNING.formatted(CONTRACTS, "? ,? ,? ,? ,? ,?");
        String sqlDTV = INSERT_PREPARED_STATEMENT_FOR_DTV.formatted(DTV_CONTRACTS, "?, ?");
        String sqlMobile = INSERT_PREPARED_STATEMENT_BEGINNING.formatted(MOBILE_CONTRACTS, "?, ?, ?, ?");
        String sqlWI = INSERT_PREPARED_STATEMENT_BEGINNING.formatted(WI_CONTRACTS, "?, ?");

        int dtvAmt = 0, mobileAmt = 0, wiAmt = 0;

        Connection connection = null;
        PreparedStatement contractStatement = null;
        PreparedStatement dtvStatement = null;
        PreparedStatement mobileStatement = null;
        PreparedStatement wiStatement = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);

            contractStatement = connection.prepareStatement(sqlContracts);
            dtvStatement = connection.prepareStatement(sqlDTV);
            mobileStatement = connection.prepareStatement(sqlMobile);
            wiStatement = connection.prepareStatement(sqlWI);

            for (Contract contract : contracts) {
                Person person = contract.getOwner();
                if (!isPersonExists(person.getId())) insertPerson(person);
                contractStatement.setInt(1, contract.getId());
                contractStatement.setString(2, contract.getClass().getSimpleName());
                contractStatement.setString(3, contract.getStartDate().toString());
                contractStatement.setString(4, contract.getFinishDate().toString());
                contractStatement.setString(5, contract.getNumber());
                contractStatement.setInt(6, person.getId());
                contractStatement.addBatch();

                if (DigitalTelevisionContract.class.equals(contract.getClass())) {
                    for (String s : ((DigitalTelevisionContract) contract).getChannelPackage()) {
                        dtvStatement.setInt(1, contract.getId());
                        dtvStatement.setString(2, s);
                        dtvStatement.addBatch();
                    }
                    ++dtvAmt;
                } else if (MobileContract.class.equals(contract.getClass())) {
                    mobileStatement.setInt(1, contract.getId());
                    mobileStatement.setDouble(2, ((MobileContract) contract).getMinutesAmount());
                    mobileStatement.setInt(3, ((MobileContract) contract).getSmsAmount());
                    mobileStatement.setDouble(4, ((MobileContract) contract).getTrafficGbAmount());
                    mobileStatement.addBatch();
                    ++mobileAmt;
                } else if (WiredInternetContract.class.equals(contract.getClass())) {
                    wiStatement.setInt(1, contract.getId());
                    wiStatement.setDouble(2, ((WiredInternetContract) contract).getConnectionSpeed());
                    wiStatement.addBatch();
                    ++wiAmt;
                }
            }
            contractStatement.executeBatch();
            if (dtvAmt > 0) dtvStatement.executeBatch();
            if (mobileAmt > 0) mobileStatement.executeBatch();
            if (wiAmt > 0) wiStatement.executeBatch();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (contractStatement != null) contractStatement.close();
                if (connection != null) connection.close();
                if (dtvStatement != null) dtvStatement.close();
                if (mobileStatement != null) mobileStatement.close();
                if (wiStatement != null) wiStatement.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
    }

    /**
     * Сhecks if a person with this person_id exists in the persons table
     *
     * @param personId person_id
     * @return true if person with this person_id exists in the persons table,
     * else false
     */
    private boolean isPersonExists(int personId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try {
            connection = DriverManager.getConnection(url, user, pass);
            statement = connection.prepareStatement(SELECT_PREPARED_SINGLE_PERSON, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, personId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) result = resultSet.getInt(1) == 1;
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Inserts person to the database
     *
     * @param person Person
     */
    private void insertPerson(Person person) {
        String sql = INSERT_PREPARED_STATEMENT_BEGINNING.formatted(PERSONS, "? ,? ,? ,? ,?");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, person.getId());
            statement.setString(2, person.getName());
            statement.setString(3, person.getDateOfBirth().toString());
            statement.setBoolean(4, Gender.Female == person.getGender());
            Array arr = connection.createArrayOf("INT", Arrays.stream(person.getPassportData()).mapToObj(o -> (Object) o).toArray());
            statement.setArray(5, arr);
            statement.executeUpdate();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
    }

    /**
     * Adds all contracts from DB to the Contract repository object
     *
     * @param contractRepository Contract Repository
     */
    public void getContractsFromDB(ContractRepository contractRepository) {
        String sql = SELECT_BEGINNING.formatted(CONTRACTS);
        String sqlWhere = SELECT_PERSON_BEGINNING_WHERE.formatted(PERSONS);
        Connection connection = null;
        Statement statement = null;
        PreparedStatement personPreparedStatement = null;
        PreparedStatement contractPreparedStatement = null;
        ResultSet contractsResultSet = null;
        ResultSet personsResultSet = null;
        ResultSet specifiedResultSet = null;
        Contract contractToAdd = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
            statement = connection.createStatement();
            personPreparedStatement = connection.prepareStatement(sqlWhere);
            contractsResultSet = statement.executeQuery(sql);


            while (contractsResultSet.next()) {
                int id = contractsResultSet.getInt(1);
                String type = contractsResultSet.getString(2);

                LocalDate startDate = LocalDate.parse(contractsResultSet.getString(3));
                LocalDate finishDate = LocalDate.parse(contractsResultSet.getString(4));
                String number = contractsResultSet.getString(5);
                int personID = contractsResultSet.getInt(6);
                personPreparedStatement.setInt(1, personID);
                Person person = null;
                if (isPersonExists(personID)) {
                    personsResultSet = personPreparedStatement.executeQuery();
                    if (personsResultSet.next()) {
                        int[] arr = new int[2];
                        ResultSet tempResultSet = personsResultSet.getArray(5).getResultSet();
                        int i = 0;
                        while (tempResultSet.next()) {
                            arr[i] = tempResultSet.getInt(2);
                            i++;
                        }
                        tempResultSet.close();
                        person = new Person(
                                personsResultSet.getInt(1),
                                personsResultSet.getString(2),
                                personsResultSet.getDate(3).toLocalDate(),
                                personsResultSet.getBoolean(4) ? Gender.Female : Gender.Male,
                                arr
                        );
                    }
                }

                switch (type) {
                    case "DigitalTelevisionContract" -> {
                        contractPreparedStatement = connection.prepareStatement(
                                SELECT_CONTRACT_BEGINNING_WHERE.formatted(DTV_CONTRACTS)
                        );
                        contractPreparedStatement.setInt(1, id);
                        List<String> channels = new ArrayList<>();
                        specifiedResultSet = contractPreparedStatement.executeQuery();
                        while (specifiedResultSet.next()) {
                            channels.add(specifiedResultSet.getString(3));
                        }
                        contractToAdd = new DigitalTelevisionContract(id,
                                startDate,
                                finishDate,
                                number,
                                person,
                                channels.toArray(new String[]{}));
                    }
                    case "MobileContract" -> {
                        contractPreparedStatement = connection.prepareStatement(
                                SELECT_CONTRACT_BEGINNING_WHERE.formatted(MOBILE_CONTRACTS)
                        );
                        contractPreparedStatement.setInt(1, id);
                        specifiedResultSet = contractPreparedStatement.executeQuery();
                        if (specifiedResultSet.next()) {
                            contractToAdd = new MobileContract(
                                    id,
                                    startDate,
                                    finishDate,
                                    number, person,
                                    specifiedResultSet.getDouble(2),
                                    specifiedResultSet.getInt(3),
                                    specifiedResultSet.getDouble(4)
                            );
                        }
                    }
                    case "WiredInternetContract" -> {
                        contractPreparedStatement = connection.prepareStatement(
                                SELECT_CONTRACT_BEGINNING_WHERE.formatted(WI_CONTRACTS)
                        );
                        contractPreparedStatement.setInt(1, id);
                        specifiedResultSet = contractPreparedStatement.executeQuery();
                        if (specifiedResultSet.next()) {
                            contractToAdd = new WiredInternetContract(
                                    id,
                                    startDate,
                                    finishDate,
                                    number, person,
                                    specifiedResultSet.getDouble(2)
                            );
                        }
                    }
                }
                contractRepository.add(Objects.requireNonNull(contractToAdd));
            }
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (personPreparedStatement != null) personPreparedStatement.close();
                if (contractPreparedStatement != null) contractPreparedStatement.close();
                if (connection != null) connection.close();
                if (personsResultSet != null) personsResultSet.close();
                if (contractsResultSet != null) contractsResultSet.close();
                if (specifiedResultSet != null) specifiedResultSet.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
    }
}
