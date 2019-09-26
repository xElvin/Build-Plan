<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="modal-dialog" style="width: 45rem; ">
    <div class="modal-content" style="height: 55rem">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Person add</h4>
        </div>
        <div class="modal-body">
            <form method="post">
                <label for="userName">Name</label>       <input type="text" id="userName"    value="${u.name}"            name="name"       class="form-control" disabled="disabled">
                <label for="userSurname">Surname</label> <input type="text" id="userSurname" value="${u.surname}"         name="surname"    class="form-control" disabled="disabled">
                <label for="userDep">Department</label>  <input type="text" id="userDep"     value="${u.department.name}" name="department" class="form-control" disabled="disabled">
                <label for="userMail">Mail</label>       <input type="text" id="userMail"    value="${u.mail}"            name="mail"       class="form-control" disabled="disabled">
                <label for="userPos">Position</label>    <input type="text" id="userPos"     value="${u.position.name}"   name="position"   class="form-control" disabled="disabled">
                <label for="userFloor">Floor</label>     <select id="userFloor" value="${u.floor.id}" name="floor" class="form-control selectpicker" onchange="getRoomCombo($(this).val())">

                                                            <c:forEach items="${floors}" var="f">
                                                                <option ${f.id == u.floor.id ? 'selected':''} value="${f.id}">${f.name}</option>
                                                            </c:forEach>
                                                         </select>

                <label for="userRoom">Room</label>       <select id="userRoom"  value="${u.room.id}" name="room" class="form-control selectpicker">
                                                            <c:forEach items="${rooms}" var="r">
                                                                <option ${r.id == u.room.id ? 'selected':''} value="${r.id}">${r.name}</option>
                                                            </c:forEach>
                                                         </select>

                <div class="modal-footer">
                    <button type="button" id="addPersonBtnId" class="btn btn-primary" onclick="updateUser(${u.id})">Edit</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>
        <%--<div class="modal-footer">
            <button type="button" id="addPersonBtnId" class="btn btn-primary" onclick="updateUser(${u.id})">Edit</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>--%>
    </div>
</div>





<%--<div class="modal-dialog" style="width: 45rem; ">
    <div class="modal-content" style="height: 55rem">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Person add</h4>
        </div>
        <div class="modal-body">
            <form method="post">
            </form>
        </div>
        &lt;%&ndash;<div class="modal-footer">
            <button type="button" id="addPersonBtnId" class="btn btn-primary" onclick="updateUser(${u.id})">Edit</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>&ndash;%&gt;
    </div>
</div>--%>

