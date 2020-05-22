package com.ibm.dip.samplemicrosvc.controllers;

import com.ibm.dip.samplemicrosvc.integration.AuditHelper;
import com.ibm.dip.samplemicrosvc.model.APIResponse;
import com.ibm.dip.samplemicrosvc.model.AuditEntry;
import com.ibm.dip.samplemicrosvc.model.UserInfo;
import com.ibm.dip.samplemicrosvc.services.SampleService;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Autowired
    SampleService service;

    @Autowired
    AuditHelper auditHelper;

    @RequestMapping(method = RequestMethod.GET, value = "/userInfo/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
//    @Timed(extraTags = {"componentClass", "SampleController", "methodName", "getUserInfo", "componentType", "controller"})
    @Timed
    public @ResponseBody ResponseEntity<APIResponse<UserInfo>> getUserInfo(@PathVariable("userId") String userId)
    {
        logger.debug("Received userId: {}", userId);
        UserInfo userInfo = service.getUserInformation(userId);
        APIResponse<UserInfo> savedCase = new APIResponse<>();
        savedCase.setResponse(userInfo);
        savedCase.setStatus(APIResponse.STATUS_SUCCESS);
        auditHelper.logAuditEntry(userId, "UserInfo", AuditEntry.ACTION_READ);
        return ResponseEntity.ok(savedCase);
    }
}
