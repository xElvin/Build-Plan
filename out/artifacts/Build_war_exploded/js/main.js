function callModal(userId, floorId)
{
    $('#userModalId').modal('show');
    getUserById(userId, floorId);
}


function getFloor()
{
    $.ajax({
        url: "cs?action=getFloors",
        type: 'GET',
        dataType: 'html',
        success: function (data)
        {
            $('#FloorDataId').html(data);
        },
        error: function ()
        {
            alert('Have an error!');
        }
    })
}


function getRooms()
{
    $.ajax({
        url: "cs?action=getRooms",
        type: 'GET',
        dataType: 'html',
        data: 'floorId='+Cookies.get('floorId'),
        success: function (data)
        {
            $('#roomDataId').html(data);
        },
        error: function ()
        {
            alert('Have an error!');
        }
    })
}


/*function getUsers()
{
    $.ajax({
        url: "cs?action=getUsers",
        type: 'GET',
        dataType: 'html',
        data: 'floorId='+Cookies.get('floorId'),
        success: function (data)
        {
            alert('success='+data);
            $('#roomDataId').html(data);
        },
        error: function ()
        {
            alert('Have an error!');
        }
    })
}*/




function getUserById(userid, floorid)
{
    $.ajax({
        url: "cs?action=getUserById",
        type: 'GET',
        dataType: 'html',
        data: 'userId='+userid+'&floorId='+floorid,
        success: function (data)
        {
            $('#userModalId').html(data);
        },
        error: function ()
        {
            alert('Have an error!');
        }
    })
}


/*function getFloorCombo()
{
    $.ajax({
        url: "cs?action=getFloorsCombo",
        type: 'GET',
        dataType: 'html',
        success: function (data)
        {
            $('#userFloor').html(data);
        },
        error: function ()
        {
            alert('Have an error!');
        }
    })
}*/



function getRoomCombo(floorId)
{
    $.ajax({
        url: "cs?action=getRoomsCombo",
        type: 'GET',
        dataType: 'html',
        data: 'floorId='+floorId,
        success: function (data)
        {
            $('#userRoom').html(data);
        },
        error: function ()
        {
            alert('Have an error!');
        }
    })
}



function updateUser(userId)
{
    var floorId = $('#userFloor').val();
    var roomId  = $('#userRoom').val();

    //alert('flo='+floorId+' rom='+roomId+' user='+userId);
    $.ajax({
        url: "cs?action=updateUser",
        type: 'POST',
        dataType: 'html',
        data: 'floorId='+floorId+'&roomId='+roomId+'&userId='+userId,
        success: function ()
        {
            getRooms();
            $('#userModalId').modal('toggle');
        },
        error: function ()
        {
            alert('Have an error!');
        }
    })
}