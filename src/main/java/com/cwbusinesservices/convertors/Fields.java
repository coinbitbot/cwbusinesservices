package com.cwbusinesservices.convertors;

/**
 * Created by oleh_kurpiak on 07.09.2016.
 */
public class Fields {


    public static class User {
        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String LAST_NAME = "last_name";
        public static final String FIRST_NAME = "first_name";
        public static final String PASSWORD = "password";
        public static final String ROLE = "role";
        public static final String ACTIVE = "active";
        public static final String PHONE = "phone";

        public static final String DEFAULT = ID + ',' + EMAIL;
    }

    public static class InfoPage {
        public static final String ID = "id";
        public static final String URL = "url";
        public static final String HEADER = "header";
        public static final String SUB_HEADER = "sub_header";
        public static final String TEXT = "text";
        public static final String ACTIVE = "active";
        public static final String META_TITLE = "meta_title";
        public static final String META_DESCRIPTION = "meta_description";
        public static final String META_KEYWORDS = "meta_keywords";

        public static final String DEFAULT = ID + ',' + URL;
    }

    public static class Company{
        public static final String ID = "id";
        public static final String NAME = "NAME";
        public static final String HAS_LOGO = "has_logo";
        public static final String TEXT = "text";
        public static final String ACTIVE ="active";

        public static final String DEFAULT = ID + ',' + NAME;
    }

    public static class Service{
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String HAS_IMG = "has_icon";
        public static final String DESCRIPTION = "description";
        public static final String ACTIVE ="active";

        public static final String DEFAULT = ID + ',' + NAME;
    }

    public static class Testimonial{
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String TEXT = "text";
        public static final String POSITION = "position";
        public static final String ACTIVE ="active";

        public static final String DEFAULT = ID + ',' + NAME;
    }

    public static final String DEFAULT ="id";
}
