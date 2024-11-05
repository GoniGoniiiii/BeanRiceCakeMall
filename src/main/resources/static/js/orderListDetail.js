function review() {
    const user_num = document.getElementById("user_num").value;
    console.log('user_num : ' + user_num);

    const product_num = document.querySelector("#product_num").value;
    console.log('product_num : ' + product_num);

    document.querySelectorAll(".product-img").forEach((img) => {
        const src = img.getAttribute("src");
        console.log('product_img : ' + src);
    });

    document.querySelectorAll(".product-name").forEach((td) => {
        const text = td.textContent;
        console.log('product_name : ' + text);
    });

}

function addReview() {
    // 체크된 첫 번째 체크박스를 선택하고, 그 값을 가져옵니다.
    const selectedProduct = document.querySelector('input[name="select_product"]:checked');

    // 체크된 체크박스가 있으면 그 값을 가져오고, 없으면 null을 반환합니다.
    const productNum = selectedProduct ? selectedProduct.value : null;

    const reviewTitle = document.getElementById('review_title').value;
    const reviewContent = document.getElementById('review_content').value;
    const userNum = document.getElementById('user_num').value;
    const formattedText = reviewContent.replace(/\n/g, '<br>');  // 줄바꿈 문자를 <br> 태그로 변환
    const inputImg = document.querySelector('.file');
    const files=inputImg.files;

    reviewContent.innerHTML = formattedText;  // 변환된 HTML을 삽입

    if (reviewTitle == null || reviewTitle === '') {
        alert('리뷰 제목을 입력하세요!');
        document.getElementById('review_title').focus();
        return;
    }

    if (reviewContent == null || reviewContent === '') {
        alert('리뷰 내용을 입력하세요!');
        document.getElementById('review_content').focus();
        return;
    }
    console.log(productNum);
    console.log(reviewTitle);
    console.log(reviewContent);

    const formData = new FormData();

    formData.append('review_title', reviewTitle);
    formData.append('review_content', reviewContent);
    formData.append('product_num', productNum);
    formData.append('user_num', userNum);

    for(let i=0; i<files.length; i++){
        formData.append('review_img', files[i]);
    }

    fetch('/my/addReview', {
        method: 'Post',
        body: formData
    })
        .then(response => response.text())
        .then(result => {
            console.log(result);
            document.getElementById("result").textContent = result;
            document.getElementById("product_num").value = productNum;

            var modal = new bootstrap.Modal(document.getElementById('resultModal'));
            modal.show();

            document.getElementById('review_title').value = '';
            document.getElementById('review_content').value = '';
            if (selectedProduct) {
                selectedProduct.checked = false;
            }
        })
}

function checkReview() {
    const productNum = document.getElementById('product_num').value;
    window.location = "/productDetail/" + productNum;
}

function orderList() {
    location.href = "/my/orderList";
}

function formatPrices() {
    const priceElements = document.querySelectorAll(".order-price");

    priceElements.forEach(element => {
        const price = parseInt(element.textContent.replace(/[^0-9]/g, ''));
        if (!isNaN(price)) {
            element.textContent = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }
    });
}

window.onload = formatPrices;

const fileDOM = document.querySelector('.file');
const preview = document.querySelector('.preview-box');

fileDOM.addEventListener('change', () => {
    preview.innerHTML = '';
    const files = fileDOM.files;

    if(files.length>10){
        alert('최대 10개까지 이미지를 선택하실 수 있습니다!');
        return;
    }

    const fileCount = Math.min(files.length, 10);

    for (let i = 0; i < fileCount; i++){
        const file=files[i];
        const reader=new FileReader();

        reader.onload=({target})=>{
            const img=document.createElement('img');
            img.src=target.result;
            img.style.width='100px';
            img.style.height='100px';
            img.style.marginRight='10px';
            img.style.marginBottom='5px';

            preview.appendChild(img);
        }
        reader.readAsDataURL(file);
    }
})