function getContextPath() {
	var hostIndex = location.href.indexOf( location.host ) + location.host.length;
	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
}


$(function() {
	$("#getout").click(function() {
		
		var checkdel = confirm("정말로 탈퇴하시겠습니까?");
		
		if(checkdel == true){
		
			var loginId = $("#loginId").val();
			location.href=getContextPath()+"/memberdelete?loginId="+loginId;
		}
	});
});