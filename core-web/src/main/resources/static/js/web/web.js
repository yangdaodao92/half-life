var webWrapperTemplate = '\
    <div class="panel panel-default panelWebWrapperId_@{webWrapperId}" style="margin-bottom: 5px">\
        <div class="panel-heading" role="tab" id="headingOne1_@{webWrapperId}">\
            <h5 class="panel-title" style="display: flex; justify-content: space-between; align-items: center">\
                <a data-toggle="collapse" href="#collapseOne1_@{webWrapperId}" aria-expanded="true" aria-controls="collapseOne1_@{webWrapperId}"\
                    <span>@{webWrapperName}</span>\
                </a>\
                <i class="fa fa-edit editWebWrapper" style="cursor: pointer" webWrapperId="@{webWrapperId}"></i>\
            </h5>\
        </div>\
        <div id="collapseOne1_@{webWrapperId}" class="panel-collapse collapse show" role="tabpanel" aria-labelledby="headingOne">\
            @{webList}\
        </div>\
    </div>';
var webTemplate = '\
    <div>\
        <div style="display: flex; justify-content: space-between; align-items: center">\
            <span>@{title}</span>\
            <i class="fa fa-clone openUrl" url="@{url}"></i>\
        </div>\
    </div>';
var addWebRowTemplate = '\
    <div class="addWebRow" webId="@{webId}">\
        <input name="webTitle" class="add-web-name form-control form-control-sm" value="@{title}" type="text" placeholder="Title……"/>\
        <input name="webUrl" class="add-web-url form-control form-control-sm" value="@{url}" type="text" placeholder="Url……"/>\
        <i class="fa fa-minus-circle deleteWeb" webId="@{webId}"></i>\
    </div>';

$(function () {

    $.ajax({
        url: '/web/load',
        type: 'post',
        dataType: 'json',
        success: function (webWrapperList) {
            $.each(webWrapperList, function (i, webWrapper) {
                $('#accordion1').append(renderWrapperTemplate(webWrapper))
            })
        }
    });
    // 增加新的网址输入框
    $('body').on('click', '.addWebInput', function () {
        $(this).parent().siblings('.addWebList').append(renderTemplate(addWebRowTemplate, {
            webId: '', title: '', url: ''
        }))
    });

    // 打开添加网址弹窗
    $('body').on('click', '.wrapper .header .addWebWrapper', function () {
        $('#addWebModal .webWrapperName').val('');
        $('#addWebModal .addWebRow').remove();
        $('#addWebModal').modal('show');
    });
    $('#addWebBtn').on('click', function () {
        var webWrapperName = $('#addWebModal .webWrapperName').val();
        if (!webWrapperName) {
            alert('请输入类型');
            return
        }
        var webs = [];
        $('#addWebModal .addWebRow').each(function (i, ele) {
            webs.push({
                url: $(ele).find('input[name="webUrl"]').val(),
                title: $(ele).find('input[name="webTitle"]').val()
            })
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
            success: function (webWrapper) {
                $('#accordion1').append(renderWrapperTemplate(webWrapper));
                $('#addWebModal').modal('hide');
            }
        })
    });
    $('#addWebModal').on('click', '.deleteWeb', function () {
        $(this).parent().remove()
    });

    // 点击修改 打开修改弹窗
    $('body').on('click', '.editWebWrapper', function () {
        loadByWebWrapperId($(this).attr('webWrapperId'), function (webWrapper) {
            $('#updateWebModal input[name="webWrapperName"]').val(webWrapper.name);
            $('#updateWebModal input[name="webWrapperName"]').attr('webWrapperId', webWrapper.id);
            $('#updateWebModal .addWebList').empty();

            var webListDiv = '';
            $.each(webWrapper.webList, function (index, web) {
                webListDiv += renderTemplate(addWebRowTemplate, {
                    webId: web.id,
                    title: web.title,
                    url: web.url
                })
            });
            $('#updateWebModal .addWebList').append(webListDiv);
            $('#updateWebModal').modal('show');
        });
    });

    // 修改网址
    $('#updateWebBtn').on('click', function () {
        var webWrapperId = $('#updateWebModal .webWrapperName').attr('webWrapperId');
        var webWrapperName = $('#updateWebModal .webWrapperName').val();
        if (!webWrapperName) {
            alert('请输入类型');
            return
        }
        var webs = [];
        $('#updateWebModal .addWebRow').each(function (i, ele) {
            webs.push({
                id: $(ele).attr('webId'),
                url: $(ele).find('input[name="webUrl"]').val(),
                title: $(ele).find('input[name="webTitle"]').val()
            })
        });

        $.ajax({
            url: '/web/update',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify({
                webWrapper: {
                    id: webWrapperId,
                    name: webWrapperName
                },
                webs: webs
            }),
            success: function (webWrapper) {
                $('.panelWebWrapperId_' + webWrapper.id).replaceWith(renderWrapperTemplate(webWrapper));
                $('#updateWebModal').modal('hide');
            }
        })
    });
    $('#updateWebModal').on('click', '.webWrapperNameDelete', function () {
        var webWrapperId = $('#updateWebModal .webWrapperName').attr('webWrapperId');
        $.ajax({
            url: '/web/delete/webWrapper/' + webWrapperId,
            type: 'post',
            dataType: 'json',
            success: function (webWrapper) {
                if (!webWrapper.isValid) {
                    $('.panelWebWrapperId_' + webWrapperId).remove();
                    $('#updateWebModal').modal('hide');
                }
            }
        })
    });
    $('#updateWebModal').on('click', '.deleteWeb', function () {
        var $this = $(this);
        $.ajax({
            url: '/web/delete/web/' + $this.parent().attr('webId'),
            type: 'post',
            dataType: 'json',
            success: function (webWrapper) {
                if (webWrapper) {
                    $('.panelWebWrapperId_' + webWrapper.id).replaceWith(renderWrapperTemplate(webWrapper));
                    $this.parent().remove();
                }
            }
        })
    });

    // 打开网址
    $('body').on('click', '.openUrl', function () {
        if ($(this).attr('url')) {
            window.open($(this).attr('url'))
        }
    });

    // 动态解析网址title
    $('body').on('blur', '.addWebRow input[name="webUrl"]', function () {
        var $this = $(this);
        if (!$this.siblings('input[name="webTitle"]').val()) {
            $.ajax({
                url: '/web/getWebPageTitle',
                type: 'post',
                data: {
                    url: $this.val()
                },
                success: function (title) {
                    if (title) {
                        $this.siblings('input[name="webTitle"]').val(title)
                    }
                }
            })
        }
    })

});

function renderWrapperTemplate(webWrapper) {
    if (webWrapper) {
        var webListDivs = '';
        $.each(webWrapper.webList, function (i, web) {
            webListDivs += renderTemplate(webTemplate, {
                title: web.title,
                url: web.url
            })
        });
        return renderTemplate(webWrapperTemplate, {
            webWrapperId: webWrapper.id,
            webWrapperName: webWrapper.name,
            webList: webListDivs
        });
    }
}

function loadByWebWrapperId(webWrapperId, func) {
    $.ajax({
        type: 'get',
        url: '/web/loadByWebWrapperId',
        dataType: 'json',
        data: {
            webWrapperId: webWrapperId
        },
        success: function (data) {
            func(data)
        }
    })
}

function renderTemplate(template, object) {
    $.each(object, function (key, val) {
        template = template.replace(new RegExp('@{' + key + '}', 'gm'), val || '');
    });
    return template;
}