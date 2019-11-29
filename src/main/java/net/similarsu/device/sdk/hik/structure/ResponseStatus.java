package net.similarsu.device.sdk.hik.structure;

/**
 * @author whh
 * @date 2019/11/28
 */
public class ResponseStatus {
    private String requestURL;
    private int statusCode;
    private String statusString;
    private String subStatusCode;
    private String ID;

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public String getSubStatusCode() {
        return subStatusCode;
    }

    public void setSubStatusCode(String subStatusCode) {
        this.subStatusCode = subStatusCode;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
