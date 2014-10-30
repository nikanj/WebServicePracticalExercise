package de.tum.in.dss.service;

import java.util.Iterator;
import java.util.List;

import org.apache.thrift.TException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;


public class myimpl implements addservice.Iface{
	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public List<GuestBook> retrieveGuestBook(GuestBook guestBook)
			throws TException {
		String sql = "select * from GUEST_BOOK where BOOK_ID = :guestBookId or AUTHOR_NAME = :name or AUTHOR_TITLE = :title or BOOK_DATA = :text or GUEST_EMAIL_ID = :email";

	    SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(guestBook);
	    Object obj = this.namedParameterJdbcTemplate.query(sql, namedParameters, new GuestBookMapper());
	    
	    if(obj!=null){
	    	List<GuestBook> guestBookList = (List<GuestBook>) obj;
	    	return guestBookList;
	    }
	    else{
	    	return null;
	    }
	}
}
	


