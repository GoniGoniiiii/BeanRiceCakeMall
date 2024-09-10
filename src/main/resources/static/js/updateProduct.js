function replaceEnter() {
    const spanElement = document.getElementById("product_content");
    const text = spanElement.innerText || spanElement.textContent;  // 텍스트 콘텐츠를 가져옴
    const formattedText = text.replace(/\n/g, '<br>');  // 줄바꿈 문자를 <br> 태그로 변환
    spanElement.innerHTML = formattedText;  // 변환된 HTML을 삽입
}