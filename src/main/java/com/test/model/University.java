package com.test.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "selectBooks",query ="SELECT u FROM University u")
})
@Table(name = "university")
public class University {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(name = "name")
    private String name;

//    @Column(name = "street")
    private String street;

    @Column(name = "university_type")
    @Enumerated(EnumType.STRING)
    UniversityType universityType;

    public University(){}

    public University(String name, String street) {
        this.name = name;
        this.street = street;
    }

    public University(String name, String street, UniversityType universityType) {
        this.name = name;
        this.street = street;
        this.universityType = universityType;
    }

    public University(int id, String name, String street) {
        this.id = id;
        this.name = name;
        this.street = street;
    }

    public UniversityType getUniversityType() {
        return universityType;
    }

    public void setUniversityType(UniversityType universityType) {
        this.universityType = universityType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(street, that.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, street);
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
