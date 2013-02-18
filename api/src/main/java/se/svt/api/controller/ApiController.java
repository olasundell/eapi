package se.svt.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * TODO write comment
 */
@Controller
public class ApiController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public final ModelAndView displayPage() {
		return new ModelAndView();
	}
}
