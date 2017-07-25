var wait = 60;

function sendEmailFun() {
    if (!checkEmail()) {
        alert("邮箱格式不正确，请检查！");
        return;
    }
    var emailStr = document.getElementById('email').value;
    $.post('sendEmail', { email: emailStr }, function (data) {
        if (data.indexOf('successful') > 0) {
            for (var j = wait; j >= 0; j--) {
                setTimeout('doUpdate(' + j + ')', (wait - j) * 1000);
            }
        } else {
            alert("邮件发送失败，请重试！");
        }
    });
}

function doUpdate(num) {
    var btn = document.getElementById('sendEmail');
    btn.setAttribute("disabled", true);
    btn.innerHTML = "重新发送(" + num + "s)";
    if (num == 0) {
        btn.removeAttribute("disabled");
        btn.innerHTML = "免费获取验证码";
    }
}