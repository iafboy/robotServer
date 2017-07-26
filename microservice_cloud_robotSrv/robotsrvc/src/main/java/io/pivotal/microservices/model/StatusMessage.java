package io.pivotal.microservices.model;

import java.io.Serializable;
public class StatusMessage implements Serializable{
	private String taskid;
	private long distance;
	private double angle;
	private double lng;
	private double lat;
	private int lpwm;
	private int rpwm;
	private int stopped;
	private String detail;

	public int getStopped() {
		return stopped;
	}

	public void setStopped(int stopped) {
		this.stopped = stopped;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public int getLpwm() {
		return lpwm;
	}

	public void setLpwm(int lpwm) {
		this.lpwm = lpwm;
	}

	public int getRpwm() {
		return rpwm;
	}

	public void setRpwm(int rpwm) {
		this.rpwm = rpwm;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
