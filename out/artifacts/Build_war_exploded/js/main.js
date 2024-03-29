var globRoomId = '';
var globPersonCount = '';
var globReserveId = '';

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
    $('.datepicker').datepicker({
        format: 'yyyy-mm-dd'
    });
    var minutes = ["00","10", "20", "30", "40", "50"];
    var hours = ["9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"];
    $('.timepicker').clockpicker({
        autoclose: true,
        twelvehour: false,
        afterShow: function() {
            $('.clockpicker-hours').find('.clockpicker-tick').filter(function (index, element)
            {
                return !($.inArray($(element).text(), hours) != -1)
            }).remove();
            $(".clockpicker-minutes").find(".clockpicker-tick").filter(function(index,element){
                return !($.inArray($(element).text(), minutes) != -1)
            }).remove();
        },
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


function getInfo(room_id)
{
    var user_id = Cookies.get('user_id');

    $('#infoModalId').modal('show');

    $.ajax({
        url: 'cs?action=getReserves',
        type: 'GET',
        dataType: 'html',
        data: 'user_id='+user_id+'&room_id='+room_id,
        success: function (data)
        {
            $('#infoModalId').html(data);
        },

        error: function ()
        {
            alert('Have an error!')
        }

    })
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

    $.ajax({
        url: "cs?action=updateUser",
        type: 'POST',
        dataType: 'html',
        data: 'floorId='+floorId+'&roomId='+roomId+'&userId='+userId,
        success: function (data, status, xhr)
        {
            var message = xhr.getResponseHeader('message');
            if (message == 'updated')
            {
                alert('Successfully updated!');
                getRooms();
                $('#userModalId').modal('toggle');
            }

            else if (message == 'not updated')
            {
                alert('Room have a max user!');
            }

        },
        error: function ()
        {
            alert('Have an error!');
        }
    })
}



function editReserve(reserve_id)
{
    globReserveId = reserve_id;
    $.ajax({
        type: 'GET',
        url: 'cs?action=editReserve',
        data: 'reserve_id='+reserve_id,
        dataType: 'html',
        success: function (data)
        {
            $('#infoModalId').modal('toggle');
            $('#updateReserveModal').modal('show');
            $('#updateReserveModal').html(data);
        },
        error: function () {
            alert('Have a error!')
        }
    })
}


function updateReserve()
{
    var date         = $('#SelectDayU').val();
    var start_time   = $('#SelectStartTimeU').val();
    var end_time     = $('#SelectEndTimeU').val();
    var person_count = $('#PersonCountU').val();
    $.ajax({
        type: 'POST',
        url: 'cs?action=updateReserve',
        data: 'date='+date+'&start_time='+start_time+'&end_time='+end_time+
              '&person_count='+person_count+'&reserve_id='+globReserveId,

        success: function (data, status, xhr)
        {
            var message = xhr.getResponseHeader('message');

            if (message == "reserved")
            {
                alert('Reservation updated!');
                $('#updateReserveModal').modal('toggle');
            }
            else if (message == "not reserved")
            {
                alert("The room was reserved for this time. Please, choose other time!")
            }
        },
        error: function () {
            alert('Have an error!')
        }
    })
}


function deleteReserve(reserve_id)
{
    var answer = confirm('Are you sure you want to delete the reservation?');
    if(answer)
    {
        $.ajax({
            type: 'POST',
            url: 'cs?action=deleteReserve',
            data: 'reserve_id='+reserve_id,
            complete: function () {
                alert('Reservation deleted');
                $('#infoModalId').modal('toggle');
            },
            error: function () {
                alert('Have a error!');
            }
        })
    }
}