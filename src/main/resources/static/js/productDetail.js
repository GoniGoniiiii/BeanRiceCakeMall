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
        const currentValue = parseInt(quantityInput.value);
        if (currentValue > 1) {
            quantityInput.value = currentValue - 1;
            // 가격 업데이트
            updateTotalPrice();
        }
    });

    // '+' 버튼 클릭 시
    plusButton.addEventListener('click', function() {
        const currentValue = parseInt(quantityInput.value);
        quantityInput.value = currentValue + 1;
        // 가격 업데이트
        updateTotalPrice();
    });
});

function scrollToElement(elementId) {
    const element = document.getElementById(elementId);
    if (element) {
        element.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
}

function updateProduct(){
    const user_num=document.getElementById("product_num").value;
    window.location="/admin/updateProduct/"+user_num;
}

document.addEventListener("DOMContentLoaded", function() {
    replaceEnter();
});

function replaceEnter() {
    const spanElement = document.getElementById("product_content");
    const text = spanElement.innerText || spanElement.textContent;  // 텍스트 콘텐츠를 가져옴
    const formattedText = text.replace(/\n/g, '<br>');  // 줄바꿈 문자를 <br> 태그로 변환
    spanElement.innerHTML = formattedText;  // 변환된 HTML을 삽입
}

function cart(){
    const userNum= document.getElementById("user_num").value;
    const productNum=document.getElementById("product_num").value;
    const cartCnt=document.getElementById("cart_cnt").value;
    const productImg=document.getElementById("product_img").value;

    console.log(cartCnt);
    console.log(userNum);
    var data={
        user_num :userNum,
        product_num :productNum,
        cart_cnt : cartCnt,
        product_img : productImg
    };

    if(userNum==='-1'){
        alert("로그인이 필요합니다!");
        window.location="/login";
    }else{
        fetch("/cart",{
            method:'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response =>response.text())
            .then(result=>{
                document.getElementById("modal-content").textContent=result;
                var modal=new bootstrap.Modal(document.getElementById('cartModal'));
                modal.show();
            })
            .catch(error =>{
                console.error('Error :' +error);
            })
    }
}
function goCart(){
    //장바구니로 이동
    const user_num= document.getElementById("user_num").value;
    console.log(user_num);
    window.location="/my/shoppingBag/"+user_num;
}

function keep(){
    //쇼핑 계속하기
    window.location.reload();
}