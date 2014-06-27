package com.carloscortina.demo.service;

import java.util.List;

import com.carloscortina.demo.model.Staffmember;

public interface StaffMemberService {

	public void createStaffmember(Staffmember staff);
	public Staffmember getStaffmember(int id);
	public List<Staffmember> getStaffmembers();
}
