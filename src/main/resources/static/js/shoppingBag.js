document.addEventListener('DOMContentLoaded', function () {
    const minusButtons = document.querySelectorAll('.minus');
    const plusButtons = document.querySelectorAll('.plus');
    const quantityInputs = document.querySelectorAll('.quantity-input');
    const applyButtons = document.querySelectorAll('.apply-btn');
    const allChoose = document.getElementById('All-choose');
    const itemCheckboxes = document.querySelectorAll('.item-checkbox');
    const productPrices = document.querySelectorAll('.product-sprice');
    const totalPriceElement = document.getElementById('total-price');
    const finalPrice = document.getElementById('final-price');

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
            updateDeliveryFee(); // 배송비 업데이트
            updateFinalPrice();//총 결제금액 업데이트
        });
    });

    // 개별 상품 가격 업데이트 함수
    function updatePrice(index) {
        let quantity = parseInt(quantityInputs[index].value);
        let priceElement = productPrices[index];
        let pricePerItem = parseInt(priceElement.getAttribute("data-value"));
        priceElement.textContent = (pricePerItem * quantity).toLocaleString() + '원';
        console.log(pricePerItem * quantity);
        console.log("현재수량 : " + quantity);
    }

// 총합계 금액 업데이트 함수
    function updateTotalPrice() {
        let totalPrice = 0;
        productPrices.forEach((priceElement, index) => {
            const pricePerItem = parseInt(priceElement.getAttribute("data-value"));
            const quantity = parseInt(quantityInputs[index].value);
            totalPrice += pricePerItem * quantity;
        });
        console.log(totalPrice);
        totalPriceElement.textContent = totalPrice.toLocaleString() + '원';
    }

// 배송비 업데이트 함수
    function updateDeliveryFee() {
        let totalDeliveryFee = 0;

        // 배송비를 가진 모든 요소를 선택
        const deliveryFeeElements = document.querySelectorAll('.delivery-fee');

        // 각 요소의 배송비를 더함
        deliveryFeeElements.forEach((element) => {
            const feePerItem = parseInt(element.getAttribute("data-fee")) || 0;
            totalDeliveryFee += feePerItem;
        });

        // 계산된 총 배송비를 id="delivery-fee"에 표시
        const deliveryFeeElement = document.getElementById('delivery-fee');
        deliveryFeeElement.textContent = totalDeliveryFee.toLocaleString() + '원';

        console.log(totalDeliveryFee);
    }

    function updateFinalPrice() {
        let totalElement = document.getElementById('total-price');
        let deliveryFeeElement = document.getElementById('delivery-fee');

        // 텍스트 콘텐츠에서 숫자를 추출
        let total = parseInt(totalElement.textContent.replace(/[^0-9]/g, ''), 10);
        let deliveryFee = parseInt(deliveryFeeElement.textContent.replace(/[^0-9]/g, ''), 10);
        let finalPrice=0;

        finalPrice = total + deliveryFee;
        const finalElement = document.getElementById('final-price');
        finalElement.textContent = finalPrice.toLocaleString() + '원';

    }

    // 페이지 로드시 초기 총합계 금액 표시
    updateTotalPrice(); // 페이지 로드 시 총합계 초기화
    updateDeliveryFee(); // 배송비 업데이트
    updateFinalPrice(); //결제금액 업데이트

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

    fetch('/cart/delete', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }, body: JSON.stringify(
            {
                product_num: product_num,
                user_num: user_num
            })
    }).then(response => response.text())
}