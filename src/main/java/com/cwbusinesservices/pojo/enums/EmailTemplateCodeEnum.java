package com.cwbusinesservices.pojo.enums;

import java.util.*;

/**
 * Created by Andrii on 31.07.2017.
 */
public enum EmailTemplateCodeEnum {
    NEW_USER_REGISTER(Arrays.asList(
            new HashMap<String, String>(){{
                put("value", "url");
                put("label", "Url to set password page");
            }},
            new HashMap<String, String>(){{
                put("value", "NAME");
                put("label", "User name");
            }}
    )),
    NEW_REQUEST_IN_SYSTEM(new ArrayList<>()),
    EMAIL_SUBSCRIPTION(Arrays.asList(
            new HashMap<String, String>(){{
                put("value", "name");
                put("label", "Subscriber name");
            }}
    )),
    NEW_REQUEST_TO_USER(Arrays.asList(
            new HashMap<String, String>(){{
                put("value", "NAME");
                put("label", "User name");
            }},
            new HashMap<String, String>(){{
                put("value", "LINK");
                put("label", "Link to user profile");
            }}
    )),
    EMAIL_FROM_CONTACT_PAGE(Arrays.asList(
            new HashMap<String, String>(){{
                put("value", "USER_NAME");
                put("label", "User name");
            }},
            new HashMap<String, String>(){{
                put("value", "EMAIL");
                put("label", "User email");
            }},
            new HashMap<String, String>(){{
                put("value", "TEXT");
                put("label", "Message text");
            }}
    )),
    RECOVER_PASSWORD(Arrays.asList(
            new HashMap<String, String>(){{
                put("value", "NAME");
                put("label", "User name");
            }},
            new HashMap<String, String>(){{
                put("value", "PASSWORD");
                put("label", "New password");
            }}
    ));

    private List<Map<String, String>> emailFields;

    EmailTemplateCodeEnum(List<Map<String, String>> emailFields) {
        this.emailFields = emailFields;
    }

    public List<Map<String, String>> getEmailFields() {
        return emailFields;
    }
}
