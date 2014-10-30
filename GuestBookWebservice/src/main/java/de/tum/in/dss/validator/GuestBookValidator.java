package de.tum.in.dss.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import de.tum.in.dss.constant.CommonConstants;
import de.tum.in.dss.model.GuestBook;

public class GuestBookValidator implements Validator {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class arg0) {

		return GuestBook.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		
		
			
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, CommonConstants.FIELD_NAME_NAME,
				"required.name", CommonConstants.ERROR_MSG_FIELD_SIDE_EMPTY);

		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, CommonConstants.FIELD_NAME_TEXT,
				"required.text", CommonConstants.ERROR_MSG_FIELD_SIDE_EMPTY);
	
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, CommonConstants.FIELD_NAME_TITLE,
				"required.title", CommonConstants.ERROR_MSG_FIELD_SIDE_EMPTY);
		
		GuestBook guestBook = (GuestBook) arg0;
		 
 		if(guestBook!=null){
			
			if(guestBook.getText()!=null && (guestBook.getText().trim().length()<3 || guestBook.getText().trim().length()>500)){
				arg1.rejectValue(CommonConstants.FIELD_NAME_TEXT,"error.text.length", CommonConstants.ERROR_MSG_FIELD_CHARACTERS_LENGTH);
			}
			
			if(guestBook.getName()!=null && (guestBook.getName().trim().length()<10 || guestBook.getName().trim().length()>30)){
				arg1.rejectValue(CommonConstants.FIELD_NAME_NAME,"error.name.length", CommonConstants.ERROR_MSG_FIELD_CHARACTERS_LENGTH);
			}
			else if(guestBook.getName()!=null && !acceptedPatternMatch(guestBook.getName(),CommonConstants.ACCEPTED_CHARACTERS_PATTERN)){
				arg1.rejectValue(CommonConstants.FIELD_NAME_NAME,"error.invalid.characters", CommonConstants.ERROR_MSG_FIELD_INVALID_CHARACTERS);
			}
			
			if(guestBook.getTitle()!=null && (guestBook.getTitle().trim().length()<5 || guestBook.getTitle().trim().length()>40)){
				arg1.rejectValue(CommonConstants.FIELD_NAME_TITLE,"error.title.length", CommonConstants.ERROR_MSG_FIELD_CHARACTERS_LENGTH);
			}
			else if(guestBook.getTitle()!=null && !acceptedPatternMatch(guestBook.getTitle(),CommonConstants.ACCEPTED_CHARACTERS_PATTERN)){
				arg1.rejectValue(CommonConstants.FIELD_NAME_TITLE,"error.invalid.characters", CommonConstants.ERROR_MSG_FIELD_INVALID_CHARACTERS);
			}
			
			if(guestBook.getEmail()!=null && !guestBook.getEmail().trim().isEmpty() && !acceptedPatternMatch(guestBook.getEmail(),CommonConstants.ACCEPTED_EMAIL_PATTERN)){
				arg1.rejectValue(CommonConstants.FIELD_NAME_EMAIL,"error.invalid.email", CommonConstants.ERROR_MSG_FIELD_INVALID_EMAIL);
			}
			
			if(guestBook.getKeywords()!=null && !guestBook.getKeywords().trim().isEmpty() && !acceptedPatternMatch(guestBook.getKeywords(),CommonConstants.ACCEPTED_KEYWORDS_PATTERN)){
				arg1.rejectValue(CommonConstants.FIELD_NAME_KEYWORDS,"error.invalid.keywords", CommonConstants.ERROR_MSG_FIELD_INVALID_KEYWORD);
			}
		}
	}

	private boolean acceptedPatternMatch(String input,String regexPattern){
		
		if(input!=null){
			Pattern pattern = Pattern.compile(regexPattern);
			Matcher matcher = pattern.matcher(input);
			return matcher.matches();
		}
		return false;
	}
}
