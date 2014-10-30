function CheckLength() { 
    var msg_area = document.getElementById("Message"); 
    msg_area.innerHTML = ""; 
    if (document.getElementById("itemtextid").value.length<2 && document.getElementById("itemtextid").value.length>300) { 
        msg_area.innerHTML = "YOU DID NOT ENTER ENOUGH INFORMATION";        
        return false; 
    }else if (document.getElementById("itemnameid").value.length<5) { 
        msg_area.innerHTML = "YOU DID NOT ENTER ENOUGH INFORMATION";        
        return false; 
    } else {
    	document.getElementById("createbookentryform").submit(); 
    }
} 
