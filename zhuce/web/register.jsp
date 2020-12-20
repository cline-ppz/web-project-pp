<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>用户注册</title>
		<link href="css/register.css" rel="stylesheet"/>
		<style type="text/css">
			.body{
				background-image: url(images/log3.jpg);
				background-repeat: no-repeat ;
				background-size: 100%;
			}
			img{
				margin: 30px;
				cursor: pointer;
			}
		</style>
    </head> 
    <body class="body">
		<div id="loginForm">
			<form>
				<p style="font-size: larger; color: blue; margin-left: 30%;">账号注册</p>
			    <p>
					<label>&nbsp;&nbsp;
					<input type="text" id="phone" name="phone" placeholder="手机号码"/></label>
			    </p>
			    <p>
					<label>&nbsp;&nbsp;
						<input type="text" id="checkCode" name="checkCode" placeholder="校验码"/>
						<button  id="getCheckCode" name="getCheckCode"  value="获取验证码" οnclick="sendCode(this)"></button>
					</label>
			    </p>
				<p>
					<label>&nbsp;&nbsp;
						<input type="password" id="password1" name="password1" placeholder="登陆密码"/>
					</label>
				</p>
				<p>
					<label>&nbsp;&nbsp;
						<input type="password" id="password2" name="password2" placeholder="确认密码"/>
					</label>
				</p>
				<p>&nbsp;&nbsp;
					<label>
						<input type="checkbox" />已阅读并同意《用户协议》
					</label>
				</p>
			    <p>&nbsp;&nbsp;
					<label>
						<input type="submit" id="register" value="立即注册" />
					</label>
			    </p>
				<p id="login">
					<font >立即登陆>></font>
				</p>
			</form>
		</div>

    </body>
	<script type="text/javascript">
		function change(obj) {
			obj.src = "${ctx}/Code?time=" + new Date().getTime();
		}
		function checkForm() {
			var password = checkPassword();
			var pwd2 = ConfirmPassword();
			var phone = checkPhone();
			return password && pwd2 && phone;
//          alert(nametip.value);
		}
		//验证密码
		function checkPassword() {
			var password = document.getElementById('password');
			var errpass = document.getElementById('errpass');
			var pattern = /^\w{8,16}$/; //密码要在8-16位
			if (!pattern.test(password.value)) {
				errpass.innerHTML = "密码不合规范"
				errpass.className = "error"
				return false;
			} else {
				errpass.innerHTML = "OK"
				errpass.className = "success";
				return true;
			}
		}
		//确认密码
		function ConfirmPassword() {
			var userpasswd = document.getElementById('userPasword');
			var userConPassword = document.getElementById('userConfirmPasword');
			var errConPasswd = document.getElementById('conPasswordErr');
			if ((userpasswd.value) != (userConPassword.value)
					|| userConPassword.value.length == 0) {
				errConPasswd.innerHTML = "上下密码不一致"
				errConPasswd.className = "error"
				return false;
			} else {
				errConPasswd.innerHTML = "OK"
				errConPasswd.className = "success";
				return true;
			}
		}
		//验证手机号
		function checkPhone() {
			var userphone = document.getElementById('userPhone');
			var phonrErr = document.getElementById('phoneErr');
			var pattern = /^1[34578]\d{9}$/; //验证手机号正则表达式
			if (!pattern.test(userphone.value)) {
				phonrErr.innerHTML = "手机号码不合规范"
				phonrErr.className = "error"
				return false;
			} else {
				phonrErr.innerHTML = "OK"
				phonrErr.className = "success";
				return true;
			}
		}
	</script>

</html>