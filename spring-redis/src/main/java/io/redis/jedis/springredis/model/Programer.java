package io.redis.jedis.springredis.model;

import java.io.Serializable;
import java.util.Objects;

public class Programer implements Serializable {
    private int id;
    private String company;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programer programer = (Programer) o;
        return id == programer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
