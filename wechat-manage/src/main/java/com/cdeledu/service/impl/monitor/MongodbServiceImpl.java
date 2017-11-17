package com.cdeledu.service.impl.monitor;

import org.springframework.stereotype.Service;

import com.cdeledu.service.monitor.MongodbService;

@Service("mongodbService")
public class MongodbServiceImpl implements MongodbService {

	@Override
	public void getMongodbList() {
	}

	@Override
	public Integer countMongodb() {
		return null;
	}

	@Override
	public void getMongodb(Long id) {
	}

	@Override
	public void getMongodbOverview(Long id) {

	}

	@Override
	public void getMongodbListDatabases(Long id) {
	}
}
