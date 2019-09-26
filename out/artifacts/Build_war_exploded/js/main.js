var globRoomId = '';
var globPersonCount = '';

function callModal(userId, floorId)
{
    $('#userModalId').modal('show');
    getUserById(userId, floorId);
}


function callReserveModal(roomId, personCount)
{
    globRoomId = roomId;
    globPersonCount = personCount;
    $('#reserveModalId').modal('show');
}


$(function ()
{
    $('#SelectDay').datepicker({
        format: 'yyyy-mm-dd'
    });

    $('.timepicker').clockpicker({
        autoclose: true,
        twelvehour: false,
        default: DisplayCurrentTime()
    }).find('input').val(DisplayCurrentTime())
});

function DisplayCurrentTime() {
    var date = new Date();
    var hours = date.getHours() > 12 ? date.getHours() - 12 : date.getHours();
    var am_pm = date.getHours() >= 12 ? "PM" : "AM";
    hours = hours < 10 ? "0" + hours : hours;
    var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
    time = hours + ":" + minutes + ":" + am_pm;
    //time = hours + ":" + minutes + am_pm;
    return time;
};



function reserve()
{
    var date       = $('#SelectDay').val();
    var start_time = $('#SelectStartTime').val();
    var end_time   = $('#SelectEndTime').val();
    var person_count = $('#PersonCount').val();
    var room_id    = globRoomId;
    var user_id    = Cookies.get('user_id');
    //alert(person_count+' globco='+globPersonCount);

    if (date != '' && date != null && start_time != '' && start_time != null && end_time != '' && end_time != null && person_count > 0)
    {
        if (person_count <= globPersonCount)
        {
            $.ajax({
                url: 'cs?action=reserve',
                type: 'POST',
                dataType: 'html',
                data: 'date='+date+'&start_time='+start_time+'&end_time='+end_time+
                      '&person_count='+person_count+'&room_id='+room_id+'&user_id='+user_id,
                success: function (data, status, xhr)
                {
                    var message = xhr.getResponseHeader('message');

                    if (message == "reserved")
                    {
                        alert('Room successful reserved!');
                        $('#reserveModalId').modal('toggle')
                    }
                    else if (message == "not reserved")
                    {
                        alert("The room was reserved for this time. Please, choose other time!")
                    }
                },
                error: function ()
                {
                    alert('Have an error!')
                }
            })
        }

        else
        {
            alert('Please, do not enter more than '+globPersonCount);
        }
    }

    else
    {
        alert('Please fill all fields!');
    }
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