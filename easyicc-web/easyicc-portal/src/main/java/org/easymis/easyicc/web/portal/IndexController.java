package org.easymis.easyicc.web.portal;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/index.html")
	public String blank() throws NoSuchAlgorithmException {
		return "/index";
	}
}
