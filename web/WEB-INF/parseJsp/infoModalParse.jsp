<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 26.09.2019
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="modal-dialog" style="width: 75rem; ">
    <div class="modal-content" style="height: 50rem">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Reserves</h4>
        </div>
        <div class="modal-body">
            <form method="post">

                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">Date</th>
                        <th scope="col">Start time</th>
                        <th scope="col">End time</th>
                        <th scope="col">Person count</th>
                        <th scope="col">Room's name</th>
                    </tr>
                    </thead>

                    <tbody>

                    <c:forEach items="${reserves}" var="r" varStatus="loop">
                        <tr>
                            <th scope="row">${loop.count}</th>
                            <td>${r.date}</td>
                            <td>${r.start_time}</td>
                            <td>${r.end_time}</td>
                            <td>${r.person_count}</td>
                            <td>${r.room_name}</td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
            </form>
        </div>

    </div>
</div>


