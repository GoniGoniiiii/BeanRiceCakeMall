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

    if (reviewTitle == null || reviewTitle === '') {
        alert('리뷰 제을 입력하세요!');
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

    fetch('/my/addReview', {
        method: 'Post',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify({
            review_title: reviewTitle,
            review_content: reviewContent,
            product_num: productNum,
            user_num: userNum
        })
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