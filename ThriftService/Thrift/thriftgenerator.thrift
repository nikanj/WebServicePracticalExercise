namespace java de.tum.in.dss.service

struct GuestBook
{
	1:i32 guestBookId,
	2:string title,
	3:string name,
	4:string text,
	5:string email,
 	6:string keywords,
 	7:list<string> keywordList;
}

service addservice
{
list<GuestBook> retrieveGuestBook(GuestBook guestBook)
}