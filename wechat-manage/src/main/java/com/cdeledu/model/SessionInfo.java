package com.cdeledu.model;

import java.io.Serializable;

import com.cdeledu.model.rbac.ManagerUser;

public class SessionInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private ManagerUser managerUser;

	public ManagerUser getManagerUser() {
		return managerUser;
	}

	public void setManagerUser(ManagerUser managerUser) {
		this.managerUser = managerUser;
	}

}
