package com.viplearner.url_shortener.resource.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetShortUrlResponse {
    private final String status;
    private final String url;

    public GetShortUrlResponse(String status, String url) {
        // Jackson deserialization
        this.status = status;
        this.url = url;
    }

    @JsonProperty
    public String getUrl() {
        return url;
    }

    @JsonProperty
    public String getStatus() {
        return status;
    }
}
