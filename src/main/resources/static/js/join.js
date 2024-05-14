//아이디 중복체크
var user_id=document.getElementById("user_id");
var idMsg=document.getElementById("idMessage");

user_id.addEventListener("change",function(){
    var id=user_id.value;

    if(id!==''&& id!=='null'){
        idMsg.innerHTML = id+'는 사용 가능한 아이디입니다!';
        idMsg.style.display='block';
        console.log("id중복확인 결과 사용가능한 아이디입니다.");
    }
})

//비밀번호 형식에 맞는지
var user_pw=document.getElementById("user_pw");
var pwMessage=document.getElementById("pwMessage");

user_pw.addEventListener("change",function(){
    var pw=user_pw.value;
    // 비밀번호 유효성 검사 조건 설정(최소 8자리 이상, 대문자,소문자,숫자,특수문자 포함)
    var minLength = 8;
    var hasUpperCase = /[A-Z]/.test(pw);
    var hasLowerCase = /[a-z]/.test(pw);
    var hasDigit = /\d/.test(pw);
    var hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(pw);

    // 값이 들어오면 형식에 맞는지 알려줘!
    // 입력값이 있을 때 메시지를 보여주자~
    if (pw) {
        pwMessage.style.display = 'block';
    } else {
        pwMessage.style.display = 'none';
    }

    // 모든 조건을 만족하는지 확인
    if (pw.length >= minLength&&
        hasUpperCase &&
        hasLowerCase &&
        hasDigit &&
        hasSpecialChar) {
        console.log("비밀번호 유효성 검사 성공"+pw);
        pwMessage.innerHTML = '비밀번호 형식이 올바릅니다!';
        pwMessage.style.color = 'green';
    } else {
        console.log('비밀번호 유효성 검사 실패'+pw);
        pwMessage.innerHTML = '비밀번호가 형식에 맞지 않습니다.';
        pwMessage.style.color = 'red';
    }
})


//비밀번호 비밀번호 확인 값이 같은지 체크
var user_pw2=document.getElementById("user_pw2");
var checkMsg=document.getElementById("checkMessage");

user_pw2.addEventListener("change",function(){
    var pw=user_pw.value;
    var pwCheck=user_pw2.value;

    //pwCheck에 값이 들어오 checkMsg띄워줘~~~
    if (pwCheck) {
        checkMsg.style.display = 'block';
    } else {
        checkMsg.style.display = 'none';
    }

    if (pw === pwCheck) {
        console.log("비밀번호를 잘 입력했구나 축하한다.");
        checkMsg.innerHTML = '비밀번호가 같습니다!';
        checkMsg.style.color = 'green';
    } else {
        console.log("비밀번호가 틀렸다. 똑바로 해봐라.");
        checkMsg.innerHTML = '비밀번호가 다릅니다!'
        checkMsg.style.color = 'red';
    }

})


//email 직접입력.선택
var select=document.getElementById("select_userEmail");
var input=document.getElementById("input_userEmail");

select.addEventListener("change",function(){
    var selectValue=select.value;

    if(selectValue==='직접 입력'){
        input.removeAttribute('readonly');
        input.focus(); // 포커스를 input 요소로 이동
        input.value = ''; // 내용을 빈 문자열로 설정하여 내용을 지움
    }else{
        input.setAttribute('readonly', 'readonly');
        input.value=selectValue;
    }
})

//전화번호 체크
var p_num1=document.getElementById("p_num1");
var p_num2=document.getElementById("p_num2");
var p_num3=document.getElementById("p_num3");

function validatePhoneNumber(input, minLength,maxLength) {
    var value = input.value;
    if (isNaN(value)) { // 숫자가 아닌 경우
        input.focus();
        input.value = '';
        alert("숫자만 입력 가능합니다!");
        return false;
    } else if (value.length < minLength || value.length>maxLength) {
        input.focus();
        alert("번호를 올바르게 입력하세요!");
        return false;
    }
    return true;
}

p_num1.addEventListener("change", function() {
    validatePhoneNumber(p_num1, 2,4);
});

p_num2.addEventListener("change", function() {
    validatePhoneNumber(p_num2, 4,4);
});

p_num3.addEventListener("change", function() {
    validatePhoneNumber(p_num3, 4,4);
});

//생년월일 체크
var birth_year=document.getElementById('year');
var birth_month=document.getElementById('month');
var birth_day=document.getElementById('day');

function validateBirth(input, min, max, errorMessage) {
    var value = parseInt(input.value);
    if (isNaN(value) || value < min || value > max) {
        input.focus();
        input.value = '';
        alert(errorMessage);
        return false;
    }
    return true;
}

birth_year.addEventListener("change", function() {
    validateBirth(birth_year, 1900, 2024, "년도를 다시 확인해주세요");
});

birth_month.addEventListener("change", function() {
    validateBirth(birth_month, 1, 12, "월을 다시 확인해주세요");
});

birth_day.addEventListener("change", function() {
    validateBirth(birth_day, 1, 31, "일을 다시 확인해주세요");
});

//전체동의 버튼 누르면 두개 다 동의 눌러지게
function Agree() {
    var agree1 = document.getElementById('join-agree1');
    var agree2 = document.getElementById('join-agree2');
    var allAgree = document.getElementById('join-allAgree');

    agree1.checked = !agree1.checked;
    agree2.checked = !agree2.checked;
    allAgree.checked = agree1.checked && agree2.checked;
}

// 폼 요소 가져오기
var form = document.querySelector("form[name='join']");

// 폼 제출 시 실행할 함수
function handleSubmit(event) {
    event.preventDefault();
    // 입력 요소의 값 가져오기
    //전화번호 합치기
    var p_num1 = document.getElementById("p_num1").value;
    var p_num2 = document.getElementById("p_num2").value;
    var p_num3 = document.getElementById("p_num3").value;

    //생년월일 합치기
    var year=document.getElementById("year").value;
    var month=document.getElementById("month").value;
    var day=document.getElementById("day").value;

    //이메일 합치기
    var emailId=document.getElementById("user_emailId").value;
    var domain=document.getElementById("input_userEmail").value;

    // 변수에 저장 시켜주기
    var telNumber1 = p_num1 +"-"+ p_num2 +"-"+ p_num3;
    var birth=year+"-"+month+"-"+day;
    var email=emailId+"@"+domain;
    console.log(telNumber1);
    console.log(birth);
    console.log(email);

    // 숨겨진 입력 요소에 값 설정
    document.getElementById("user_tel").value = telNumber1;
    document.getElementById("user_birth").value=birth;
    document.getElementById("user_email").value=email;

    form.submit();
}

// 폼 제출 이벤트에 핸들러 등록
form.addEventListener("submit", handleSubmit);