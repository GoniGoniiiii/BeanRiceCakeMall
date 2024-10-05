document.addEventListener("DOMContentLoaded", function () {
    var rightContent = document.querySelector(".right-content");
    var orderProduct = document.querySelector(".order-product");

    // order-product 요소를 만났을 때 오른쪽 박스의 바닥이 보이게 함
    orderProduct.addEventListener("mouseenter", function () {
        // 오른쪽 박스의 scrollTop을 최대값으로 설정하여 바닥이 보이게 함
        rightContent.scrollTop = rightContent.scrollHeight;
    });

    //주문자 정보
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

    //배송자 정보
    var delivery_select = document.getElementById("select_deliveryEmail");
    var delivery_input = document.getElementById("input_deliveryEmail");


    delivery_select.addEventListener("change", function () {
        var selectValue = delivery_select.value;

        if (selectValue === '직접 입력') {
            delivery_input.removeAttribute('readonly');
            delivery_input.focus(); // 포커스를 input 요소로 이동
            delivery_input.value = ''; // 내용을 빈 문자열로 설정하여 내용을 지움
        } else {
            delivery_input.setAttribute('readonly', 'readonly');
            delivery_input.value = selectValue;
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

    //포인트 적립
    const price = document.getElementById("final-price").innerText;
    const point = Number(price);

    const plus_point = document.getElementById("plus_point");
    plus_point.value = Math.round(point * 0.03);

    // document.getElementById("point").innerText = "적립예정 " + plus_point.value + "원";
    document.getElementById("point").innerText = plus_point.value;

    //포인트 사용
    const user_point = document.getElementById("user_point");
    const final_price = document.getElementById("final-price");
    const add_sale = document.getElementById("add_sale");
    const maxPoint = document.getElementById("pointAll").value;
    const oprice = Number(final_price.innerText);
    const sale_price = document.getElementById("sale_price");

    user_point.addEventListener('change', function () {
        console.log("user_point : " + user_point.value);
        console.log("allPoint:" + maxPoint);

        const userPointValue = Number(user_point.value);
        console.log("finalValue :  " + oprice);
        console.log("user_point : " + userPointValue);
        console.log("allPoint: " + maxPoint);

        if (isNaN(userPointValue)) {
            alert("숫자만 입력해주세요!");
            user_point.value = "";
            user_point.focus();
        } else if (userPointValue > maxPoint) {
            alert("최대 사용할 수 있는 포인트를 초과했습니다!");
            user_point.value = "";
            user_point.focus();
        } else if (oprice< userPointValue) {
            alert("결제금액보다 포인트 사용금액이 더 큽니다!");
            user_point.value="";
            user_point.focus();
        } else {
            const finalPrice = oprice - userPointValue;
            // final-price 값을 업데이트
            final_price.innerText = finalPrice;
            add_sale.innerText = userPointValue;
            sale_price.svalue = userPointValue;
        }
    })

//수량 * 상품금액 계산
    var count = document.querySelectorAll('tr[data-index]').length;
    console.log("count : " + count);

    for (var index = 0; index < count; index++) {
        var priceElement = document.getElementById('product_sprice_' + index);
        var countElement = document.getElementById('order_cnt' + index);
        var resultElement = document.getElementById('order_oprice' + index); // 각 제품의 결과를 표시할 span
        var hiddenElement = document.getElementById('hidden_order_oprice_' + index);

        if (priceElement && countElement) {
            //상품 실제판매가
            var productPrice = parseInt(priceElement.textContent.replace('원', '').trim());
            console.log("상품 가격 :" + productPrice);
            var orderCount = parseInt(countElement.textContent);
            console.log("상품 수량 :" + productPrice);

            var totalPrice = productPrice * orderCount;

            resultElement.textContent = totalPrice + '원';
            hiddenElement.value = totalPrice;
        } else {
            console.log('요소를 찾을 수 없습니다. 인덱스:', index);
        }
    }
})
;


//위 정보와 동일 누르면 배송정보에 주문자 정보 입력
function Check() {
    var checkbox = document.getElementById("myCheckbox");
    if (checkbox.checked) { //체크박스에 체크가 되면 주문정보칸에 있는 이름,연락처 저장
        var order_userName = document.getElementById("order_userName").value;

        var p_num1 = document.getElementById("p_num1").value;
        var p_num2 = document.getElementById("p_num2").value;
        var p_num3 = document.getElementById("p_num3").value;

        var emailId = document.getElementById("user_emailId").value;
        var domain = document.getElementById("input_userEmail").value;

        //배송정보칸에 출력
        var userName = document.getElementById("delivery_userName");
        var d_emailId = document.getElementById("delivery_emailId");
        var d_domain = document.getElementById("input_deliveryEmail");
        var p_num4 = document.getElementById("p_num4");
        var p_num5 = document.getElementById("p_num5");
        var p_num6 = document.getElementById("p_num6");

        userName.value = order_userName;
        d_emailId.value = emailId;
        d_domain.value = domain;
        p_num4.value = p_num1;
        p_num5.value = p_num2;
        p_num6.value = p_num3;

        console.log(p_num4.value);
        console.log(p_num5.value);
        console.log(p_num6.value);
    } else { //uncheck시 칸 비우기
        document.getElementById("delivery_userName").value = "";
        document.getElementById("delivery_emailId").value = "";
        document.getElementById("input_deliveryEmail").value = "";
        document.getElementById("p_num4").value = "";
        document.getElementById("p_num5").value = "";
        document.getElementById("p_num6").value = "";
    }
}

function useAllPoint() {
    var pointCheck = document.getElementById("user_allPoint");
    var user_point = document.getElementById("user_point");
    var allPoint = document.getElementById("pointAll").value;

    user_point.value = pointCheck.checked ? allPoint : 0; // 체크 여부에 따라 값 설정

    // 변경 이벤트 트리거
    var event = new Event('change');
    user_point.dispatchEvent(event);
}

function uncheckOthers(clickedId) {
    var checkboxes = document.getElementsByName('order_paymethod');
    checkboxes.forEach(function (checkbox) {
        if (checkbox.id !== clickedId) {
            checkbox.checked = false;
        }
    });
}

document.getElementById('order_paymethod1').onchange = function () {
    uncheckOthers('order_paymethod1');
    console.log(document.getElementById('order_paymethod1').value);
};

document.getElementById('order_paymethod2').onchange = function () {
    uncheckOthers('order_paymethod2');
    console.log(document.getElementById('order_paymethod2').value);
};

document.getElementById('order_paymethod3').onchange = function () {
    uncheckOthers('order_paymethod3');
    console.log(document.getElementById('order_paymethod3').value);
};

document.getElementById('order_paymethod4').onchange = function () {
    uncheckOthers('order_paymethod4');
    console.log(document.getElementById('order_paymethod4').value);
};


// 전화번호 및 생년월일, 이메일 분리 함수
function split() {
    var emailId = document.getElementById('user_emailId');
    var email = document.getElementById('input_userEmail');
    var p_num1 = document.getElementById('p_num1');
    var p_num2 = document.getElementById('p_num2');
    var p_num3 = document.getElementById('p_num3');

    //이메일을 통째로 받아서 @를 기준으로 분리해줌
    var emailValue = emailId.value;
    var emailParts = emailValue.split("@");

    emailId.value = emailParts[0];
    email.value = emailParts[1];

    //전화번호 전체를 받아서 - 기준으로 분리
    var pValue = p_num1.value;
    var pParts = pValue.split("-");

    p_num1.value = pParts[0];
    p_num2.value = pParts[1];
    p_num3.value = pParts[2];

}

//order_price값 담아주기
function updateOrderPrice() {
    // final-price 요소 가져오기
    var finalPriceElement = document.getElementById('final-price');
    var hiddenInput = document.getElementById('order_price');

    if (finalPriceElement && hiddenInput) {
        // final-price의 textContent를 가져와서 hidden input의 value로 설정
        var finalPriceValue = finalPriceElement.innerText.replace('원', '').trim(); // '원' 제거 및 공백 제거
        hiddenInput.value = finalPriceValue; // hidden input에 값 설정
    }
}

function formatNumber(number) {
    // 숫자를 문자열로 변환하고 정규식을 사용하여 3자리마다 쉼표 추가
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

//숫자값 3자리마다 , 찍어주기
function formatPrices() {
    const priceElements = document.querySelectorAll(".order-price");
    const pointInput = document.getElementById("pointAll");

    priceElements.forEach(element => {
        const price = parseInt(element.textContent.replace(/[^0-9]/g, ''));
        if (!isNaN(price)) {
            element.textContent = formatNumber(price) + '원';
        }
    });

    if (pointInput) {
        const pointValue = parseInt(pointInput.value.replace(/[^0-9]/g, ''));
        if (!isNaN(pointValue)) {
            pointInput.value = formatNumber(pointValue);
        }
    }
}

// 페이지 로드 시 split() 함수 호출
window.onload = function () {
    split();
    updateOrderPrice();
    formatPrices();
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

// 폼 요소 가져오기
var form = document.querySelector("form[name='payment']");

//폼 제출시 실행할 함수
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

    //주문자 정보 이메일
    var emailId = document.getElementById("user_emailId").value;
    var domain = document.getElementById("input_userEmail").value;

    //배송자 정보 이메일
    var d_emailId = document.getElementById("delivery_emailId").value;
    var d_domain = document.getElementById("input_deliveryEmail").value;

    var email = emailId + "@" + domain;
    var d_email = d_emailId + "@" + d_domain;

    // 전화번호 값 합치기
    var telNumber1 = p_num1 + "-" + p_num2 + "-" + p_num3;
    var telNumber2 = p_num4 + "-" + p_num5 + "-" + p_num6;
    var telNumber3 = p_num7 + "-" + p_num8 + "-" + p_num9;

    // 숨겨진 입력 요소에 값 설정
    document.getElementById("user_tel").value = telNumber1;
    document.getElementById("user_email").value = email;
    document.getElementById("delivery_email").value = d_email;
    document.getElementById("order_deliveryTel1").value = telNumber2;

    if (p_num7 && p_num8 && p_num9) {
        document.getElementById("order_deliveryTel2").value = telNumber3;
    }

    console.log("주문자 연락처 :" + telNumber1);
    console.log("배송지 연락처 :" + telNumber2);
    console.log("배송지 연락처 :" + telNumber3);

    const salePrice = document.getElementById("sale_price").value;
    if (salePrice != null || salePrice === '') {
        document.getElementById("sale_price").value = 0;
    }
}


// 폼 제출 이벤트에 핸들러 등록
form.addEventListener("submit", handleSubmit);
