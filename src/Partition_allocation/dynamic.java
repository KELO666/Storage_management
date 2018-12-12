package Partition_allocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Algorithm.first_fit;
import Algorithm.next_fit;
import entity.job;
import entity.memory;

/**
 * 动态分区分配
 * @author kelo
 *
 */
public class dynamic {
	public static void dDistribution(){
		int time = 0;
		int totalMemory = 600;//总空闲区大小
		int size = 30;//不能再切割的剩余分区大小
		System.out.println("总空闲区大小为："+totalMemory);
		System.out.println("不能再切割的剩余分区大小："+size);
		List<job> list_j = new ArrayList<job>();
		List<memory> list_m = new ArrayList<memory>();
		int num = 0;
		init(list_j,list_m);
		System.out.println("*******************************************");
		System.out.println("请选择你的分配算法：");
		System.out.println("1、首次适应算法");
		System.out.println("2、循环首次适应算法");	
		Scanner input = new Scanner(System.in);
		int k = input.nextInt();
		while(num != 5){
			dD(list_j,list_m,time,k);
			for (int i = 0; i < list_j.size(); i++) {
				if(list_j.get(i).getStatus().equals("Run")) {
					list_j.get(i).setServerTime(time-list_j.get(i).getEnterTime());
				}				
				if((time-list_j.get(i).getEnterTime()) == list_j.get(i).getNeedTime()){
					list_j.get(i).setStatus("Finish");			
				}
			}
			num = num(list_j);
			recovery(list_j,list_m);
			System.out.println("********************************************************************************************");
			System.out.println("当time="+time+"的时候：");
			System.out.println("作业队列为：");
			print_j(list_j);
			System.out.println("各个分区为：");
			print_m(list_m);
			time++;
		}
		
		
	}
	
	/**
	 * 输出分区
	 * @param list
	 */
	public static void print_m(List<memory> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
	
	/**
	 * 输出作业
	 * @param list
	 */
	public static void print_j(List<job> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
	
	/**
	 * 判断完成的作业数量
	 * @param list
	 */
	public static int num(List<job> list){
		int num = 0;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getStatus().equals("Finish")){
				num++;
			}
		}
		return num;
		
	}
	
//	/**
//	 * 作业执行
//	 * @param list
//	 * @param nowtime
//	 */
//	public static void run(List<job> list,int nowtime){		
//		for (int i = 0; i < list.size(); i++) {		
//			if((nowtime-list.get(i).getEnterTime()) == list.get(i).getNeedTime()){
//				list.get(i).setStatus("Finish");			
//			}			
//		}				
//	}
	
	/**
	 * 回收内存
	 * @param list_j
	 * @param list_m
	 */
	public static void recovery(List<job> list_j,List<memory> list_m){
		for (int i = 0; i < list_j.size(); i++) {
			if(list_j.get(i).getStatus().equals("Finish")){//作业执行完成
				for (int j = 0; j < list_m.size(); j++) {
					//System.out.println(j);
					if(list_m.get(j).getJobname().equals(list_j.get(i).getName())){//找到该作业的分区
						list_m.get(j).setStatus(0);
						list_m.get(j).setJobname("没有");
						Merge(list_m,list_m.get(j),j);//合并
					}
				}
			}
		}
	}
	
	/**
	 * 释放内存合并分区
	 * @param list_m
	 * @param m
	 * @param j m在list_m的位置
	 */
	public static void Merge(List<memory> list_m,memory m,int j){
		int b_size = 0;
		int size = list_m.get(j).getAreaSize();
		int a_size = 0;
		while(true){
			if(j==0) {//第一个分区
				if(list_m.get(j+1).getStatus()==0) {
					a_size = list_m.get(j+1).getAreaStrat();
					list_m.get(j).setAreaSize(size+a_size);
					list_m.remove(list_m.get(j+1));
					break;
				}
				break;
			}
			if(j==4) {//最后一个分区
				if(list_m.get(j-1).getStatus()==0) {
					b_size = list_m.get(j-1).getAreaSize();
					list_m.get(j-1).setAreaSize(b_size+size);
					list_m.remove(list_m.get(j));
					break;
				}
				break;
			}
			if(list_m.get(j-1).getStatus()==0&&list_m.get(j+1).getStatus()==0){//前后都是空闲
				b_size = list_m.get(j-1).getAreaSize();
				a_size = list_m.get(j+1).getAreaStrat();
				list_m.get(j-1).setAreaSize(b_size+size+a_size);
				list_m.remove(list_m.get(j));
				list_m.remove(list_m.get(j+1));
				break;
			}else if(list_m.get(j-1).getStatus()==0&&list_m.get(j+1).getStatus()!=0){//前一个分区是空闲的
				b_size = list_m.get(j-1).getAreaSize();
				list_m.get(j-1).setAreaSize(b_size+size);
				list_m.remove(list_m.get(j));
				break;
			}else if(list_m.get(j-1).getStatus()!=0&&list_m.get(j+1).getStatus()==0){//后一个分区是空闲的
				a_size = list_m.get(j+1).getAreaStrat();
				list_m.get(j).setAreaSize(size+a_size);
				list_m.remove(list_m.get(j+1));
				break;
			}else{//前后分区都不是空闲的
				break;
			}
		}
		
	}
	
	/**
	 * 分区分配
	 * @param list_j
	 * @param list_m
	 * @param time
	 */
	public static void dD(List<job> list_j,List<memory> list_m,int time,int k){
//		System.out.println("*******************************************");
//		System.out.println("请选择你的分配算法：");
//		System.out.println("1、首次适应算法");
//		System.out.println("2、循环首次适应算法");	
//		Scanner input = new Scanner(System.in);
//		int k = input.nextInt();
		int n = 0;
		if(k == 1){
			for (int i = 0; i < list_j.size(); i++) {
				if(list_j.get(i).getStatus().equals("Wait")) {
					first_fit.FF(list_j.get(i),list_m,time);
					for (int j = 0; j < list_m.size(); j++) {
						list_m.get(j).setAreaNo(j);
					}
				}
			}
		}else if(k == 2){
			for (int i = 0; i < list_j.size(); i++) {
				if(list_j.get(i).getStatus().equals("Wait")) {
					n = next_fit.NF(list_j.get(i),list_m,n,time);
				}
				
			}
		}else{
			System.out.println("你的输入有误！");
			dDistribution();//重新开始
		}
		System.out.println("*******************************************");
	}
	
	/**
	 * 初始化作业分区序列
	 * @param list_j
	 */
	public static void init(List<job> list_j,List<memory> list_m) {
		job j = new job();
		j.setName("job0");
		j.setNeedMemory(130);
		j.setServerTime(0);
		j.setNeedTime(40);
		j.setStatus("Wait");
		list_j.add(j);
		
		job j1 = new job();
		j1.setName("job1");
		j1.setNeedMemory(60);
		j1.setServerTime(0);
		j1.setNeedTime(15);
		j1.setStatus("Wait");
		list_j.add(j1);
		
		job j2 = new job();
		j2.setName("job2");
		j2.setNeedMemory(100);
		j2.setServerTime(0);
		j2.setNeedTime(15);
		j2.setStatus("Wait");
		list_j.add(j2);
		
		job j3 = new job();
		j3.setName("job3");
		j3.setNeedMemory(200);
		j3.setServerTime(0);
		j3.setNeedTime(60);
		j3.setStatus("Wait");
		list_j.add(j3);
		
		job j4 = new job();
		j4.setName("job4");
		j4.setNeedMemory(200);
		j4.setServerTime(0);
		j4.setNeedTime(20);
		j4.setStatus("Wait");
		list_j.add(j4);
		
		memory m = new memory();
		m.setAreaNo(0);
		m.setAreaSize(200);
		m.setAreaStrat(0);
		m.setStatus(0);
		m.setJobname("没有");
		list_m.add(m);
		
		memory m1 = new memory();
		m1.setAreaNo(1);
		m1.setAreaSize(100);
		m1.setAreaStrat(200);
		m1.setStatus(0);
		m1.setJobname("没有");
		list_m.add(m1);
		
		memory m2 = new memory();
		m2.setAreaNo(2);
		m2.setAreaSize(100);
		m2.setAreaStrat(300);
		m2.setStatus(0);
		m2.setJobname("没有");
		list_m.add(m2);
		
		memory m3 = new memory();
		m3.setAreaNo(3);
		m3.setAreaSize(150);
		m3.setAreaStrat(400);
		m3.setStatus(0);
		m3.setJobname("没有");
		list_m.add(m3);
		
		memory m4 = new memory();
		m4.setAreaNo(4);
		m4.setAreaSize(50);
		m4.setAreaStrat(550);
		m4.setStatus(0);
		m4.setJobname("没有");
		list_m.add(m4);
	}
}
