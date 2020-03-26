package org.easymis.easyicc.web.card.distribution.provider;

import java.util.List;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name = "authentication-server", fallback = PushCardProvider.PushCardFallback.class)
public interface PushCardProvider {
    /**
     * 推送名片
     * @param cardList
     * @throws Exception
     */
	RestResult pushCard(List<Card> cardList, String serveName);


    @Component
    class PushCardFallback implements PushCardProvider {
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
		public RestResult pushCard(List<Card> cardList, String serveName) {
			 return RestResult.buildError();
		}
    	}
}
