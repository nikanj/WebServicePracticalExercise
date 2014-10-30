/**
 * 
 */
package de.tum.in.dss.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import de.tum.in.dss.constant.CommonConstants;
import de.tum.in.dss.model.GuestBookEntries;

public class GuestBookUploadValidator implements Validator {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return GuestBookEntries.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		GuestBookEntries entries = (GuestBookEntries) target;

		if (entries.getSelectedGuestBookIds() != null
				&& entries.getSelectedGuestBookIds().length == 0) {
			errors.rejectValue(CommonConstants.FIELD_NAME_SELECTED_IDS,
					"required.select",
					CommonConstants.ERROR_MSG_FIELD_SIDE_EMPTY);
		}

	}

}
