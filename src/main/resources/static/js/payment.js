document.addEventListener("DOMContentLoaded", function () {
    var rightContent = document.querySelector(".right-content");
    var orderProduct = document.querySelector(".order-product");
    ``

    // order-product 요소를 만났을 때 오른쪽 박스의 바닥이 보이게 함
    orderProduct.addEventListener("mouseenter", function () {
        // 오른쪽 박스의 scrollTop을 최대값으로 설정하여 바닥이 보이게 함
        rightContent.scrollTop = rightContent.scrollHeight;
    });

    var select = document.getElementById("select_userEmail");
    var input = document.getElementById("input_userEmail");

    select.addEventListener("change", function () {
        var selectValue = select.value;

        if (selectValue === '직접 입력') {
            input.removeAttribute('readonly');
            input.focus(); // 포커스를 input 요소로 이동
            input.value = ''; // 내용을 빈 문자열로 설정하여 내용을 지움
        } else {
            input.setAttribute('readonly', 'readonly');
            input.value = selectValue;
        }
    })


    //전화번호 체크
    var p_num1 = document.getElementById("p_num1");
    var p_num2 = document.getElementById("p_num2");
    var p_num3 = document.getElementById("p_num3");
    var p_num4 = document.getElementById("p_num4");
    var p_num5 = document.getElementById("p_num5");
    var p_num6 = document.getElementById("p_num6");
    var p_num7 = document.getElementById("p_num7");
    var p_num8 = document.getElementById("p_num8");
    var p_num9 = document.getElementById("p_num9");

    function validatePhoneNumber(input, minLength, maxLength) {
        var value = input.value.trim();

        console.log(value);
        if (isNaN(value)) { // 숫자가 아닌 경우
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
    p_num4.addEventListener("change", function () {
        validatePhoneNumber(p_num4, 2, 4);
    });

    p_num5.addEventListener("change", function () {
        validatePhoneNumber(p_num5, 4, 4);
    });

    p_num6.addEventListener("change", function () {
        validatePhoneNumber(p_num6, 4, 4);
    });
    p_num7.addEventListener("change", function () {
        validatePhoneNumber(p_num7, 2, 4);
    });

    p_num8.addEventListener("change", function () {
        validatePhoneNumber(p_num8, 4, 4);
    });

    p_num9.addEventListener("change", function () {
        validatePhoneNumber(p_num9, 4, 4);
    });

});

// 폼 요소 가져오기
var form = document.querySelector("form[name='payment']");

// 폼 제출 시 실행할 함수
function handleSubmit(event) {
    // 입력 요소의 값 가져오기
    var p_num1 = document.getElementById("p_num1").value;
    var p_num2 = document.getElementById("p_num2").value;
    var p_num3 = document.getElementById("p_num3").value;
    var p_num4 = document.getElementById("p_num4").value;
    var p_num5 = document.getElementById("p_num5").value;
    var p_num6 = document.getElementById("p_num6").value;
    var p_num7 = document.getElementById("p_num7").value;
    var p_num8 = document.getElementById("p_num8").value;
    var p_num9 = document.getElementById("p_num9").value;

    // 전화번호 값 합치기
    var telNumber1 = p_num1 + p_num2 + p_num3;
    var telNumber2 = p_num4 + p_num5 + p_num6;
    var telNumber3 = p_num7 + p_num8 + p_num9;

    // 숨겨진 입력 요소에 값 설정
    document.getElementById("order_userTel").value = telNumber1;
    document.getElementById("order_deliveryTel1").value = telNumber2;
    if (p_num7 && p_num8 && p_num9) {
        document.getElementById("order_deliveryTel2").value = telNumber3;
    }
    console.log("주문자 연락처 :" + telNumber1);
    console.log("배송지 연락처 :" + telNumber2);
    console.log("배송지 연락처 :" + telNumber3);
}

// 폼 제출 이벤트에 핸들러 등록
form.addEventListener("submit", handleSubmit);

//위 정보와 동일 누르면 배송정보에 주문자 정보 입력
function Check() {
    var checkbox = document.getElementById("myCheckbox");
    if (checkbox.checked) { //체크박스에 체크가 되면 주문정보칸에 있는 이름,연락처 저장
        var order_userName = document.getElementById("order_userName").value;

        var p_num1 = document.getElementById("p_num1").value;
        var p_num2 = document.getElementById("p_num2").value;
        var p_num3 = document.getElementById("p_num3").value;

        //배송정보칸에 출력
        var userName = document.getElementById("delivery_userName");
        var p_num4 = document.getElementById("p_num4");
        var p_num5 = document.getElementById("p_num5");
        var p_num6 = document.getElementById("p_num6");

        userName.value = order_userName;
        p_num4.value = p_num1;
        p_num5.value = p_num2;
        p_num6.value = p_num3;

        console.log(p_num4.value);
        console.log(p_num5.value);
        console.log(p_num6.value);
    }
}

function useAllPoint() {
    var pointCheck = document.getElementById("user_allPoint");

    if (pointCheck.checked) {
        var allPoint = document.getElementById("pointAll").value;
        console.log(allPoint);

        var user_point = document.getElementById("user_point");
        user_point.value = allPoint;

    }
}
    function uncheckOthers(clickedId) {
        var checkboxes = document.getElementsByName('order_paymethod');
        checkboxes.forEach(function(checkbox) {
            if (checkbox.id !== clickedId) {
                checkbox.checked = false;
            }
        });
    }

    document.getElementById('order_paymethod1').onchange = function() {
        uncheckOthers('order_paymethod1');
        console.log(document.getElementById('order_paymethod1').value);
    };

    document.getElementById('order_paymethod2').onchange = function() {
        uncheckOthers('order_paymethod2');
        console.log(document.getElementById('order_paymethod2').value);
    };

    document.getElementById('order_paymethod3').onchange = function() {
        uncheckOthers('order_paymethod3');
        console.log(document.getElementById('order_paymethod3').value);
    };

    document.getElementById('order_paymethod4').onchange = function() {
        uncheckOthers('order_paymethod4');
        console.log(document.getElementById('order_paymethod4').value);
    };






