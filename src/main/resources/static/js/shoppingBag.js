document.addEventListener('DOMContentLoaded', function () {
    //left-content와 right-content 설정
    var rightContent = document.querySelector(".right-content");
    var leftContent = document.querySelector(".left-content");

    // left-content 요소를 만났을 때 오른쪽 박스의 바닥이 보이게 함
    leftContent.addEventListener("mouseenter", function () {
        // 오른쪽 박스의 scrollTop을 최대값으로 설정하여 바닥이 보이게 함
        rightContent.scrollTop = rightContent.scrollHeight;
    });


    //수량관련 설정
    const minusButtons = document.querySelectorAll('.minus');
    const plusButtons = document.querySelectorAll('.plus');
    const quantityInputs = document.querySelectorAll('.quantity-input');
    const applyButtons = document.querySelectorAll('.apply-btn');
    const allChoose = document.getElementById('All-choose');
    const itemCheckboxes = document.querySelectorAll('.item-checkbox');
    const productPrices = document.querySelectorAll('.product-sprice');
    const productoPrices = document.querySelectorAll('.product-oprice');
    const deliveryFeeElements = document.querySelectorAll('.delivery-fee');
    const totalPriceElement = document.getElementById('total-price');
    const deliveryFeeElement = document.getElementById('delivery-fee');
    const finalPriceElement = document.getElementById('final-price');
    const totalSaleElement = document.getElementById('total-sale');

    // 각 상품의 가격을 초기화하는 함수
    function initializePrices() {
        quantityInputs.forEach((input, index) => {
            let quantity = parseInt(input.value);
            let priceElement = productPrices[index];
            let pricePerItem = parseInt(priceElement.getAttribute("data-value"));
            priceElement.textContent = (pricePerItem * quantity).toLocaleString() + '원';
        });
    }

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
            updateTotals(); // 총합계 업데이트
            cartUpdate(index);
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
    function updateTotals() {
        let totalPrice = 0;
        let totalDeliveryFee = 0;
        let totalOprice = 0;
        let totalSprice = 0;

        itemCheckboxes.forEach((checkbox, index) => {
            if (checkbox.checked) {
                let quantity = parseInt(quantityInputs[index].value);
                let priceElement = productPrices[index];
                let priceElement2 = productoPrices[index];
                let pricePerItem = parseInt(priceElement.getAttribute("data-value"));
                totalPrice += pricePerItem * quantity;

                // 배송비 계산
                let deliveryFee = parseInt(deliveryFeeElements[index].getAttribute("data-fee")) || 0;
                totalDeliveryFee += deliveryFee;

                // oprice와 sprice 계산
                let oprice = parseInt(priceElement2.getAttribute("data-oprice")) || 0;
                let sprice = parseInt(priceElement.getAttribute("data-sprice")) || 0;

                console.log(`Index ${index}:`);
                console.log(`oprice: ${oprice}, sprice: ${sprice}`);
                console.log(`Quantity: ${quantity}`);

                totalOprice += oprice * quantity;
                totalSprice += sprice * quantity;

                console.log("total: " + totalOprice + "  " + totalSprice);
            }
        });
        console.log(`Total oprice: ${totalOprice}`);
        console.log(`Total sprice: ${totalSprice}`);

        // 총 상품 금액 및 배송비 업데이트
        totalPriceElement.textContent = totalPrice.toLocaleString() + '원';
        deliveryFeeElement.textContent = totalDeliveryFee.toLocaleString() + '원';
        document.getElementById("total-price").value = totalPrice;
        document.getElementById("delivery-fee").value = totalDeliveryFee;
        document.getElementById("total-oprice").value = totalOprice;
        console.log("뿍빡", totalOprice);

        // 결제 금액 업데이트
        const finalPrice = totalPrice + totalDeliveryFee;
        finalPriceElement.textContent = finalPrice.toLocaleString() + '원';
        document.getElementById('final-price').value = finalPrice;

        // oprice와 sprice의 총합 차액 계산
        const totalSale = totalOprice - totalSprice;
        totalSaleElement.textContent = totalSale.toLocaleString() + '원';
        document.getElementById("total-sale").value = totalSale;
        console.log("세일된 금액 : ", totalSale);

        const what = document.getElementById("total-price").value;
        console.log("total_price: ", what);
    }

    // 초기화
    initializePrices();
    updateTotals(); // 페이지 로드 시 총합계 초기화

    // 전체 선택 체크박스
    allChoose.addEventListener('change', function () {
        itemCheckboxes.forEach((checkbox) => {
            checkbox.checked = allChoose.checked;
        });
        updateTotals(); // 전체 선택 시 총합계 업데이트
    });

    // 개별 체크박스 변경 시 총합계 업데이트
    itemCheckboxes.forEach((checkbox) => {
        checkbox.addEventListener('change', updateTotals);
    });


    // 결제하기 버튼 클릭 이벤트
    const submitButton = document.getElementById('submit_button');
    submitButton.addEventListener('click', function (event) {
        event.preventDefault();  // 폼 기본 제출 방지

        // 체크된 장바구니 아이템들 수집
        const checkedItems = document.querySelectorAll('.item-checkbox:checked');
        const orderData = [];

        // 아이템이 선택되지 않았을 때 경고 메시지 표시
        if (checkedItems.length === 0) {
            alert("상품이 선택되지 않았습니다. 주문하실 상품을 선택해주세요!");
            return;
        }

        checkedItems.forEach((item, index) => {
            const cartNum = item.value;
            const productNum = document.getElementById(`product_num[${index}]`).value;
            const cartCnt = document.getElementById(`cart_cnt[${index}]`).value;
            const userNum = document.getElementById('user_num').value;
            const totalOprice = document.getElementById('total-oprice').value;
            const totalSale = document.getElementById('total-sale').value;
            const deliveryFee = document.getElementById('delivery-fee').value;
            const finalPrice = document.getElementById('final-price').value;

            console.log(cartNum);

            orderData.push({
                cart_num: cartNum,
                product_num: productNum,
                cart_cnt: cartCnt,
                user_num: userNum,
                total_oprice: totalOprice,
                total_sale: totalSale,
                total_delivery: deliveryFee,
                total_price: finalPrice
            });
        });

        // 서버에 데이터 전송
        fetch('/my/payment', {
            method: 'POST', headers: {
                'Content-Type': 'application/json'
            }, body: JSON.stringify(orderData)
        })
            .then(response => response.text())
            .then(data => {
                console.log('Success:', data);
                // 성공적인 응답 후 결제 페이지로 리다이렉트
                window.location.href = "/payment";
            })
            .catch((error) => {
                console.error('Error:', error);
                alert('주문 처리 중 오류가 발생했습니다.');
            });
    });
});

//장바구니 상품 삭제
function cartDelete(index) {
    const product_name = document.getElementById(`product_name[${index}]`).value;

    if (confirm(product_name + "을(를) 삭제하시겠습니까?")) {
        const product_num = document.getElementById(`product_num[${index}]`).value;
        const user_num = document.getElementById("user_num").value;
        console.log(user_num, product_num);

        fetch('/cart/delete', {
            method: 'DELETE', headers: {
                'Content-Type': 'application/json'
            }, body: JSON.stringify({
                product_num: product_num, user_num: user_num
            })
        }).then(response => {
            if (response.ok) {
                // 요청이 성공했을 경우, DOM에서 해당 항목을 삭제
                const cartItem = document.getElementById(`cart-item[${index}]`);
                if (cartItem) {
                    cartItem.remove();
                }
                console.log("삭제 완료:", "user_num:", user_num, "product_num:", product_num);
            } else {
                console.error('삭제 실패');
            }
        }).catch(error => {
            console.error('요청 중 오류 발생:', error);
        });
    }
}

//장바구니 상품 수정(수량 수정)
function cartUpdate(index) {
    const product_num = document.getElementById(`product_num[${index}]`).value;
    const user_num = document.getElementById('user_num').value;
    const cart_num = document.getElementById(`cart_num[${index}]`).value;
    const product_img = document.getElementById(`product_img[${index}]`).value;
    const cart_cnt = document.getElementById(`cart_cnt[${index}]`).value;
    console.log("user_num: ", user_num, "product_num : ", product_num, "cart_num : ", cart_num, "product_img: ", product_img, "cart_cnt:", cart_cnt);

    fetch('/cart/update', {
        method: 'POST', headers: {
            'Content-Type': 'application/json'
        }, body: JSON.stringify({
            product_num: product_num,
            user_num: user_num,
            cart_num: cart_num,
            product_img: product_img,
            cart_cnt: cart_cnt
        })
    }).then(response => response.text())
        .then(data => {
            alert("수량이 성공적으로 변경되었습니다!");
            console.log(data); // 서버 응답 데이터 확인용
        }).catch(error => {
            console.error("에러 발생: ", error);
            alert("수량 변경에 실패했습니다. 다시 시도해주세요.");
        });
}