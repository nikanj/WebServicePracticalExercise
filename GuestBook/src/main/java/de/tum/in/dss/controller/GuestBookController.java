package de.tum.in.dss.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dss.GuestBook;
import de.tum.in.dss.GuestBookEntries;
import de.tum.in.dss.GuestBookHelper;

/**
 * Servlet implementation class GuestBookController
 */
public class GuestBookController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -121477226026976456L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getRequestURI().contains("createbookentry")) {
			// request.setAttribute(CommonConstants.ERROR_MSG1_ATTRIBUTE,"");
			// request.setAttribute(CommonConstants.ERROR_MSG2_ATTRIBUTE,"");
			createBookEntry(request);
			if (request.getParameter("createbookentry") == null) {
				request.setAttribute("errorMessage1", null);
				request.setAttribute("errorMessage2", null);
			}
		}

		boolean isUserLoggedIn = false;
		Object userObject = request.getSession().getAttribute("userloggedin");

		if (userObject != null) {
			isUserLoggedIn = (Boolean) userObject;
		}

		GuestBookEntries guestBookEntries = (GuestBookEntries) getServletContext()
				.getAttribute("guestbookentries");
		setAttributes(guestBookEntries, request, response);

		if (isUserLoggedIn) {
			// Redirect as loggedin User
			request.setAttribute("user",
					request.getSession().getAttribute("user"));
			RequestDispatcher rd = request
					.getRequestDispatcher("./GuestBookHome.jsp");
			rd.forward(request, response);
		} else {
			// Redirect as anonymous User
			request.getSession().invalidate();
			RequestDispatcher rd = request
					.getRequestDispatcher("./GuestBookHome.jsp");
			rd.forward(request, response);
		}
	}

	private void setAttributes(GuestBookEntries guestBookEntries,
			HttpServletRequest request, HttpServletResponse response) {

		if (guestBookEntries != null) {
			if (request.getParameter("delete") != null) {
				deleteBookEntry(request, guestBookEntries);
			}

			if (request.getParameter("forward") != null) {
				guestBookEntries.nextDisplayListUpdate();
			} else if (request.getParameter("backward") != null) {
				guestBookEntries.previousDisplayListUpdate();
			} else {
				guestBookEntries.firstDisplayListUpdate();
			}

			request.setAttribute("currentStartIndex",
					guestBookEntries.getCurrentStartIndex() + 1);
			request.setAttribute(
					"currentEndIndex",
					(guestBookEntries.getCurrentEndIndex() > 1 ? guestBookEntries
							.getCurrentEndIndex() : 1));

			if (request.getParameter("sortByDate") != null) {
				guestBookEntries.sortGuestBookList();
				guestBookEntries.sortCurrentDisplayList();
			}
		}

		request.setAttribute("guestBookEntries", guestBookEntries);
	}

	private void createBookEntry(HttpServletRequest request) {
		// Date currentDate =
		// GuestBookHelper.formatDate(Calendar.getInstance().getTime());
		String itemName = request.getParameter("itemname");
		String itemText = request.getParameter("itemtext");
		Date currentDate = Calendar.getInstance().getTime();
		boolean isError = false;
		GuestBookEntries guestBookEntries = (GuestBookEntries) getServletContext()
				.getAttribute("guestbookentries");

		if ((itemName == null)
				|| (itemName != null && (itemName.trim().isEmpty()
						|| itemName.trim().length() < 5 || itemName.trim()
						.length() > 25))) {
			isError = true;
			request.setAttribute("errorMessage1",
					"Item Name should be min 5 and max 25 characters");
		}
		if ((itemText == null)
				|| (itemText != null && (itemText.trim().isEmpty()
						|| itemText.trim().length() < 2 || itemText.trim()
						.length() > 300))) {
			isError = true;
			request.setAttribute("errorMessage2",
					"Item Text should be min 2 and max 300 characters");
		}
		if (!isError) {
			GuestBook newEntry = new GuestBook(itemName, itemText, currentDate);
			guestBookEntries = GuestBookHelper.createNewBookList(
					guestBookEntries, newEntry);
			getServletContext().setAttribute("guestbookentries",
					guestBookEntries);
		}

	}

	@SuppressWarnings("unchecked")
	private void deleteBookEntry(HttpServletRequest request,
			GuestBookEntries guestBookEntries) {
		Set<String> parameterEntrySet = request.getParameterMap().keySet();

		if (parameterEntrySet != null) {
			Iterator<String> parameterNameIt = parameterEntrySet.iterator();

			while (parameterNameIt != null && parameterNameIt.hasNext()) {
				String parameterName = parameterNameIt.next();

				if (parameterName != null && parameterName.contains("bookid_")) {
					GuestBook deleteBook = new GuestBook();
					deleteBook.setItemName(((String[]) request
							.getParameterMap().get(parameterName))[0]);
					guestBookEntries.removeGuestBookEntry(deleteBook);
				}
			}
		}
	}
}
