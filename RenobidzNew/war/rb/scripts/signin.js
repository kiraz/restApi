function init() {
	var location = window.location.origin;
	gapi.client.load('userEndpoint', 'v1', null, location+'/_ah/api');
}

$(document).ready(function(){

    //** signIn user (email) **/
    $(document).on('click','.signInBtn', function(event){
		event.preventDefault();

        var isValid = $("#signInForm").valid();

        if(isValid) {
        	logInUser();
        }
    });


   //** signIn user (email) **/
    $(document).on('click','.signInEmailBtn', function(event){
		event.preventDefault();

		//todo validate signin
        signInEmailUser();
    });


    $("#signInForm").validate({
		rules : {
			firstNameSignIn: "required",
			lastNameSignIn: "required",
			emailSignIn: {
				required: true,
				email: true
			},
			passwordSignIn: {
			    required: true,
			    minlength: 6
			},
			passwordConfirmSignIn: {
                equalTo: "#passwordSignIn"
			}
		},
		messages: {
		    firstNameSignIn: {
		        required: "First name is required."
		    },
		    lastNameSignIn: {
		        required: "Last name is required."
		    },
		    emailSignIn: {
		        required: "Email is required."
		    },
            passwordSignIn: {
                required: "Password is required.",
                minlength: "Password must be at least 6 characters."
            },
            passwordConfirmSignIn: {
                equalTo: "Passwords must match."
            }
		}
	});


	function signInUser() {
		var requestObject = {
			firstName: $("#loginUserEmail").val(),
			lastName: $("#lastNameSignIn").val(),
			email : $("#emailSignIn").val(),
			password : $("#passwordSignIn").val()
		};

        //hiding msgbox
		$('.signInMsgBox').addClass('hidden');


		gapi.client.userEndpoint.create(requestObject).execute(function(resp) {
			if (resp != null) {
				if(resp.status === 'SUCCESS'){
					$("div.signInMsgBox").removeClass('hidden').text(resp.message);
					window.location.href = 'login.jsp';

				}else{
					$("div.signInMsgBox").removeClass('hidden').text(resp.message);
					alert(resp.message);
				}
			}
		});
	}


	function signInEmailUser() {
		var requestObject = {
			email : document.getElementById("email").value,
			password : document.getElementById("password").value
		};

		gapi.client.userEndpoint.create(requestObject).execute(function(resp) {
			if (resp != null) {
				if(resp.status === 'SUCCESS'){
					//$("div.signInMsgBox").removeClass('hidden').text(resp.message);
					window.location.href = 'login.jsp';

				}else{
					//$("div.signInMsgBox").removeClass('hidden').text(resp.message);
					alert(resp.message);
				}
			}
		});
	}


    //forget password
    $(document).on('click','.forgetPasswordBtn', function(event){
		event.preventDefault();

		var email = document.getElementById("emailForgetPassword").value;

        if(email && validateEmail(email)){
            forgetPassword(email);
        }
	});


    //** logInUser (email) **/
    $(document).on('click','.loginBtn', function(event){
		event.preventDefault();

		var isValid = $("#logInUserForm").valid();

		if(isValid) {
		    logInUser();
		}
	});


    $("#logInUserForm").validate({
		rules : {
			emailLogin: {
				required: true,
				email: true
			},
			passwordLogin: {
			    required: true,
			    minlength: 6
			}
		},
		messages: {
		    emailLogin: {
		        required: "Email is required."
		    },
            passwordLogin: {
                required: "Password is required.",
                minlength: "Password must be at least 6 characters."
            }
		}
	});


	function logInUser() {
		var requestObject = {
			email : $("#loginUserEmail").val(),
			password : $("#loginUserPassword").val()
		};

        //hiding msgbox
		$('.logInUserMsgBox').addClass('hidden');

		//validating user log in
		gapi.client.userEndpoint.login(requestObject).execute(function(resp) {
			if (resp != null) {
				if(resp.status === 'SUCCESS'){
					window.location.href = '/user/dashboard';
				}else{
					$("div.logInUserMsgBox").removeClass('hidden').text(resp.message);
				}
			}
		});
	}


	//forget password
	function forgetPassword(email) {
        var requestObject = {
    	    email : email
    	};

    	//$('.successMessageForgetPassword').addClass('hidden');
    	//$('.errorMessageForgetPassword').addClass('hidden');
    	gapi.client.userEndpoint.forgetpassword(requestObject).execute(function(resp) {
    	    if (resp != null) {
    			if(resp.status === 'SUCCESS'){
    				//$('.successMessageForgetPassword').removeClass('hidden').text(resp.message);
    				//$('#resetPasswordEmail').val('');
    				//setTimeout(function(){
    				//	window.location.href = '/login.html';
    				//}, 5000);
    				alert(resp.message);
    			}else{
    				//$('.errorMessageForgetPassword').removeClass('hidden').text(resp.message);
    				alert(resp.message);
    			}
    		}
    	});
    }
});