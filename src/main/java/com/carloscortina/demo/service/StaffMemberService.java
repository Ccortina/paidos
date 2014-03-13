package com.carloscortina.demo.service;

import java.util.List;

import com.carloscortina.demo.model.StaffMember;

public interface StaffMemberService {

	public void createStaffMember(StaffMember staff);
	public StaffMember getStaffMember(int id);
	public List<StaffMember> getStaffMembers();
}
