<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 26.09.2019
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal-dialog" style="width: 40rem; ">
    <div class="modal-content" style="height: 40rem">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Edit</h4>
        </div>
        <div class="modal-body">
            <form method="post">
                <label for="SelectDayU">Select Day</label>               <input type="text" id="SelectDayU"       value="${reserve.date}" class="datepicker form-control"  placeholder="Select Day">
                <label for="SelectStartTimeU">Select Start Clock</label> <input type="text" id="SelectStartTimeU" value="${reserve.start_time}" class="timepicker form-control" <%--data-format="hh:mm:ss"--%> type="text" placeholder="Select start clock">
                <label for="SelectEndTimeU">Select End Clock</label>     <input type="text" id="SelectEndTimeU"   value="${reserve.end_time}" class="timepicker form-control" <%--data-format="hh:mm:ss"--%> type="text" placeholder="Select end clock">
                <label for="PersonCountU">Person count</label>           <input step="1" data-step-max="10" type="number" id="PersonCountU" value="${reserve.person_count}" data-decimals="0" min="0" max="20" class="form-control" placeholder="Enter person count"/>
            </form>
        </div>
        <div class="modal-footer">
            <button type="button" id="reserveBtnId" class="btn btn-primary" onclick="updateReserve()">Update</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        </div>
    </div>
</div>
