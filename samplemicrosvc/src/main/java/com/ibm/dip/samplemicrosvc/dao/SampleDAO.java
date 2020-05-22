package com.ibm.dip.samplemicrosvc.dao;

import com.ibm.dip.microsvcengineering.framework.monitoring.MonitoredDAO;
import com.ibm.dip.samplemicrosvc.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@MonitoredDAO
public class SampleDAO {
    private static final Logger logger = LoggerFactory.getLogger(SampleDAO.class);

    public UserInfo findById(String userId)
    {
        logger.debug("received userId: {}", userId);
        return new UserInfo(userId, "T", "A", "1977-11-11", null);
    }

}
