document.addEventListener('DOMContentLoaded', function () {
    const minusButtons = document.querySelectorAll('.minus');
    const plusButtons = document.querySelectorAll('.plus');
    const quantityInputs = document.querySelectorAll('.quantity-input');
    const applyButtons = document.querySelectorAll('.apply-btn');
    const allChoose = document.getElementById('All-choose');
    const itemCheckboxes = document.querySelectorAll('.item-checkbox');
    const productPrices = document.querySelectorAll('.product-sprice');
    const totalPriceElement = document.getElementById('total-price');

    // 수량 감소 버튼
    minusButtons.forEach(function (minusButton, index) {
        minusButton.addEventListener('click', function (event) {
            event.preventDefault();
            let currentValue = parseInt(quantityInputs[index].value);
            if (currentValue > 1) {
                quantityInputs[index].value = currentValue - 1;
                updatePrice(index); // 개별 가격 업데이트
            }
        });
    });

    // 수량 증가 버튼
    plusButtons.forEach(function (plusButton, index) {
        plusButton.addEventListener('click', function (event) {
            event.preventDefault();
            let currentValue = parseInt(quantityInputs[index].value);
            quantityInputs[index].value = currentValue + 1;
            updatePrice(index); // 개별 가격 업데이트
        });
    });

    // 수량 적용 버튼
    applyButtons.forEach(function (applyButton, index) {
        applyButton.addEventListener('click', function (event) {
            event.preventDefault();
            updatePrice(index); // 개별 가격 업데이트
            updateTotalPrice(); // 총합계 업데이트
        });
    });

    // 개별 상품 가격 업데이트 함수
    function updatePrice(index) {
        let quantity = parseInt(quantityInputs[index].value);
        let priceElement = productPrices[index];
        let pricePerItem = parseInt(priceElement.getAttribute("data-value"));
        priceElement.textContent = (pricePerItem * quantity).toLocaleString() + '원';
        console.log("현재수량 : " + quantity);
    }

    // 총합계 금액 업데이트 함수
    function updateTotalPrice() {
        let totalPrice = 0;
        productPrices.forEach((priceElement) => {
            const price = parseInt(priceElement.getAttribute("data-value"));
            totalPrice += price;
        });
        totalPriceElement.textContent = totalPrice.toLocaleString() + '원';
    }

    // 페이지 로드시 초기 총합계 금액 표시
    updateTotalPrice(); // 페이지 로드 시 총합계 초기화

    // 전체 선택 체크박스
    allChoose.addEventListener('change', function () {
        const isChecked = allChoose.checked;
        itemCheckboxes.forEach(function (checkbox) {
            checkbox.checked = isChecked;
        });
    });
});

function goPayment() {
    console.log("호출됨");
    location.href = "/payment";
}

function cartDelete() {
    const product_num = document.getElementById("product_num").value;
    const user_num = document.getElementById("user_num").value;
    console.log(user_num, product_num);

    fetch('/cart/delete',{
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }, body: JSON.stringify(
            {
                product_num: product_num,
                user_num: user_num
            })
    }) .then(response => response.text())
}