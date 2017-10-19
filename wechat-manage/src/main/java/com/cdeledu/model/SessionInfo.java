package com.cdeledu.model;

import java.io.Serializable;

import com.cdeledu.model.rbac.SysUser;

public class SessionInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private SysUser managerUser;

	public SysUser getManagerUser() {
		return managerUser;
	}

	public void setManagerUser(SysUser managerUser) {
		this.managerUser = managerUser;
	}

}
