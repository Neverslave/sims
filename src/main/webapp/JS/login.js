$(document).keypress(function(e) {
    if (e.which == 13) {
        login();
        // loadDict();
    }
});

$(document).ready(function() {

    var isIE = document.all ? true : false;
    $("input").each(function() {
        var placeholderValue = this.getAttribute("placeholder"); //缓存默认的placeholder值  
        if (isIE && placeholderValue) {
            this.setAttribute("value", placeholderValue);
            this.onfocus = function() {
                $.trim(this.value) == placeholderValue ? this.value = "" : '';
            };
            this.onblur = function() {
                $.trim(this.value) == "" ? this.value = placeholderValue : '';
            };
        }
    });


    $("#password").focus(function() {
        $(this).attr("type", "password");
    });
    $("#password").blur(function() {
        var placeholder = $(this).attr("placeholder");
        var value = $(this).val();
        if (!value || placeholder == value) {
            $(this).attr("type", "text");
        }

    });
});

function login() {
    var data = CloudUtils.convertStringJson('loginForm');
    var options = {
        url: 'login',
        data: data,
        callBackFun: function(data) {
            if (data.result == 0) {
                store.clear();
                store.set('menuList', JSON.stringify(data.menuList));
                store.set('userId', data.userId);
                store.set('roleId', data.roleId == null ? null : data.roleId);
                store.set('representId', data.representId == null ? null : data.representId);
                store.set('roleList', JSON.stringify(data.roleList));
                store.set('username', data.username == null ? null : data.username);
                store.set('corpId', data.corpId == null ? null : data.corpId);
                store.set('deptId', data.deptId == null ? null : data.deptId);
                store.set('roleType', data.roleType == null ? null : data.roleType);
                store.set('roleName', data.roleName == null ? null : data.roleName);
                store.set('mobilephone', data.mobilephone == null ? null : data.mobilephone);
                store.set('serverDate', data.serverDate == null ? null : data.serverDate);
                store.set('corpName', data.corpName == null ? null : data.corpName);
                window.location.href = "index.html";
            } else {
                bootbox.alert(data.resultNote);
                document.getElementById("clickImg").onclick();
                return false;
            }
        },
        errorCallback: function(data) {
            bootbox.alert("error");
        }
    };
    CloudUtils.ajax(options);
}

function loginCode() {


    var phone = $("#phone").val();
    var param = {
        phone: phone
    };

    var options = {
        url: 'login/phoneCode',
        data: JSON.stringify(param),
        callBackFun: function(data) {
            if (data.result == 0) {
                Countdown();
            } else {
                bootbox.alert(data.resultNote);
                return false;
            }
        }
    };
    CloudUtils.ajax(options);
}

/*验证码倒计时*/
var timer = 60;

function Countdown() {
    if (timer >= 1) {
        timer -= 1;
        var str = timer + "秒后可重发";
        $("#timeChange").text(str);
        $("#timeChange").attr("disabled", true);
        setTimeout(function() {
            Countdown();
        }, 1000);
    } else {
        $("#timeChange").attr("disabled", false);
        $("#timeChange").text("重新发送");
        timer = 60;
    }
}

function loginPhone() {
    var data = CloudUtils.convertStringJson('loginForm2');
    var options = {
        url: 'login/phone',
        data: data,
        callBackFun: function(data) {
            if (data.result == 0) {
                store.clear();
                store.set('menuList', JSON.stringify(data.menuList));
                store.set('userId', data.userId);
                store.set('roleId', data.roleId == null ? null : data.roleId);
                store.set('username', data.username == null ? null : data.username);
                store.set('corpId', data.corpId == null ? null : data.corpId);
                store.set('deptId', data.deptId == null ? null : data.deptId);
                store.set('roleType', data.roleType == null ? null : data.roleType);
                store.set('roleName', data.roleName == null ? null : data.roleName);
                store.set('mobilephone', data.mobilephone == null ? null : data.mobilephone);
                window.location.href = "index.html";
            } else {
                bootbox.alert(data.resultNote);
                return false;
            }
        },
        errorCallback: function(data) {
            bootbox.alert("error");
        }
    };
    CloudUtils.ajax(options);


}

