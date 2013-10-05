package com.carloscortina.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloscortina.demo.dao.StaffMemberDao;
import com.carloscortina.demo.model.StaffMember;

@Service
@Transactional
public class StaffMemberServiceImp implements StaffMemberService {

	@Autowired
	private StaffMemberDao staffMemberDao;
	
	@Override
	public void createStaffMember(StaffMember staff) {
		// TODO Auto-generated method stub
		staffMemberDao.createStaffMember(staff);
	}

	@Override
	public StaffMember getStaffMember(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StaffMember> getStaffMembers() {
		// TODO Auto-generated method stub
		return null;
	}

}
