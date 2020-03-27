package org.easymis.easyicc.card.readdata.provider;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "easyicc-card-distribution", fallback = DistributionCardProvider.DistributionCardFallback.class)
public interface DistributionCardProvider {
    /**
     * 分配名片
     * @param cardList
     * @throws Exception
     */
    @PostMapping(value = "/distribution/allocationCard")
	RestResult allocationCard(List<Card> cardList, String serveName);


    @Component
    class DistributionCardFallback implements DistributionCardProvider {
        /**
         * 降级统一返回无权限
         *
         * @param authentication
         * @param url
         * @param method
         * @return <pre>
         * Result:
         * {
         *   code:"-1"
         *   mesg:"系统异常"
         * }
         * </pre>
         */
		@Override
		public RestResult allocationCard(List<Card> cardList, String serveName) {
			 return RestResult.buildError();
		}
    	}
}
