package com.hoaxify.backend.soapclient.participant;

import com.hoaxify.backend.soapclient.SOAPConnector;
import com.hoaxify.backend.soapclient.participant.model.BankaSubeOkuCevap;
import com.hoaxify.backend.soapclient.participant.model.BankaSubeOkuIstem;
import com.hoaxify.backend.soapclient.participant.model.BankaSubeleri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParticipantRepository {
    @Autowired
    private SOAPConnector soapClient;

    public List<BankaSubeleri> getAllParticipantInfo() {
//        ObjectFactory objectFactory = new ObjectFactory();
//        var request = objectFactory.createBankaSubeOkuIstem();
        var request = new BankaSubeOkuIstem();
        request.setBlgTur("TUM");
        BankaSubeOkuCevap response = (BankaSubeOkuCevap) soapClient.callWebService(
                "https://appg.tcmb.gov.tr/mbnbasuse/services/bankaSubeOku", request);
        var tumCvp = response.getTumCvp();
        return tumCvp.getBankaSubeleri();
    }
}
