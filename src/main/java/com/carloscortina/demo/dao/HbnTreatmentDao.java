package com.carloscortina.demo.dao;

import org.springframework.stereotype.Repository;

import com.carloscortina.demo.model.Treatment;

@Repository
public class HbnTreatmentDao extends GenericHbnDao<Treatment> implements TreatmentDao {

}
