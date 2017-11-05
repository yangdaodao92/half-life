$(function () {
    $('#addTaskModal input[name="planDate"]').datepicker();
    $('#addTaskModal input[name="planDate"]').val(moment().format('MM/DD/YYYY'));
    // 加载本周任务
    $.ajax({
        url: '/task/list/week',
        type: 'post',
        success: function (result) {
            $('#accordion2').empty().append(result);
        }
    });
    // 加载今日任务
    loadTodayTaskList();

    // 添加任务
    $('body').on('click', '.wrapper .header .addTask', function () {
        $('#addTaskModal .main-task,.child-task').val('');
        $('#addTaskModal').modal('show');

        $('#addTaskBtn').on('click', function () {
            var content = $('#addTaskModal .main-task').val();
            if (!content) {
                alert('请输入主任务');
                return
            }
            var childTask = [];
            $('#addTaskModal .child-task').each(function (i, ele) {
                if ($(ele).val()) {
                    childTask.push({content: $(ele).val()})
                }
            });

            $.ajax({
                url: '/task/add',
                type: 'post',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify({
                    planDate: $('#addTaskModal input[name="planDate"]').val(),
                    content: content,
                    childTaskList: childTask
                }),
                success: function (result) {
                    if (result.code == 200) {
                        $('#addTaskModal').modal('hide');
                    }
                }
            })
        });
    });

    // 移动到今日任务
    $('body').on('click', '.wrapper .moveToToday', function () {
        $.ajax({
            url: '/task/moveToToday',
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify({
                currentTaskId: $(this).attr('taskId')
            }),
            success: function (result) {
                if (result.code == 200) {
                    loadTodayTaskList();
                }
            }
        })
    });

    // 从今日任务移出
    $('body').on('click', '.wrapper .removeFromToday', function () {
        $.ajax({
            url: '/task/removeFromToday',
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify({
                currentTaskId: $(this).attr('taskId')
            }),
            success: function (result) {
                if (result.code == 200) {
                    loadTodayTaskList();
                }
            }
        })
    });

    // 添加子任务
    $('body').on('click', '.addChildTaskInput', function () {
        var childTaskEle = '\
            <input class="child-task form-control form-control-sm" type="text" placeholder="子任务……"/>\
            <i class="fa fa-minus-circle deleteChildTaskInput"></i>';
        $(this).parents('.form-group:first').append(childTaskEle)
    });

});

function loadTodayTaskList() {
    $.ajax({
        url: '/task/list/today',
        type: 'post',
        success: function (result) {
            $('#accordion1').empty().append(result);
        }
    });

}
