package com.test.repository;

import com.test.model.UniversityType;

public interface UniversityRepository {
    void save(String name, String street, UniversityType type);
}
