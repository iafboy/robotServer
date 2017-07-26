package io.pivotal.microservices.controller;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.alibaba.fastjson.JSON;
import io.pivotal.microservices.model.Barycenter;
import io.pivotal.microservices.model.Coordinate;
import io.pivotal.microservices.model.RobotCommand;
import io.pivotal.microservices.model.StatusMessage;
import io.pivotal.microservices.services.GisMapTransService;
import io.pivotal.microservices.services.RobotStatusHistoryRepoService;
import io.pivotal.microservices.utils.BarycenterComparator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;


@RestController
public class RobotSrvcController {

	protected Logger logger = Logger.getLogger(RobotSrvcController.class
			.getName());

	@Resource(name="RobotStatusHistoryRepoService")
	protected RobotStatusHistoryRepoService rshService;

	@Resource(name="GisMapTransService")
	protected GisMapTransService gisMapTransService;

	@RequestMapping(value="/robotServiceTask",method = RequestMethod.GET,headers="Accept=application/json",produces="application/json;charset=UTF-8")
	public RobotCommand getTask(){
		RobotCommand returnValue=null;
		//to be done
		returnValue=new RobotCommand();
		returnValue.setActionType(1);
		returnValue.setAngle(30.0);
		returnValue.setDelay(300);
		returnValue.setTaskId("A1");
		return returnValue;
	}

	// {"rpwm":500,"stopped":0,"distance":3000,"lng":23.32,"lpwm":500,"angle":30.3,"detail":"running","lat":33.13,"taskid":"A1"}

	@RequestMapping(value="/robotServiceTask",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public void uploadTaskStatus( @RequestBody Map map) {
		logger.info("get message["+map.toString()+"]");
		StatusMessage sm= convertSM(map);
		logger.info("save message["+map.toString()+"] to object "+sm);
		if(sm!=null)
		try {
			rshService.insertHistory(sm);
		} catch (Exception e) {
			logger.warning(e.getMessage());
		}
	}

	private StatusMessage convertSM(Map map) {
		StatusMessage value=null;
		if(map!=null&&map.containsKey("taskid")){
			value=new StatusMessage();
			value.setTaskid((String)map.get("taskid"));
			value.setStopped(Integer.parseInt((String)map.get("stopped")));
			value.setRpwm(Integer.parseInt((String)map.get("rpwm")));
			value.setLpwm(Integer.parseInt((String)map.get("lpwm")));
			value.setLng(Double.parseDouble((String)map.get("lng")));
			value.setLat(Double.parseDouble((String)map.get("lng")));
			value.setAngle(Double.parseDouble((String)map.get("angle")));
			value.setDetail((String)map.get("detail"));
			value.setDistance(Long.parseLong((String)map.get("distance")));
		}
		return value;
	}

	//通过三角质心算法计算位置
	private Coordinate getLocation(Barycenter[] barycenters){
		if(barycenters!=null&&barycenters.length>3) {
			Arrays.sort(barycenters, new BarycenterComparator());
			return gisMapTransService.trilateration(barycenters[0],barycenters[1],barycenters[2]);
		}
		return null;
	}

}
