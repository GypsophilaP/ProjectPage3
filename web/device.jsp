<%--
  Created by IntelliJ IDEA.
  User: Gypsophila
  Date: 2016/12/26
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FormatDeviceData</title>
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
                "<option value='VARCHAR' selected>VARCHAR</option>" +
                "<option value='INTEGER' >INTEGER</option>" +
                "<option value='FLOAT' >FLOAT</option>"+
                "<option value='DOUBLE'>DOUBLE</option>"+
                "<option value='DATETIME' >DATETIME</option>"+
                "<option value='timestamp'>timestamp</option>";

        var strTypeName = "TYPE &nbsp&nbsp";
        var strLenght = "<br>长度<input type='text' name='devices["+i+"].length'>";
        var strUnit = "<br><input type='text' name='devices["+i+"].unit'>"
        fileDiv.innerHTML += strValueName;
        fileDiv.innerHTML += strHtml;


        fileDiv.innerHTML += strTypeName;
        fileDiv.innerHTML += strType;
        fileDiv.innerHTML += strU1;

        fileDiv.innerHTML += strLenght
        fileDiv.innerHTML += strU2;
        i++;
    }

    function removeFile(obj) {
        obj.removeNode(true);
    }
</script>

<form action="define" method="post">
    <div name="outside" align="center" style=" margin-top:200px">
        Device: <input type="text" name="deviceType" ><br/>
        <tr>

            <td><div id="fileDiv"> </div></td>

        </tr>
        <input type="button" name="add" value="add" onclick="addItem()">
        <input type="submit" value="submit" >
    </div>
</form>
</body>
</html>
