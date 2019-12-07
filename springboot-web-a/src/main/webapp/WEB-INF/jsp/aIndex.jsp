<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
	/*//使用设置响应头允许跨域
	$(document).ready(function() {
		$.ajax({
			type : "POST",
			async : false,
			url : "http://b.xiaoming.com:8081/ajaxB",
			dataType : "json",
			success : function(data) {
				alert(data.code);
			},
			error : function() {
				alert('fail');
			}
		});

	});*/

	//jsonp解决跨域问题 只能是GET方法
   /* $(document).ready(function() {
        $.ajax({
            type : "GET",
            async : false,
            url : "http://b.xiaoming.com:8081/ajaxB",
            dataType : "jsonp",
			jsonp : "jsonCallBack",//服务端用于接收callback调用的function名的参数 发送请求http://b.itmayiedu.com:8081/ajaxB?jsonpCallback=随机数
            success : function(data) {
                alert(data.code);
            },
            error : function() {
                alert('fail');
            }
        });

    });*/

   //使用HttpClient进行内部转发
    $(document).ready(function() {
        $.ajax({
            type : "POST",
            async : false,
            url : "http://a.xiaoming.com:8080/forwardB",
            dataType : "json",
            success : function(data) {
                alert(data.code);
            },
            error : function() {
                alert('fail');
            }
        });

    });
</script>
</head>
<body>显示 ....
</body>
</html>