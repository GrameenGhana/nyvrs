package org.motechproject.nyvrs.service.impl;

import org.motechproject.nyvrs.domain.ClientRegistration;
import org.motechproject.nyvrs.repository.ClientRegistrationDataService;
import org.motechproject.nyvrs.service.CampaignService;
import org.motechproject.nyvrs.service.ClientRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clientRegistrationService")
public class ClientRegistrationServiceImpl implements ClientRegistrationService {

    @Autowired
    private ClientRegistrationDataService clientRegistrationDataService;

    @Autowired
    CampaignService campaignService;


    @Override
    public void add(ClientRegistration clientRegistration) {

        // Check for existence of NYVRS campaigns
        campaignService.handleNyvrsCampaignsInDb();
        ClientRegistration savedClientRegistration = clientRegistrationDataService.create(clientRegistration);
        campaignService.enrollToNyvrsCampaign(
                savedClientRegistration.getId().toString(), savedClientRegistration.getChannel());
    }



    @Override
    public ClientRegistration findClientRegistrationByNumber(String number) {
        return clientRegistrationDataService.findClientRegistrationByNumber(number);
    }

    @Override
    public List<ClientRegistration> getAll() {
        return clientRegistrationDataService.retrieveAll();
    }

    @Override
    public void delete(ClientRegistration clientRegistration) {
        clientRegistrationDataService.delete(clientRegistration);
    }

    @Override
    public void update(ClientRegistration clientRegistration) {
        clientRegistrationDataService.update(clientRegistration);
    }

    @Override
    public ClientRegistration getById(Long clientRegistrationId) {
        return clientRegistrationDataService.findClientRegistrationById(clientRegistrationId);
    }
}
