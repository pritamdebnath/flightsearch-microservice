package micromakers.com.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {

    @JsonProperty("username")
    String username;
    @JsonProperty("password")
    String password;
    @JsonProperty("grant_type")
    String grantType;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
        this.grantType = "password";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
