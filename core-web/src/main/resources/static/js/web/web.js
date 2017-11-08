$(function () {

    // 添加网址
    $('body').on('click', '.wrapper .header .addTask', function () {
        $('#addWebModal .webWrapper,.web').val('');
        $('#addWebModal').modal('show');

        $('#addWebBtn').on('click', function () {
            var webWrapperName = $('#addWebModal .webWrapper').val();
            if (!webWrapperName) {
                alert('请输入类型');
                return
            }
            var webs = [];
            $('#addWebModal .web').each(function (i, ele) {
                if ($(ele).val()) {
                    webs.push({url: $(ele).val()})
                }
            });

            $.ajax({
                url: '/web/add',
                type: 'post',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify({
                    webWrapper: {name: webWrapperName},
                    webs: webs
                }),
                success: function (result) {
                    if (result.code == 200) {
                        $('#addWebModal').modal('hide');
                    }
                }
            })
        });
    });

});