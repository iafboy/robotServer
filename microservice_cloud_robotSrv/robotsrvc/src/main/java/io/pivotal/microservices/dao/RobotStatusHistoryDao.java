package io.pivotal.microservices.dao;

import io.pivotal.microservices.model.StatusMessage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Mapper
public interface RobotStatusHistoryDao {

	@Insert("Insert into T_ROBOTSTATUSHISOTRY(taskid,distance,angle,lan,lat,lpwm,rpwm,stopped,detail) values=(#{taskid},#{distance},#{angle},#{lan},#{lat},#{lpwm},#{rpwm},#{stopped},#{detail})")
	public boolean insertRobotStatusHistory(StatusMessage sm);

}
