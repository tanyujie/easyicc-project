package org.easymis.easyicc.domain.vo;

import java.util.HashMap;
import java.util.Map;


public class CompanyTotal {

	private Map<String, Integer> allocations = new HashMap<String, Integer>();
	
	private Map<String, Integer> weights = new HashMap<String, Integer>();
	
	
	public synchronized void addWeight(String bussinessGroupId, int weight){
		if(this.weights.containsKey(bussinessGroupId)){
			this.weights.put(bussinessGroupId, this.weights.get(bussinessGroupId) + weight);
		}else{
			this.weights.put(bussinessGroupId, weight);
		}
	}
	
	public void addAllocation(String bussinessGroupId, int count){
		if(this.allocations.containsKey(bussinessGroupId)){
			this.allocations.put(bussinessGroupId, this.allocations.get(bussinessGroupId) + count);
		}else{
			this.allocations.put(bussinessGroupId, count);
		}
	}
	
	public int getTotalWeight(String bussinessGroupId){
		if(this.weights.containsKey(bussinessGroupId)){
			return this.weights.get(bussinessGroupId);
		}else{
			return 0;
		}
	}
	
	public int getAllocationTotal(String bussinessGroupId){
		if(this.allocations.containsKey(bussinessGroupId)){
			return this.allocations.get(bussinessGroupId);
		}else{
			return 0;
		}
	}
	
}
