package com.ibm.dip.samplemicrosvc.services;

import com.ibm.dip.microsvcengineering.framework.monitoring.MonitoredService;
import com.ibm.dip.samplemicrosvc.model.AddressInfo;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@MonitoredService
public class AddressService {
    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

    public AddressInfo getAddress(String addressId)
    {
        int i = RandomUtils.nextInt();

        if (i%3 == 0)
        {
            throw new IllegalStateException("Simulated Error");
        }
        else if (i%7 == 0)
        {
            throw new IllegalArgumentException("Simulated: Specified addressId is not correct");
        }


        logger.debug("received addressId: {}", addressId);
        return new AddressInfo(addressId, "J.M. Road", "B-103", "Deccan", "411001");
    }


}
