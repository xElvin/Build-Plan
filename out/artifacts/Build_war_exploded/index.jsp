<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 13.09.2019
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Main Page || Floors</title>

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css">


    <script type="text/javascript" src="js/jquery/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="js/jquery/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.dataTables.js"></script>
    <script type="text/javascript" src="js/jquery/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-ui.js"></script>

    <script>
        $(function ()
        {
            $('#FloorDataId').show();
            getFloor();
        })
    </script>

  </head>



  <body style="background-color: #e0f7f7;">
  <div id="FloorDataId">

  </div>
  </body>

</html>
