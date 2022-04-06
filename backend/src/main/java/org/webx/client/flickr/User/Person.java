package org.webx.client.flickr.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    @JsonProperty("username")
    private Field username;

    @JsonProperty("realname")
    private Field realname;

    public Field getUsername() {
        return username;
    }

    public void setUsername(Field username) {
        this.username = username;
    }

    public Field getRealname() {
        return realname;
    }

    public void setRealname(Field realname) {
        this.realname = realname;
    }

}
