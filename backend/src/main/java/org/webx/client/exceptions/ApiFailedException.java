package org.webx.client.exceptions;

public class ApiFailedException extends Exception {
    private final String apiName;
    private final String apiStatus;

    public ApiFailedException(String apiName, String apiStatus) {
        super(
            String.format("Api '%s' failed with status: %s", apiName, apiStatus)
        );

        this.apiName = apiName;
        this.apiStatus = apiStatus;
    }

    public String getApiName() {
        return apiName;
    }

    public String getApiStatus() {
        return apiStatus;
    }
}
