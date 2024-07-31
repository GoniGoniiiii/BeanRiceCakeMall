// 비밀번호 유효성 검사
var user_pw = document.getElementById("user_pw");
var pwMessage = document.getElementById("pwMessage");

user_pw.addEventListener("change", function () {
    var pw = user_pw.value;
    var minLength = 8;
    var hasUpperCase = /[A-Z]/.test(pw);
    var hasLowerCase = /[a-z]/.test(pw);
    var hasDigit = /\d/.test(pw);
    var hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(pw);

    if (pw) {
        pwMessage.style.display = 'block';
    } else {
        pwMessage.style.display = 'none';
    }

    if (pw.length >= minLength &&
        hasUpperCase &&
        hasLowerCase &&
        hasDigit &&
        hasSpecialChar) {
        pwMessage.innerHTML = '비밀번호 형식이 올바릅니다!';
        pwMessage.style.color = 'green';
    } else {
        pwMessage.innerHTML = '비밀번호가 형식에 맞지 않습니다.';
        pwMessage.style.color = 'red';
    }
});

// 비밀번호 확인
var user_pw2 = document.getElementById("user_pw2");
var checkMsg = document.getElementById("checkMessage");

user_pw2.addEventListener("change", function () {
    var pw = user_pw.value;
    var pwCheck = user_pw2.value;

    if (pwCheck) {
        checkMsg.style.display = 'block';
    } else {
        checkMsg.style.display = 'none';
    }

    if (pw === pwCheck) {
        checkMsg.innerHTML = '비밀번호가 같습니다!';
        checkMsg.style.color = 'green';
    } else {
        checkMsg.innerHTML = '비밀번호가 다릅니다!';
        checkMsg.style.color = 'red';
    }
});

// 이메일 직접 입력 또는 선택
var select = document.getElementById("select_userEmail");
var input = document.getElementById("input_userEmail");

select.addEventListener("change", function () {
    var selectValue = select.value;

    if (selectValue === '직접 입력') {
        input.removeAttribute('readonly');
        input.focus();
        input.value = '';
    } else {
        input.setAttribute('readonly', 'readonly');
        input.value = selectValue;
    }
});

// 전화번호 체크
var p_num1 = document.getElementById("p_num1");
var p_num2 = document.getElementById("p_num2");
var p_num3 = document.getElementById("p_num3");

function validatePhoneNumber(input, minLength, maxLength) {
    var value = input.value;
    if (isNaN(value)) {
        input.focus();
        input.value = '';
        alert("숫자만 입력 가능합니다!");
        return false;
    } else if (value.length < minLength || value.length > maxLength) {
        input.focus();
        alert("번호를 올바르게 입력하세요!");
        return false;
    }
    return true;
}

p_num1.addEventListener("change", function () {
    validatePhoneNumber(p_num1, 2, 4);
});

p_num2.addEventListener("change", function () {
    validatePhoneNumber(p_num2, 4, 4);
});

p_num3.addEventListener("change", function () {
    validatePhoneNumber(p_num3, 4, 4);
});

// 회원 탈퇴
function unRegister() {
    var confirmation = confirm("정말 회원탈퇴를 하시겠습니까?");
    if (confirmation) {
        var password = prompt("비밀번호를 입력하세요");
        var user_pw = 'Qq1234567!';
        if (password !== null && password === user_pw) {
            alert("회원탈퇴가 완료되었습니다.");
        } else {
            alert("비밀번호가 틀렸습니다. 다시 시도해주세요.");
        }
    } else {
        alert("회원탈퇴가 취소되었습니다.");
    }
}

// 전화번호 및 생년월일, 이메일 분리 함수
function split() {
    var emailId = document.getElementById('user_emailId');
    var email = document.getElementById('input_userEmail');
    var p_num1 = document.getElementById('p_num1');
    var p_num2 = document.getElementById('p_num2');
    var p_num3 = document.getElementById('p_num3');
    var year = document.getElementById('year');
    var month = document.getElementById('month');
    var day = document.getElementById('day');

    var emailValue = emailId.value;
    emailId.value = emailValue.split("@")[0];
    email.value = emailValue.split("@")[1];

    var pValue = p_num1.value;
    if (pValue) {
        p_num1.value = pValue.split("-")[0];
        p_num2.value = pValue.split("-")[1];
        p_num3.value = pValue.split("-")[2];
    }

    var bValue = year.value;
    if (bValue) {
        year.value = bValue.split("-")[0];
        month.value = bValue.split("-")[1];
        day.value = bValue.split("-")[2];
    }
}
// 페이지 로드 시 split() 함수 호출
window.onload = function () {
    split();
}
function zipcode() {
    new daum.Postcode({
        oncomplete: function (data) {
// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

// 각 주소의 노출 규칙에 따라 주소를 조합한다.
// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수

//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

// 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('user_zipcode').value = data.zonecode;
            document.getElementById('user_address').value = addr;
// 커서를 상세주소 필드로 이동한다.
            document.getElementById('user_addrDetail').focus();
        }
    }).open();
}

// 폼 제출 시 값 조합
var form = document.querySelector("form[name='info']");

function handleSubmit(event) {
    event.preventDefault();

    var p_num1 = document.getElementById("p_num1").value;
    var p_num2 = document.getElementById("p_num2").value;
    var p_num3 = document.getElementById("p_num3").value;
    var year = document.getElementById("year").value;
    var month = document.getElementById("month").value;
    var day = document.getElementById("day").value;
    var emailId = document.getElementById("user_emailId").value;
    var domain = document.getElementById("input_userEmail").value;

    var telNumber1 = p_num1 + "-" + p_num2 + "-" + p_num3;
    var birth = year + "-" + month + "-" + day;
    var email = emailId + "@" + domain;

    document.getElementById("user_tel").value = telNumber1;
    document.getElementById("user_birth").value = birth;
    document.getElementById("user_email").value = email;

    form.submit();
}

// 폼 제출 이벤트에 핸들러 등록
form.addEventListener("submit", handleSubmit);

