package xyz.ther.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "mail")
@PropertySource(value = {"classpath:myconfig.properties"},
        ignoreResourceNotFound = false, encoding = "UTF-8")
public class MyConfig {

    private String username;

    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "MyConfig{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

