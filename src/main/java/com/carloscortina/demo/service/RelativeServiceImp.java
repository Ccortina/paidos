package com.carloscortina.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloscortina.demo.dao.RelativeDao;
import com.carloscortina.demo.model.Relative;

@Service
@Transactional
public class RelativeServiceImp implements RelativeService {

    @Autowired
    RelativeDao relativeDao;

    @Override
    public void createRelative(Relative relative) {
            // TODO Auto-generated method stub
            relativeDao.createRelative(relative);

    }

    @Override
    public Relative getRelative(int id) {
            // TODO Auto-generated method stub
            return relativeDao.getRelative(id);
    }

    @Override
    public List<Relative> getAllRelatives() {
            // TODO Auto-generated method stub
            return relativeDao.getAllRelatives();
    }

    @Override
    public void updateRelative(Relative item){
        relativeDao.updateRelative(item);
    }

    @Override
    public List<Relative> getRelativeByRelationship(int idRelationship) {
        return relativeDao.getRelativeByRelationship(idRelationship);
    }

}
