
$(function() {
	validateKickout();
    validateRule();
	$('.imgcode').click(function() {
		var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
		$(".imgcode").attr("src", url);
	});
    $('.greeting').css('display','none');
    $('.blindfold').css('display','none');
});

// 鼠标在输入用户名时，图片样式变化
$(".uname").focus(function() {
    $('.normal').css('display','none');
    $('.greeting').css('display','block');
    $('.blindfold').css('display','none');
}).blur(function() {
    $('.normal').css('display','block');
    $('.greeting').css('display','none');
    $('.blindfold').css('display','none');
});

// 鼠标在输入密码时，图片样式变化
$(".pword").focus(function() {
    $('.normal').css('display','none');
    $('.greeting').css('display','none');
    $('.blindfold').css('display','block');
}).blur(function() {
    $('.normal').css('display','block');
    $('.greeting').css('display','none');
    $('.blindfold').css('display','none');
});

// 鼠标到输入验证码时，图片样式变化
$(".code").focus(function() {
    $('.normal').css('display','none');
    $('.greeting').css('display','block');
    $('.blindfold').css('display','none');
}).blur(function() {
    $('.normal').css('display','block');
    $('.greeting').css('display','none');
    $('.blindfold').css('display','none');
});

$.validator.setDefaults({
    submitHandler: function() {
		login();
    }
});

function login() {
	$.modal.loading($("#btnSubmit").data("loading"));
	var username = $.common.trim($("input[name='username']").val());
    var password = $.common.trim($("input[name='password']").val());
    var validateCode = $("input[name='validateCode']").val();
    var rememberMe = $("input[name='rememberme']").is(':checked');
    $.ajax({
        type: "post",
        url: ctx + "login",
        data: {
            "username": username,
            "password": password,
            "validateCode" : validateCode,
            "rememberMe": rememberMe
        },
        success: function(r) {
            if (r.code == 0) {
                location.href = ctx + 'index';
            } else {
            	$.modal.closeLoading();
            	$('.imgcode').click();
            	$(".code").val("");
            	$.modal.msg(r.msg);
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    // 表单必输项校验
    $("#signupForm").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: icon + "请输入您的用户名",
            },
            password: {
                required: icon + "请输入您的密码",
            }
        }
    })
}

function validateKickout() {
	if (getParam("kickout") == 1) {
	    layer.alert("<font color='red'>您已在别处登录，请您修改密码或重新登录</font>", {
	        icon: 0,
	        title: "系统提示"
	    },
	    function(index) {
	        //关闭弹窗
	        layer.close(index);
	        if (top != self) {
	            top.location = self.location;
	        } else {
	            var url  =  location.search;
	            if (url) {
	                var oldUrl  = window.location.href;
	                var newUrl  = oldUrl.substring(0,  oldUrl.indexOf('?'));
	                self.location  = newUrl;
	            }
	        }
	    });
	}
}

function getParam(paramName) {
    var reg = new RegExp("(^|&)" + paramName + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}