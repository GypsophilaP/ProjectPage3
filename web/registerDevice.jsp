<%--
  Created by IntelliJ IDEA.
  User: Gypsophila
  Date: 2016/12/26
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<script language="JavaScript">
    var i = 0;
    function addItem() {

        var fileDiv = document.all['fileDiv'];
        var strHtml = "<br><input type='text'  name='devices[" + i + "].columnName'> " ;

        var strU1 = "<u>";
        var strValueName = "Column"+i + "&nbsp&nbsp";
        var strU2 = "</u>";
        var strType = "<br><select  name='devices[" + i + "].type'>" +
                "<option value='' selected>VARCHAR</option>" +
                "<option value='' >INTEGER</option>" +
                "<option value='' >FLOAT</option>"+
                "<option value=''>double</option>"
        "<option value='' >DATETIME</option>"+
        "<option value=''>timestamp</option>";

        var strTypeName = "TYPE &nbsp&nbsp";
        var strLenght = "<br><input type='text' name='devices["+i+"].length'>";
        var strUnit = "<br><input type='text' name='devices["+i+"].unit'>"
        fileDiv.innerHTML += strHtml;

        fileDiv.innerHTML += strU1;
        fileDiv.innerHTML += strValueName;
        fileDiv.innerHTML += strU2;
        fileDiv.innerHTML += strType;
        fileDiv.innerHTML += strU1;
        fileDiv.innerHTML += strTypeName;
        fileDiv.innerHTML += strU2;
        i++;
    }

    function removeFile(obj) {
        obj.removeNode(true);
    }
</script>

<form action="formatDeviceData" method="post">
    <div name="outside" align="center" style=" margin-top:200px">
        Device: <input type="text" name="deviceName" ><br/>
        <tr>

            <td><div id="fileDiv"> </div></td>

        </tr>
        <input type="button" name="add" value="add" onclick="addItem()">
        <input type="submit" value="submit" >
    </div>
</form>
</body>
</html>
