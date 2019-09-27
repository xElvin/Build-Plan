<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 13.09.2019
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rooms</title>

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-clockpicker.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-clockpicker.min.css">


    <script type="text/javascript" src="js/jquery/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="js/jquery/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.dataTables.js"></script>
    <script type="text/javascript" src="js/jquery/dataTables.bootstrap.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-ui.js"></script>
    <script type="text/javascript" src="js/jquery/bootstrap-clockpicker.js"></script>
    <script type="text/javascript" src="js/jquery/bootstrap-clockpicker.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>

    <script>
        $(function ()
        {
            $('#roomDataId').show();
            getRooms();
        });
    </script>
</head>
<body style="background-color: #e0f7f7;">

<jsp:include page="WEB-INF/parseJsp/logoutParse.jsp"></jsp:include>


<div id="roomDataId" style="display: none;">

</div>

<div class="modal fade" id="userModalId" role="dialog" style="display: none;" data-backdrop="static" data-keyboard="false">
</div>

<div class="modal fade" id="reserveModalId" role="dialog" style="display: none;" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="width: 40rem; ">
        <div class="modal-content" style="height: 40rem">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Reserve</h4>
            </div>
            <div class="modal-body">
                <form method="post">
                    <label for="SelectDay">Select Day</label>               <input type="text" id="SelectDay"       <%--value="${reserve.date}"--%> class="datepicker form-control"  placeholder="Select Day">
                    <label for="SelectStartTime">Select Start Clock</label> <input type="text" id="SelectStartTime" <%--value="${reserve.start_time}"--%> class="timepicker form-control" <%--data-format="hh:mm:ss"--%> type="text" placeholder="Select start clock">
                    <label for="SelectEndTime">Select End Clock</label>     <input type="text" id="SelectEndTime"   <%--value="${reserve.end_time}"--%> class="timepicker form-control" <%--data-format="hh:mm:ss"--%> type="text" placeholder="Select end clock">
                    <label for="PersonCount">Person count</label>           <input step="1" data-step-max="10" type="number" id="PersonCount" <%--value="${reserve.person_count}"--%> data-decimals="0" min="0" max="20" class="form-control" placeholder="Enter person count"/>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="reserveBtnId" class="btn btn-primary" onclick="reserve()">Reserve</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="infoModalId" role="dialog" style="display: none;" data-backdrop="static" data-keyboard="false">

</div>


<div class="modal fade" id="updateModal" role="dialog" style="display: none;" data-backdrop="static" data-keyboard="false">

</div>

</body>
</html>
