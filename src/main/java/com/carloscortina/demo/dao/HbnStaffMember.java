package com.carloscortina.demo.dao;

import com.carloscortina.demo.model.Staffmember;
import org.springframework.stereotype.Repository;

@Repository
public class HbnStaffMember extends GenericHbnDao<Staffmember> implements StaffMemberDao {
  
}
