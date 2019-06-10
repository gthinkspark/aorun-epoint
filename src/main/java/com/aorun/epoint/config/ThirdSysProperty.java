package com.aorun.epoint.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "othersysproperty")
public class ThirdSysProperty {

    private String unionbaseurl;

    public String getUnionbaseurl() {
        return unionbaseurl;
    }

    public void setUnionbaseurl(String unionbaseurl) {
        this.unionbaseurl = unionbaseurl;
    }


}