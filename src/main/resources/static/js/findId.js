document.addEventListener("DOMContentLoaded", function () {
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

    selectValue.addEventListener("change", function () {
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
// 폼 요소 가져오기
var form = document.querySelector("form[name='findId']");

// // 폼 제출 시 실행할 함수
// function handleSubmit(event) {
//     event.preventDefault();
//     // 입력 요소의 값 가져오기
//     //전화번호 합치기
//     var p_num1 = document.getElementById("p_num1").value;
//     var p_num2 = document.getElementById("p_num2").value;
//     var p_num3 = document.getElementById("p_num3").value;
//
//     // 변수에 저장 시켜주기
//     var telNumber = p_num1 + "-" + p_num2 + "-" + p_num3;
//     console.log(telNumber);
//
//     // 숨겨진 입력 요소에 값 설정
//     document.getElementById("user_tel").value = telNumber;
//
//     form.submit();
// }
//
// // 폼 제출 이벤트에 핸들러 등록
// form.addEventListener("submit", handleSubmit);

function goLogin() {
    window.location = "/login";
}

function search() {

    var userName = document.getElementById("user_name").value;
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

    var data = {
        user_name: userName,
        user_email: userEmail,
        user_tel: userTel
    };

    fetch('/user/findId', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.text())
        .then(data => {
            document.getElementById("user_id").textContent = data;
            var modal = new bootstrap.Modal(document.getElementById('findIdModal'));
            modal.show();
        })
        .catch(error => {
            //에러처리
            console.error('Error : ' + error);
        })
}
