package org.easymis.easyicc.service;

import org.easymis.easyicc.domain.entity.CardInterface;

public interface CardInterfaceService {
	public void save(CardInterface bean);
	public CardInterface findById(String id);
}
