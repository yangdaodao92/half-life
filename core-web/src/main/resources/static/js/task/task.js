$(function () {
    $('body').on('click', '.wrapper .header .fa-plus-circle', function () {
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
                    content: content,
                    childTaskList: childTask
                }),
                success: function () {

                }
            })
        })
    })
});
