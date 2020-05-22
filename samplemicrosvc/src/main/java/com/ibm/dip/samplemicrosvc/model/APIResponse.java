package com.ibm.dip.samplemicrosvc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class APIResponse<T>
{

    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_ERROR = "ERROR";

    private String status;
    private String errorMessage;
    private Object errDetails;
    private T response;
    private String version;

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errMsg)
    {
        this.errorMessage = errMsg;
    }

    public Object getErrDetails()
    {
        return errDetails;
    }

    public void setErrDetails(Object errDetails)
    {
        this.errDetails = errDetails;
    }

    public T getResponse()
    {
        return response;
    }

    public void setResponse(T response)
    {
        this.response = response;
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