package myutils;

import java.util.LinkedList;
import java.util.Queue;

public class IdGen{
	LinkedList<Integer> list=new LinkedList<Integer>();
	private Queue<Integer> ids=list;
	
	public IdGen(int numIds){
		for(int i=0;i<numIds;i++){
			ids.add(i);
		}
	}
	public int getId(){
		return ids.remove();
		
	}
	public void retId(int id){					//insert in end of queue number
		ids.offer(id);
	}
	public Boolean conf(int id){
		for(int i=0;i<list.size();i++){
			if(id==list.get(i)){
				list.remove(i);
				return true;
				}
		}
		return false;
	}
	public void show(){
		System.out.println(list);
	}
}
