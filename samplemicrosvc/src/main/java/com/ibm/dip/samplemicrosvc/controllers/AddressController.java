package com.ibm.dip.samplemicrosvc.controllers;

import com.ibm.dip.samplemicrosvc.integration.AuditHelper;
import com.ibm.dip.samplemicrosvc.model.APIResponse;
import com.ibm.dip.samplemicrosvc.model.AddressInfo;
import com.ibm.dip.samplemicrosvc.model.AuditEntry;
import com.ibm.dip.samplemicrosvc.services.AddressService;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {
    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    AddressService addressService;

    @Autowired
    AuditHelper auditHelper;

    @RequestMapping(method = RequestMethod.GET, value = "/addressDetails/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
//    @Timed(extraTags = {"componentClass", "AddressController", "methodName", "getAddressDetails", "componentType", "controller"})
    @Timed
    public @ResponseBody ResponseEntity<APIResponse<AddressInfo>> getAddressDetails(@PathVariable("addressId") String addressId)
    {
        logger.debug("Received userId: {}", addressId);
        AddressInfo addressInfo = addressService.getAddress(addressId);
        APIResponse<AddressInfo> savedCase = new APIResponse<>();
        savedCase.setResponse(addressInfo);
        savedCase.setStatus(APIResponse.STATUS_SUCCESS);
        auditHelper.logAuditEntry("tambre", "AddressInfo", AuditEntry.ACTION_READ);
        return ResponseEntity.ok(savedCase);
    }

}
