package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Treatment;
import java.util.List;

public interface TreatmentDao extends GenericDao<Treatment>{

    public List<Treatment> getTreatmentByCIE10AndUser(int diagnoticId,int userId);
    public List<Treatment> getTreatmentByUser(int userId);
}
