package repository;

import contracts.Contract;
import contracts.DigitalTelevisionContract;
import contracts.MobileContract;
import contracts.WiredInternetContract;
import person.Gender;
import person.Person;

import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;

public class JDBCManager {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String USER = "sa";
    static final String PASS = "ыф";
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
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

    public static void setupDB() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
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
    }

    public static void main(String[] args) {

        Contract[] contracts = new Contract[]{
                new MobileContract(1,
                        LocalDate.of(2021, 2, 24),
                        LocalDate.of(2022, 3, 18),
                        "3",
                        new Person(2,
                                "Testerov Tester Testerovich",
                                LocalDate.of(2001, Calendar.APRIL, 28),
                                Gender.Male,
                                new int[]{2016, 137567}),
                        12,
                        34,
                        56),
                new DigitalTelevisionContract(
                        2,
                        LocalDate.of(2021, 1, 24),
                        LocalDate.of(2022, 4, 18),
                        "4",
                        new Person(3,
                                "Testerov Tester Testerovich",
                                LocalDate.of(2002, Calendar.MAY, 29),
                                Gender.Male,
                                new int[]{2016, 134567}),
                        new String[]{"NTV", "TNT", "STS", "MatchTV"}
                ),
                new WiredInternetContract(
                        3,
                        LocalDate.of(2021, 1, 24),
                        LocalDate.of(2022, 2, 18),
                        "5",
                        new Person(4,
                                "Testerova Tester Testerovna",
                                LocalDate.of(2003, 5, 25),
                                Gender.Female,
                                new int[]{2016, 134667}),
                        23.7
                )
        };

        setupDB();

        insertContracts(contracts);
//        Person p = new Person(1, "Testerov Tester Testerovich",
//                LocalDate.of(2001, 3, 23), Gender.Male, new int[]{2016, 234567});
//        insertPerson(p);
//        System.out.println(isPersonExists(p));
    }

    public static boolean isPersonExists(Person person) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.prepareStatement(SELECT_PREPARED_SINGLE_PERSON, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, person.getId());
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

    public static void insertContracts(Contract[] contracts) {
        String sql_contracts = INSERT_PREPARED_STATEMENT_BEGINNING.formatted(CONTRACTS, "? ,? ,? ,? ,?");
        String sql_dtv = INSERT_PREPARED_STATEMENT_FOR_DTV.formatted(DTV_CONTRACTS, "?, ?");
        String sql_mobile = INSERT_PREPARED_STATEMENT_BEGINNING.formatted(MOBILE_CONTRACTS, "?, ?, ?, ?");
        String sql_wi = INSERT_PREPARED_STATEMENT_BEGINNING.formatted(WI_CONTRACTS, "?, ?");

        int dtv_amt = 0, mobile_amt = 0, wi_amt = 0;

        Connection connection = null;
        PreparedStatement contract_statement = null;
        PreparedStatement dtv_statement = null;
        PreparedStatement mobile_statement = null;
        PreparedStatement wi_statement = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            contract_statement = connection.prepareStatement(sql_contracts);
            dtv_statement = connection.prepareStatement(sql_dtv);
            mobile_statement = connection.prepareStatement(sql_mobile);
            wi_statement = connection.prepareStatement(sql_wi);

            for (Contract contract : contracts) {
                Person person = contract.getOwner();
                if (!isPersonExists(person)) insertPerson(person);
                contract_statement.setInt(1, contract.getId());
                contract_statement.setString(2, contract.getStartDate().toString());
                contract_statement.setString(3, contract.getFinishDate().toString());
                contract_statement.setString(4, contract.getNumber());
                contract_statement.setInt(5, person.getId());
                contract_statement.addBatch();

                if (DigitalTelevisionContract.class.equals(contract.getClass())) {
                    for (String s : ((DigitalTelevisionContract) contract).getChannelPackage()) {
                        dtv_statement.setInt(1, contract.getId());
                        dtv_statement.setString(2, s);
                        dtv_statement.addBatch();
                    }
                    ++dtv_amt;
                } else if (MobileContract.class.equals(contract.getClass())) {
                    mobile_statement.setInt(1, contract.getId());
                    mobile_statement.setDouble(2, ((MobileContract) contract).getMinutesAmount());
                    mobile_statement.setInt(3, ((MobileContract) contract).getSmsAmount());
                    mobile_statement.setDouble(4, ((MobileContract) contract).getTrafficGbAmount());
                    mobile_statement.addBatch();
                    ++mobile_amt;
                } else if (WiredInternetContract.class.equals(contract.getClass())) {
                    wi_statement.setInt(1, contract.getId());
                    wi_statement.setDouble(2, ((WiredInternetContract) contract).getConnectionSpeed());
                    wi_statement.addBatch();
                    ++wi_amt;
                }
            }
            contract_statement.executeBatch();
            if (dtv_amt > 0) dtv_statement.executeBatch();
            if (mobile_amt > 0) mobile_statement.executeBatch();
            if (wi_amt > 0) wi_statement.executeBatch();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (contract_statement != null) contract_statement.close();
                if (connection != null) connection.close();
                if (dtv_statement != null) dtv_statement.close();
                if (mobile_statement != null) mobile_statement.close();
                if (wi_statement != null) wi_statement.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }

    }

    public static void insertPerson(Person person) {
        String sql = INSERT_PREPARED_STATEMENT_BEGINNING.formatted(PERSONS, "? ,? ,? ,? ,?");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
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
}
