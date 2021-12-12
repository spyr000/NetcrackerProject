package repository;

import com.opencsv.CSVReader;
import contracts.DigitalTelevisionContract;
import contracts.MobileContract;
import contracts.WiredInternetContract;
import exceptions.CSVParseException;
import person.Gender;
import person.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Fills contract repository with contracts from .csv file
 *
 * @author almtn
 */
public class RepoCSVFiller {
    private final char separator = ';';
    private File file;

    /**
     * @param file .csv file from which the repository will be filled
     */
    public RepoCSVFiller(File file) {
        this.file = file;
    }

    /**
     * Fills repo with contracts from {@link RepoCSVFiller#file}
     *
     * @param repo contract repository to fill
     * @throws CSVParseException if the file cannot be parsed
     */
    public void fillRepo(ContractRepository repo) throws CSVParseException {
        CSVReader csvReader = null;

        try {
            csvReader = new CSVReader(new FileReader(file), separator);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String[]> records;
        try {
            assert csvReader != null;
            records = csvReader.readAll();
        } catch (IOException e) {
            throw new CSVParseException("Failed to read all field from .csv file");
        }
        assert records != null;
        for (String[] record : records) {
            LocalDate start = LocalDate.parse(record[0].replaceAll("\\p{C}", "").trim());
            LocalDate finish = LocalDate.parse(record[1].replaceAll("\\p{C}", "").trim());
            String number = record[2].replaceAll("\\p{C}", "").trim();
            String name = record[3].replaceAll("\\p{C}", "").trim();
            Gender gender = Gender.valueOf(record[4].replaceAll("\\p{C}", "").trim());
            LocalDate dayOfBirth = LocalDate.parse(record[5].replaceAll("\\p{C}", "").trim());
            String[] strPPdata = record[6].replaceAll("\\p{C}", "").trim().split(" ");
            int[] ppData = new int[]{Integer.parseInt(strPPdata[0]), Integer.parseInt(strPPdata[1])};
            String className = record[7].replaceAll("\\p{C}", "").trim();

            switch (className) {
                case ("DigitalTelevisionContract"):
                    String[] channels = record[8].replaceAll("\\p{C}", "").trim().split(" ");
                    repo.add(new DigitalTelevisionContract(repo.getLength(), start, finish, number,
                            new Person(repo.getLength(), name, dayOfBirth, gender, ppData), channels));
                    break;
                case ("MobileContract"):
                    String[] strMobileData = record[8].replaceAll("\\p{C}", "").trim().split(" ");
                    double[] mobileData = new double[]{Double.parseDouble(strMobileData[0]), Double.parseDouble(strMobileData[1]), Double.parseDouble(strMobileData[2])};
                    repo.add(new MobileContract(repo.getLength(), start, finish, number,
                            new Person(repo.getLength(), name, dayOfBirth, gender, ppData), mobileData[0], (int) mobileData[1], mobileData[2]));
                    break;
                case ("WiredInternetContract"):
                    double connectionSpeed = Double.parseDouble(record[8].replaceAll("\\p{C}", "").trim());
                    repo.add(new WiredInternetContract(repo.getLength(), start, finish, number,
                            new Person(repo.getLength(), name, dayOfBirth, gender, ppData), connectionSpeed));
                    break;
            }
        }
    }

    /**
     * @return .csv file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file .csv file
     */
    public void setFile(File file) {
        this.file = file;
    }
}
