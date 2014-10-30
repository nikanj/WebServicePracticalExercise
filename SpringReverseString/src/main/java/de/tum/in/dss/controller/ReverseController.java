package de.tum.in.dss.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import de.tum.in.dss.model.StringReverse;

public class ReverseController extends SimpleFormController {
	public ReverseController() {
		setCommandClass(StringReverse.class);
		setCommandName("reverse");
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		StringReverse reverse = new StringReverse();
		reverse.setText("trialString");
		return reverse;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		StringReverse reverse = (StringReverse) command;

		if (reverse != null) {
			reverse.setReverseText(reverse.reverseString(reverse.getText()));
		}
		System.out.println(reverse);
		return new ModelAndView("reverseresult", "reverseobj", reverse);
	}

}
