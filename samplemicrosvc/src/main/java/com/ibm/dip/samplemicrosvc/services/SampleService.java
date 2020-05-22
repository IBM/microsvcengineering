package com.ibm.dip.samplemicrosvc.services;

import com.ibm.dip.microsvcengineering.framework.monitoring.MonitoredService;
import com.ibm.dip.samplemicrosvc.dao.SampleDAO;
import com.ibm.dip.samplemicrosvc.model.APIResponse;
import com.ibm.dip.samplemicrosvc.model.AddressInfo;
import com.ibm.dip.samplemicrosvc.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@MonitoredService
public class SampleService {
    private static final Logger logger = LoggerFactory.getLogger(SampleService.class);

    @Autowired
    SampleDAO dao;

    @Autowired
    RestTemplate template;

    @Value("${application.address.serviceURL}")
    String addressUrl;

    public UserInfo getUserInformation(String userId)
    {
            logger.debug("received userId: {}", userId);

            UserInfo userInfo = dao.findById(userId);

            logger.debug("Getting address details");

            ResponseEntity<APIResponse<AddressInfo>> response = template.exchange(addressUrl, HttpMethod.GET, null,
                    new ParameterizedTypeReference<APIResponse<AddressInfo>>() {
                    }, "123");

            logger.debug("received response code: {}", response.getStatusCode());

            userInfo.setAddress(response.getBody().getResponse());
            return userInfo;
    }
}
