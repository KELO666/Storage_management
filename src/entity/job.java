package entity;
/**
 * 
 * @author kelo
 *
 */
public class job {
	String name;
	int needMemory;//需要内存
	int needTime;//运行时间
	int enterTime;//开始执行时间
	int serverTime;//服务时间
	String status;//状态  
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNeedMemory() {
		return needMemory;
	}
	public void setNeedMemory(int needMemory) {
		this.needMemory = needMemory;
	}
	public int getNeedTime() {
		return needTime;
	}
	public void setNeedTime(int needTime) {
		this.needTime = needTime;
	}
	public int getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(int enterTime) {
		this.enterTime = enterTime;
	}
	public int getServerTime() {
		return serverTime;
	}
	public void setServerTime(int serverTime) {
		this.serverTime = serverTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "job [name=" + name + ", needMemory=" + needMemory + ", needTime=" + needTime + ", enterTime="
				+ enterTime + ", serverTime=" + serverTime + ", status=" + status + "]";
	}
	
	
	
}
