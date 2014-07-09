package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Treatment;
import java.util.List;

public interface TreatmentService extends GenericService<Treatment>{

    public List<Treatment> getTreatmentByCIE10AndUser(int diagnoticId,int userId);
    public List<Treatment> getTreatmentByUser(int userId);
}
