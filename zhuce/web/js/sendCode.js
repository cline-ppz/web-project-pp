// 点击获取，进入倒计时，按序号进行

let clock = '';
let nums = 60;
let btn = null;
let code;

function time(thisBtn) {//③time()函数处理“获取”按钮
    btn = thisBtn;
    btn.disabled = true; // 将按钮置为不可点击
    btn.value = nums + '秒后可重新获取';//让按钮显示倒计时
    clock = setInterval(doLoop, 1000); // ④setInterval（arg1，arg2）为定时器，arg1为执行哪个函数，arg2为多久执行一次（单位毫秒），
}
function doLoop() {
    nums--;
    if (nums > 0) {
        btn.value = nums + '秒后可重新获取';
    } else {//如果倒数计时结束，就清除定时器，将按钮状态改为可点击，并将按钮值显示为“重新发送”，最后重置倒计时时间60秒。
        clearInterval(clock); // 清除js定时器
        btn.disabled = false;
        btn.value = '重新发送验证码';
        nums = 60; // 重置时间
    }
}

//请求后台发送验证码
let sendCode = function () {
    $.ajax({
        url : "UserServlet",
        type : "post",
        dataType:"json",
        success : function(response) {//成功回调函数，失败不调用！
            code  = response;

        },
    });
}