package de.tum.in.dss.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import de.tum.in.dss.model.StringReverse;

public class StringReverseValidator implements Validator {
	@Override
	public boolean supports(Class arg0) {
		return StringReverse.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {

		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "text",
				"Field is empty !!", "Required to enter a field");

		StringReverse reverse = (StringReverse) arg0;

		if (!arg1.hasErrors()
				&& (reverse.getText().trim().length() < 3 || reverse.getText()
						.trim().length() > 10)) {
			arg1.rejectValue("text", null,
					"Length of input should be min 3 and max 10 characters");
		}

	}
}
