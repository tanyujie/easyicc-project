package org.easymis.easyicc.domain.entity;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class CustomerReport {
	private String createUserId;
	
	private String realName;
	
	private int chatTotal;
	
	private int effectTotal;
	
	private int cardTotal;
	
	private int validTotal;
	
	private int backTotal;
	
	private double cardRatio;
	
	private double backRatio;
	
	public CustomerReport(String createUserId, String realName, int effectTotal, int chatTotal, int cardTotal, int validTotal, int backTotal){
		this.createUserId = createUserId;
		this.realName = realName;
		this.effectTotal = effectTotal;
		this.chatTotal = chatTotal;
		this.cardTotal = cardTotal;
		this.validTotal = validTotal;
		this.backTotal = backTotal;
		if(effectTotal > 0 && this.cardTotal >0){
			this.cardRatio = (new BigDecimal(this.cardTotal*100)).divide(new BigDecimal(this.effectTotal), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		if(this.cardTotal > 0 && this.backTotal > 0){
			this.backRatio = (new BigDecimal(this.backTotal*100)).divide(new BigDecimal(this.cardTotal), 2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
	}
}
