package xml.adapters;

import contracts.DigitalTelevisionContract;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import xml.pojo.AdaptedDigitalTelevisionContract;
import xml.pojo.AdaptedMobileContract;

public class DigitalTelevisionContractAdapter extends XmlAdapter<AdaptedDigitalTelevisionContract, DigitalTelevisionContract> {
    @Override
    public DigitalTelevisionContract unmarshal(AdaptedDigitalTelevisionContract adaptedDigitalTelevisionContract) throws Exception {
        return new DigitalTelevisionContract(
                adaptedDigitalTelevisionContract.getId(),
                adaptedDigitalTelevisionContract.getStartDate(),
                adaptedDigitalTelevisionContract.getFinishDate(),
                adaptedDigitalTelevisionContract.getNumber(),
                adaptedDigitalTelevisionContract.getOwner(),
                adaptedDigitalTelevisionContract.getChannelPackage()
        );
    }

    @Override
    public AdaptedDigitalTelevisionContract marshal(DigitalTelevisionContract digitalTelevisionContract) throws Exception {
        AdaptedDigitalTelevisionContract ad = new AdaptedDigitalTelevisionContract();
        ad.setId(digitalTelevisionContract.getId());
        ad.setStartDate(digitalTelevisionContract.getStartDate());
        ad.setFinishDate(digitalTelevisionContract.getFinishDate());
        ad.setNumber(digitalTelevisionContract.getNumber());
        ad.setChannelPackage(digitalTelevisionContract.getChannelPackage());
        ad.setOwnerBean(digitalTelevisionContract.getOwner());
        return ad;
    }
}
