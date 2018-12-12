package entity;
/**
 * 
 * @author kelo
 *
 */
public class memory {
	int areaNo;//分区号
	int areaSize;//分区大小
	int areaStrat;//分区开始地址
	int status;//状态 0-空闲 1-已分配
	String jobname;//被分配到的作业
	public int getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(int areaNo) {
		this.areaNo = areaNo;
	}
	public int getAreaSize() {
		return areaSize;
	}
	public void setAreaSize(int areaSize) {
		this.areaSize = areaSize;
	}
	public int getAreaStrat() {
		return areaStrat;
	}
	public void setAreaStrat(int areaStrat) {
		this.areaStrat = areaStrat;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	@Override
	public String toString() {
		return "memory [areaNo=" + areaNo + ", areaSize=" + areaSize + ", areaStrat=" + areaStrat + ", status=" + status
				+ ", jobname=" + jobname + "]";
	}
	
	
	
}
