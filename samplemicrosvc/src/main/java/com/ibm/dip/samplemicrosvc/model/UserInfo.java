package com.ibm.dip.samplemicrosvc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class UserInfo implements Serializable {

    private String userId;
    private String firstName;
    private String lastName;
    private String dob;
    private AddressInfo address;

    public UserInfo(String userId, String firstName, String lastName, String dob, AddressInfo address) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAddress(AddressInfo address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(userId, userInfo.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
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
