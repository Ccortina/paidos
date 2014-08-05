package com.carloscortina.demo.service;

import com.carloscortina.demo.model.Cie10;
import java.util.List;

public interface Cie10Service extends GenericService<Cie10> {
    
    public List<Cie10> getCie10ByUser(int id);
    public List<Cie10> getAvaibleCie10ByUser(int idUser);
    public List<Cie10> getCie10ByTreatmentAndUser(int idUser,int idTreatment);
}
