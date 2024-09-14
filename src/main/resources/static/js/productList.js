function replaceEnter() {
    const spanElement = document.getElementById("product_content");
    const text = spanElement.innerText || spanElement.textContent;  // 텍스트 콘텐츠를 가져옴
    const formattedText = text.replace(/\n/g, '<br>');  // 줄바꿈 문자를 <br> 태그로 변환
    spanElement.innerHTML = formattedText;  // 변환된 HTML을 삽입
}

document.querySelectorAll('.sort-option').forEach(filter => {
    filter.addEventListener('click', function (event) {
        event.preventDefault();
        const sort = this.getAttribute('data-sort');
        const categoryNum = document.getElementById('category_num').value;

        fetch(`/productList/${categoryNum}?sort=${sort}`, {
            headers: {
                'X-Requested-With': 'XMLHttpRequest'
            }
        })
            .then(response => response.text())
            .then(html => {
                document.querySelector('#productList').innerHTML = html;
            })
            .catch(error => console.error('Error : ', error));
    });
});