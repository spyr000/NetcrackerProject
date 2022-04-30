package xml.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import person.Person;
import xml.pojo.AdaptedPerson;

public class PersonAdapter extends XmlAdapter<AdaptedPerson, Person> {
    @Override
    public Person unmarshal(AdaptedPerson adaptedPerson) throws Exception {
        return new Person(
                adaptedPerson.getId(),
                adaptedPerson.getName(),
                adaptedPerson.getDateOfBirth(),
                adaptedPerson.getGender(),
                adaptedPerson.getPassportData()
        );
    }

    @Override
    public AdaptedPerson marshal(Person person) throws Exception {
        AdaptedPerson ad = new AdaptedPerson();
        ad.setId(person.getId());
        ad.setGender(person.getGender());
        ad.setName(person.getName());
        ad.setDateOfBirth(person.getDateOfBirth());
        ad.setPassportData(person.getPassportData());
        return ad;
    }
}
