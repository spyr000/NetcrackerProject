package xml.adapters;

import contracts.MobileContract;
import contracts.WiredInternetContract;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import xml.pojo.AdaptedMobileContract;
import xml.pojo.AdaptedWiredInternetContract;

public class WiredInternetContractAdapter extends XmlAdapter<AdaptedWiredInternetContract, WiredInternetContract> {
    @Override
    public WiredInternetContract unmarshal(AdaptedWiredInternetContract adaptedWiredInternetContract) throws Exception {
        return new WiredInternetContract(
                adaptedWiredInternetContract.getId(),
                adaptedWiredInternetContract.getStartDate(),
                adaptedWiredInternetContract.getFinishDate(),
                adaptedWiredInternetContract.getNumber(),
                adaptedWiredInternetContract.getOwner(),
                adaptedWiredInternetContract.getConnectionSpeed()
        );
    }

    @Override
    public AdaptedWiredInternetContract marshal(WiredInternetContract wiredInternetContract) throws Exception {
        AdaptedWiredInternetContract ad = new AdaptedWiredInternetContract();
        ad.setId(wiredInternetContract.getId());
        ad.setStartDate(wiredInternetContract.getStartDate());
        ad.setFinishDate(wiredInternetContract.getFinishDate());
        ad.setNumber(wiredInternetContract.getNumber());
        ad.setConnectionSpeed(wiredInternetContract.getConnectionSpeed());
        ad.setOwnerBean(wiredInternetContract.getOwner());
        return ad;
    }
}
