document.addEventListener('DOMContentLoaded', function() {
    const minusButtons = document.querySelectorAll('.minus');
    const plusButtons = document.querySelectorAll('.plus');
    const quantityInputs = document.querySelectorAll('.quantity-input');
    const applyButtons = document.querySelectorAll('.apply-btn');
    const allChoose = document.getElementById('All-choose');
    const itemCheckboxes = document.querySelectorAll('.item-checkbox');

    minusButtons.forEach(function(minusButton, index) {

    minusButtons.forEach(function(minusButton, index) {
        minusButton.addEventListener('click', function(event) {
            event.preventDefault();
            let currentValue = parseInt(quantityInputs[index].value);
            if (currentValue > 1) {
                quantityInputs[index].value = currentValue - 1;
            }
        });
    });

    plusButtons.forEach(function(plusButton, index) {
        plusButton.addEventListener('click', function(event) {
            event.preventDefault();
            let currentValue = parseInt(quantityInputs[index].value);
            quantityInputs[index].value = currentValue + 1;
        });
    });

    applyButtons.forEach(function(applyButton, index) {
        applyButton.addEventListener('click', function(event) {
            event.preventDefault();
            let quantity = quantityInputs[index].value;
            console.log("수량:", quantity);
        });
    });
});

allChoose.addEventListener('change', function() {
    if (allChoose.checked) {
        itemCheckboxes.forEach(function(checkbox) {
            checkbox.checked = true;
            console.log("선택된 상품 :"+checkbox.value);
        });
    } else {
        itemCheckboxes.forEach(function(checkbox) {
            checkbox.checked = false;
        });
    }
});
});
function goPayment() {
    location.href = "/payment";
}