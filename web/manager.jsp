<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Gypsophila
  Date: 2016/12/30
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script type="text/javascript" src="jquery-2.1.1.min.js"></script>
<script>
    function cancle(userServId) {
        $.ajax({
            type : "POST",
            url : "/start.action",
            data : {
                userServId	: userServId
            },
            success: function(data){
                alert("success");
            }
        });
    }
</script>
<% int i = 0;%>
<head>
    <title>Manager</title>
</head>
<body>
<form name="form2" method="post"></form>
<form name="form1" method="post"></form>
    <s:form method="POST" action="manager">
        <tr>
            <s:submit value="点击查询"></s:submit>
        </tr>
        <tr>
            <td width="128" height="25" align="center"> ID</td>
            <td width="157" align="center">设备类型</td>
            <td width="147" align="center">设备地址</td>
            <td width="281" align="center">是否启用</td>
        </tr>
        <s:iterator value="#request.manage" id="ma">
<%--<% i++;
    String str = "<div id=\"" + i +"\"" ;
    out.println(str);
%>--%>

            <tr>
                <td width="128" height="25" align="center"><s:property  value="#ma.id" /></td>
                <td width="157" align="center"><s:property value="#ma.type"/></td>

                <td width="147" align="center"><s:property value="#ma.address"/></td>
                <td width="281" align="center"><s:property value="#ma.flag"/></td>
                <td><s:submit type="button" id="%{#ma.id}" value="启动" onclick="cancle(this.id)"/></td>
               <%-- <td><input id="${ma.type}" type="button" value="启动" onclick="start(this.id)"></td>--%>

            </tr>
               <%-- </div>--%>
        </s:iterator>
    </s:form>
</body>
</html>
