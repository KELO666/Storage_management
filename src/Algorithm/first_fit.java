package Algorithm;

import java.util.ArrayList;
import java.util.List;

import entity.job;
import entity.memory;

/**
 * 首次适应算法
 * @author kelo
 *
 */
public class first_fit {
	public static void FF(job j,List<memory> list,int time){
		int m_size ;//分区的大小
		int u_size = j.getNeedMemory();//作业所需内存
		List<Integer> list_i = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			list_i.add(list.get(i).getStatus());
		}
		if(list_i.contains(0)){//存在分区可以分配
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getStatus() == 0){//只读取空闲的
					m_size = list.get(i).getAreaSize();
					if(m_size > u_size){
						if((m_size-u_size)<=30){
							list.get(i).setStatus(1);//整个分区分配出去
							list.get(i).setJobname(j.getName());//设置被分配到的作业名
							j.setEnterTime(time);//设置开始运行时间
							j.setStatus("Run");
							
						}else{
							list.get(i).setAreaSize(u_size);//整个分区切割后分配出去
							list.get(i).setStatus(1);
							list.get(i).setJobname(j.getName());//设置被分配到的作业名
							j.setEnterTime(time);//设置开始运行时间
							j.setStatus("Run");
							
							memory m = new memory();//剩下的分区成为独立一个
							m.setAreaNo(i+1);
							m.setAreaSize(m_size-u_size);
							m.setAreaStrat(list.get(i).getAreaStrat()+u_size);
							m.setStatus(0);
							m.setJobname("没有");
							list.add(i+1, m);//插入
						}
						break;
					}
				}
				
			}
		}		
	}
	
	
}
