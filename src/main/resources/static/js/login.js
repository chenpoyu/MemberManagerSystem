var alertClose = '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
var warningAlert = '<div class="alert alert-warning fade show" id="warning_alert" tabindex="-1" role="alert" aria-hidden="true"></div>';
var dangerAlert = '<div class="alert alert-danger fade show" id="danger_alert" tabindex="-1" role="alert" aria-hidden="true"></div>';

$(function() {
    $('#login').bind("click", login);
    $('#register').bind("click", register);
    $('#forget').bind("click", forget);
    
    // for test convience
    $('#account').val('admin');
    $('#pwd').val('1234');
});

function login() {
    var account = $('#account').val();
    var pwd = $('#pwd').val();
    $('#response').html('');

    var errorMsg = '';
    if (!accountRegex.test(account)) {
        errorMsg = '帳號格式錯誤';
    } else if (!pwdRegex.test(pwd)) {
        errorMsg = '密碼格式錯誤';
    } else {
    	var data = { 
    		account: account
			, pwd: pwd // TODO 加密
		};
		$.ajax({
			url: "./api/login"
			, method: "POST"
			, data: JSON.stringify(data)
			, error: function( jqXHR, textStatus ) {
				console.log( "Request failed: " + textStatus );
		        $(dangerAlert).appendTo('#response');
		        $('#response #danger_alert').html(alertClose + '帳號密碼錯誤');
			}
			, success: function (data) {
				console.log( "Success: " + data );
				$.cookie('token', data.token, {
					expires: 1
//					, path: '/'
//					, domain: 'localhost'
//					, secure: true
				});
				$.cookie('username', data.name);
				$(location).attr('href', "./main");
			}
		});
    }

    if (errorMsg.length > 0) {
        $(dangerAlert).appendTo('#response');
        $('#response #danger_alert').html(alertClose + errorMsg);
    }
}

function register() {
    var account = $('#r_account').val();
    var pwd = $('#r_pwd').val();
    var email = $('#r_email').val();
    var name = $('#r_name').val();
    $('#r_response').html('');

    var errorMsg = '';
    if (!accountRegex.test(account)) {
        errorMsg = '帳號格式錯誤';
    } else if (!pwdRegex.test(pwd)) {
        errorMsg = '密碼格式錯誤';
    } else if (!emailRegex.test(email)) {
        errorMsg = 'Email格式錯誤';
    } else {
    	var data = { 
    		account: account
			, pwd: pwd // TODO 加密
			, name: name
			, email: email
		};
		$.ajax({
			url: "./api/register"
			, method: "POST"
			, data: JSON.stringify(data)
			, error: function( jqXHR, textStatus ) {
				console.log( "Request failed: " + textStatus );
	            $(warningAlert).appendTo('#r_response');
	            $('#r_response #warning_alert').html(alertClose + jqXHR.responseJSON.message);
			}
			, success: function (msg) {
	            $('#r_cancel').click();
	            $(warningAlert).appendTo('#response');
	            $('#response #warning_alert').html(alertClose + '註冊成功，請用註冊之帳號密碼登入');
			}
		});
    }

    if (errorMsg.length > 0) {
        $(warningAlert).appendTo('#r_response');
        $('#r_response #warning_alert').html(alertClose + errorMsg);
    }
}

function forget() {
    var email = $('#f_email').val();
    $('#f_response').html('');

    var errorMsg = '';
    if (!emailRegex.test(email)) {
        errorMsg = 'Email格式錯誤';
    } else {
		$.ajax({
			url: "./api/forgot/" + account
			, method: "POST"
			, error: function( jqXHR, textStatus ) {
				console.log( "Request failed: " + textStatus );
	            $(warningAlert).appendTo('#f_response');
	            $('#f_response #warning_alert').html(alertClose + jqXHR.responseJSON.message);
			}
			, success: function (msg) {
	            $('#f_cancel').click();
	            $(warningAlert).appendTo('#response');
	            $('#response #warning_alert').html(alertClose + '驗證Email已發送，請至信箱確認');
			}
		});
    }

    if (errorMsg.length > 0) {
        $(warningAlert).appendTo('#f_response');
        $('#f_response #warning_alert').html(alertClose + errorMsg);
    }
}