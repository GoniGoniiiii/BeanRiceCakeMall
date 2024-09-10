document.addEventListener("DOMContentLoaded", function() {
    var selectValue = document.getElementById("select-value");
    var tel = document.getElementById("tel");
    var email = document.getElementById("email");

    // 초기 상태 설정
    if (selectValue.value === 'email') {
        email.classList.remove("hidden");
        tel.classList.add("hidden");
    } else {
        email.classList.add("hidden");
        tel.classList.remove("hidden");
    }

    selectValue.addEventListener("change", function() {
        var select = selectValue.value;
        console.log("선택된 인증 방법 : " + select);

        if (select === 'tel') {
            console.log("전화번호가 선택됨");
            email.classList.add("hidden");
            tel.classList.remove("hidden");
        } else {
            console.log("이메일이 선택됨");
            email.classList.remove("hidden");
            tel.classList.add("hidden");
        }
    });
});


function goLogin() {
    window.location = "/login";
}

function redirect() {
    window.location = "/user/findPw";
}

function search() {

    var userName = document.getElementById("user_name").value;
    var userId=document.getElementById("user_id").value;
    var userEmail = document.getElementById("user_email").value;
    var p_num1 = document.getElementById("p_num1").value;
    var p_num2 = document.getElementById("p_num2").value;
    var p_num3 = document.getElementById("p_num3").value;

    var telNumber = p_num1 + "-" + p_num2 + "-" + p_num3;

    document.getElementById("user_tel").value = telNumber;

    var userTel = document.getElementById("user_tel").value;

    console.log(userName);
    console.log(userEmail);
    console.log(userTel);
    console.log(userId);

    var data = {
        user_name: userName,
        user_email: userEmail,
        user_tel: userTel,
        user_id:userId
    };

    fetch('/user/findPw', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.text())
        .then(data => {
            document.getElementById("user_pw").textContent = data;
            if (data !== '찾을 수 없음') {
                document.getElementById("message").textContent = "임시 비밀번호는 말그대로 임시비밀번호 이므로 필수 변경바랍니다!";
            }
            var modal = new bootstrap.Modal(document.getElementById('findPwModal'));
            modal.show();
        })
        .catch(error => {
            //에러처리
            console.error('Error : ' + error);
        })
}
