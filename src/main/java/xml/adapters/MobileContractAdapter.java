package xml.adapters;

import contracts.MobileContract;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import xml.pojo.AdaptedMobileContract;

public class MobileContractAdapter extends XmlAdapter<AdaptedMobileContract, MobileContract> {
    @Override
    public MobileContract unmarshal(AdaptedMobileContract adaptedMobileContract) throws Exception {
        return new MobileContract(
                adaptedMobileContract.getId(),
                adaptedMobileContract.getStartDate(),
                adaptedMobileContract.getFinishDate(),
                adaptedMobileContract.getNumber(),
                adaptedMobileContract.getOwner(),
                adaptedMobileContract.getMinutesAmount(),
                adaptedMobileContract.getSmsAmount(),
                adaptedMobileContract.getTrafficGbAmount()
        );
    }

    @Override
    public AdaptedMobileContract marshal(MobileContract mobileContract) throws Exception {
        AdaptedMobileContract ad = new AdaptedMobileContract();
        ad.setId(mobileContract.getId());
        ad.setStartDate(mobileContract.getStartDate());
        ad.setFinishDate(mobileContract.getFinishDate());
        ad.setNumber(mobileContract.getNumber());
        ad.setMinutesAmount(mobileContract.getMinutesAmount());
        ad.setSmsAmount(mobileContract.getSmsAmount());
        ad.setTrafficGbAmount(mobileContract.getTrafficGbAmount());
        ad.setOwnerBean(mobileContract.getOwner());
        return ad;
    }
}
