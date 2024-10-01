document.addEventListener('DOMContentLoaded', function () {
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

    //엔터처리
    replaceEnter();

    // '-' 버튼 클릭 시
    minusButton.addEventListener('click', function () {
        const currentValue = parseInt(quantityInput.value);
        if (currentValue > 1) {
            quantityInput.value = currentValue - 1;
            // 가격 업데이트
            updateTotalPrice();
        }
    });

    // '+' 버튼 클릭 시
    plusButton.addEventListener('click', function () {
        const currentValue = parseInt(quantityInput.value);
        quantityInput.value = currentValue + 1;
        // 가격 업데이트
        updateTotalPrice();
    });
});

function scrollToElement(elementId) {
    const element = document.getElementById(elementId);
    if (element) {
        element.scrollIntoView({behavior: 'smooth', block: 'start'});
    }
}

function updateProduct() {
    const user_num = document.getElementById("product_num").value;
    window.location = "/admin/updateProduct/" + user_num;
}


function cart() {
    const userNum = document.getElementById("user_num").value;
    const productNum = document.getElementById("product_num").value;
    const cartCnt = document.getElementById("cart_cnt").value;
    const productImg = document.getElementById("product_img").value;

    console.log(cartCnt);
    console.log(userNum);
    var data = {
        user_num: userNum,
        product_num: productNum,
        cart_cnt: cartCnt,
        product_img: productImg
    };

    if (userNum === '-1') {
        alert("로그인이 필요합니다!");
        window.location = "/login";
    } else {
        fetch("/cart", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.text())
            .then(result => {
                document.getElementById("modal-content").textContent = result;
                var modal = new bootstrap.Modal(document.getElementById('cartModal'));
                modal.show();
            })
            .catch(error => {
                console.error('Error :' + error);
            })
    }
}

// 서버에서 받은 리뷰 내용을 변환하여 출력하는 함수
function replaceEnter() {
    const content = document.querySelectorAll(".content");

    content.forEach(element => {
        const productContent = element.textContent;
        const formattedContent = productContent.replace(/\n/g, '<br>');
        element.innerHTML = formattedContent;
    })
}


// 가격 포맷팅 함수
function formatNumber(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function goPay() {
    const userNum = document.getElementById("user_num").value;
    const productNum = document.getElementById("product_num").value;
    const cartCnt = document.getElementById("cart_cnt").value;
    const productImg = document.getElementById("product_img").value;
    const oprice = parseInt(document.getElementById("product_oprice").textContent.replace(/[^0-9]/g, '')); // 숫자로 변환
    const sprice = parseInt(document.getElementById("product_sprice").textContent.replace(/[^0-9]/g, '')); // 숫자로 변환
    const delivery = Number(document.getElementById("product_delivery").textContent.replace(/[^0-9.-]+/g, ""));
    const total_sprice = Number(document.getElementById("all-price").textContent.replace(/[^0-9.-]+/g, ""));

    const total_oprice = Number(oprice) * cartCnt;
    const total_sale = Number(oprice - sprice) * cartCnt;
    const total_price = total_sprice + delivery;

    console.log(total_sale);

    console.log(oprice);
    console.log(delivery);
    console.log(sprice);
    console.log(total_sprice);
    console.log(cartCnt);
    console.log(userNum);
    console.log(total_price);


    var data = {
        user_num: userNum,
        product_num: productNum,
        cart_cnt: cartCnt,
        product_img: productImg,
        total_oprice: total_oprice,
        total_delivery: delivery,
        total_sale: total_sale,
        total_price: total_price
    };

    if (userNum === '-1') {
        alert("로그인이 필요합니다!");
        window.location = "/login";
    } else {
        fetch("/pay", {
            method: 'Post',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.text())
            .then(data => {
                window.location.href = "/payment2";
            })
            .catch(error => {
                console.error('Error :' + error);
            })
    }
}

function goCart() {
    //장바구니로 이동
    // const user_num= document.getElementById("user_num").value;
    console.log(user_num);
    window.location = "/my/shoppingBag";
}

function keep() {
    //쇼핑 계속하기
    window.location.reload();
}

function formatPrices() {
    const priceElements = document.querySelectorAll(".product-price2");

    priceElements.forEach(element => {
        const price = parseInt(element.textContent.replace(/[^0-9]/g, ''));
        if (!isNaN(price)) {
            element.textContent = formatNumber(price);
        }
    });
}

window.onload = formatPrices;

function deleteProduct() {
    const product_num = document.getElementById("product_num").value;
    window.location = "/product/deleteProduct/" + product_num;
}

function setReviewData(button) {
    var reviewId = button.getAttribute("data-review-id");
    var reviewTitle = button.getAttribute("data-review-title");
    var reviewContent = button.getAttribute("data-review-content");

    // 모달 내의 입력 필드를 찾아서 값 할당
    document.getElementById("update_review_num").value = reviewId;
    document.getElementById("update_review_title").value = reviewTitle;
    document.getElementById("update_review_content").value = reviewContent;
}

function updateReview() {
    const uReviewNum = document.getElementById("update_review_num").value;
    const uReviewTitle = document.getElementById("update_review_title").value;
    const uReviewContent = document.getElementById("update_review_content").value;
    const productNum = document.getElementById("review_product_num").value;
    const userNum = document.getElementById("review_user_num").value;

    console.log(uReviewNum);
    console.log(uReviewTitle);
    console.log(uReviewContent);
    console.log(productNum);
    console.log(userNum);

    fetch('/my/updateReview', {
        method: 'Post',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            review_num: uReviewNum,
            review_title: uReviewTitle,
            review_content: uReviewContent,
            product_num: productNum,
            user_num: userNum
        })
    })
        .then(response => response.text())
        .then(result => {
                alert(result);
                window.location.reload();
            }
        )
        .catch(error => console.log(error));
}

function deleteReview(button) {
    const reviewNum = button.getAttribute("data-review-id");

    const del = confirm("리뷰를 정말 삭제하시겠습니까?");
    if (del) {
        fetch('/my/deleteReview', {
            method: 'delete',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                review_num: reviewNum
            })
        })
            .then(response => response.text())
            .then(result => {
                alert(result);
                window.location.reload();
            })
    } else {
        alert("리뷰가 삭제되지 않았습니다.");
    }
}
