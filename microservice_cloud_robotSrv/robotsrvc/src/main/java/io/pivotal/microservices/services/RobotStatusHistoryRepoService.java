package io.pivotal.microservices.services;

import io.pivotal.microservices.dao.RobotStatusHistoryDao;
import io.pivotal.microservices.model.StatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service(value="RobotStatusHistoryRepoService")
@Transactional
public class RobotStatusHistoryRepoService {
	@Autowired
	protected RobotStatusHistoryDao robotStatusHistoryDao;

	public boolean insertHistory(StatusMessage sm) throws Exception {
		boolean result=false;
		if(robotStatusHistoryDao.insertRobotStatusHistory(sm)){
			result=true;
		}
		return result;
	}
}
