package de.tum.in.dss.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import de.tum.in.dss.model.GuestBookData;

public class GuestBookValidator implements Validator {
	private static String title = "data.title";
	private static String name = "data.name";
	private static String text = "data.text";
	private static String compulsory = "Data is required";
	private static String minlt = "Length of input should be min 3 and max 10 characters";
	private static String invalid = "Accepted characters are a-z,A-Z,0-9 and whitespaces";
	private static String valid = "^[a-zA-Z0-9\\s]+$";

	public boolean supports(Class arg0) {

		return GuestBookData.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {

		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, name, "required.name",
				compulsory);

		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, text, "required.text",
				compulsory);

		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, title,
				"required.title", compulsory);

		GuestBookData guestBookData = (GuestBookData) arg0;

		if (guestBookData != null && guestBookData.getdata() != null) {

			if (guestBookData.getdata().getText() != null
					&& (guestBookData.getdata().getText().trim().length() < 3 || guestBookData
							.getdata().getText().trim().length() > 500)) {
				arg1.rejectValue(text, "error.text.length",
						"Length of input should be min 3 and max 500 characters");
			}

			if (guestBookData.getdata().getName() != null
					&& (guestBookData.getdata().getName().trim().length() < 10 || guestBookData
							.getdata().getName().trim().length() > 30)) {
				arg1.rejectValue(name, "error.name.length",
						"Length of input should be min 10 and max 30 characters");
			} else if (guestBookData.getdata().getName() != null
					&& !acceptedPatternMatch(guestBookData.getdata().getName())) {
				arg1.rejectValue(name, "error.invalid.characters", invalid);
			}

			if (guestBookData.getdata().getTitle() != null
					&& (guestBookData.getdata().getTitle().trim().length() < 5 || guestBookData
							.getdata().getTitle().trim().length() > 40)) {
				arg1.rejectValue(title, "error.title.length",
						"Length of input should be min 5 and max 40 characters");
			} else if (guestBookData.getdata().getTitle() != null
					&& !acceptedPatternMatch(guestBookData.getdata().getTitle())) {
				arg1.rejectValue(title, "error.invalid.characters", invalid);
			}
		}
	}

	private boolean acceptedPatternMatch(String input) {

		if (input != null) {
			Pattern pattern = Pattern.compile(valid);
			Matcher matcher = pattern.matcher(input);
			return matcher.matches();
		}
		return false;
	}
}
