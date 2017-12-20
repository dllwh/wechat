package com.cdeledu.core.session.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;

import com.cdeledu.common.base.BaseClass;
import com.cdeledu.common.constants.CacheConstans;
import com.cdeledu.core.session.ShiroSessionRepository;

public class ShiroSessionRepositoryImpl extends BaseClass implements ShiroSessionRepository {
	/** ----------------------------------------------------- Fields start */
	private static final long serialVersionUID = 1L;

	/** ----------------------------------------------------- Fields end */
	@Override
	public void saveSession(Session session) {
		getKey(session.getId());
	}

	@Override
	public void deleteSession(Serializable sessionId) {
		getKey(sessionId);
	}

	@Override
	public Session getSession(Serializable sessionId) {
		getKey(sessionId);
		return null;
	}

	@Override
	public Collection<Session> getAllSessions() {
		Set<Session> sessions = new HashSet<Session>();

		return sessions;
	}

	private String getKey(Serializable sessionId) {
		return CacheConstans.redis_shiro_session + sessionId;
	}
}
