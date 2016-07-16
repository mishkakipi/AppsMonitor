package com.afeka.android.appsmonitor.data;

import java.util.List;

/**
 * Created by Michael on 16/07/2016.
 */
public class Child {

    private String id;
    private String name;
    private String email;
    private List<App> applications;

    public Child() {

    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<App> getApplications() {
        return applications;
    }

    private Child(childBuilder builder) {
        this.id = childBuilder.id;
        this.name = childBuilder.name;
        this.email = childBuilder.email;
        this.applications = childBuilder.applications;
    }

    public static childBuilder getBuilder() {
        return new childBuilder();
    }

    public void update(String name, String email, List<App> applications) {
        this.name = name;
        this.email = email;
        this.applications = applications;
    }
    public static class childBuilder {

        private static String id;
        private static String name;
        private static String email;
        private static List<App> applications;

        public childBuilder id(String id) {
            childBuilder.id = id;
            return this;
        }
        public childBuilder name(String name) {
            childBuilder.name = name;
            return this;
        }
        public childBuilder email(String email) {
            childBuilder.email = email;
            return this;
        }
        public childBuilder applications(List<App> applications) {
            childBuilder.applications = applications;
            return this;
        }

        public Child build() {
            Child newChild = new Child(this);
            return newChild;
        }
    }
}
