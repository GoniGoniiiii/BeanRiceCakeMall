document.addEventListener('DOMContentLoaded', function() {
    const minusButton = document.querySelector('.minus');
    const plusButton = document.querySelector('.plus');
    const quantityInput = document.querySelector('.quantity-input');
    const allPriceElement = document.getElementById("all-price");

    // 가격을 가져와서 표시하는 함수
    function updateTotalPrice() {
        // data-value에서 가격 가져오기
        const pricePerItem = parseInt(allPriceElement.getAttribute("data-value"));
        // 수량 가져오기
        const quantity = parseInt(quantityInput.value);
        // 총 가격 계산
        const totalPrice = pricePerItem * quantity;
        // 총 가격 업데이트
        allPriceElement.textContent = totalPrice + "원";
    }

    // 페이지 로드시 초기 가격 표시
    updateTotalPrice();

    // '-' 버튼 클릭 시
    minusButton.addEventListener('click', function() {
        let currentValue = parseInt(quantityInput.value);
        if (currentValue > 1) {
            quantityInput.value = currentValue - 1;
            // 가격 업데이트
            updateTotalPrice();
        }
    });

    // '+' 버튼 클릭 시
    plusButton.addEventListener('click', function() {
        let currentValue = parseInt(quantityInput.value);
        quantityInput.value = currentValue + 1;
        // 가격 업데이트
        updateTotalPrice();
    });
});

function scrollToElement(elementId) {
    var element = document.getElementById(elementId);
    if (element) {
        element.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
}