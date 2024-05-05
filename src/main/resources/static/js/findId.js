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
