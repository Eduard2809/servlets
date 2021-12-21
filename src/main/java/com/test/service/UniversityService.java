package com.test.service;

import com.test.model.University;
import com.test.model.UniversityType;
import com.test.repository.StudentRepository;
import com.test.repository.StudentRepositoryHibernate;
import com.test.repository.UniversityRepository;
import com.test.repository.UniversityRepositoryHibernate;

public class UniversityService {

    private UniversityRepository universityRepository;

    public UniversityService() {
        universityRepository = new UniversityRepositoryHibernate();
    }

    public void save(String name, String street, UniversityType type){
        universityRepository.save(name, street, type);
    }
}
