document.addEventListener('DOMContentLoaded', function() {
    const minusButton = document.querySelector('.minus');
    const plusButton = document.querySelector('.plus');
    const quantityInput = document.querySelector('.quantity-input');

    minusButton.addEventListener('click', function(event) {
        event.preventDefault(); // 기본 동작 중지
        let currentValue = parseInt(quantityInput.value);
        if (currentValue > 1) {
            quantityInput.value = currentValue - 1;
        }
    });

    plusButton.addEventListener('click', function(event) {
        event.preventDefault(); // 기본 동작 중지
        let currentValue = parseInt(quantityInput.value);
        quantityInput.value = currentValue + 1;
    });
});

function changeQuantity(){
    var quantity=document.querySelector('.quantity-input').value;
    let currentValue = parseInt(quantity);
    console.log("수량변경 : "+quantity);
    return quantity;
}