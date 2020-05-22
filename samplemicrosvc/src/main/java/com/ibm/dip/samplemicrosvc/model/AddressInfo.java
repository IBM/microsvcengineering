package com.ibm.dip.samplemicrosvc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class AddressInfo implements Serializable {

    private String addressId;
    private String street;
    private String houseNumber;
    private String addressLine1;
    private String zip;

    public AddressInfo()
    {

    }

    public AddressInfo(String addressId, String street, String houseNumber, String addressLine1, String zip) {
        this.addressId = addressId;
        this.street = street;
        this.houseNumber = houseNumber;
        this.addressLine1 = addressLine1;
        this.zip = zip;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressInfo that = (AddressInfo) o;
        return Objects.equals(addressId, that.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId);
    }

    @Override
    public String toString()
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            return mapper.writeValueAsString(this);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
