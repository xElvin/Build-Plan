function callModal(userId)
{
    $('#userModalId').modal('show');
    getUserById(userId);
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




function getUserById(userid)
{
    $.ajax({
        url: "cs?action=getUserById",
        type: 'GET',
        dataType: 'html',
        data: 'userId='+userid,
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


function getFloorCombo()
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
}



function getRoomCombo()
{
    $.ajax({
        url: "cs?action=getRoomsCombo",
        type: 'GET',
        dataType: 'html',
        data: 'floorId='+Cookies.get('floorId'),
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

    alert('flo='+floorId+' rom='+roomId+' user='+userId);
    /*$.ajax({
        url: "cs?action=updateUser",
        type: 'POST',
        dataType: 'html',
        data: 'floorId='+floorId+'&roomId='+roomId+'&userId='+userId,
        success: function ()
        {
            alert('User successfully updated!')
        },
        error: function ()
        {
            alert('Have an error!');
        }
    })*/
}