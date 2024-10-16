function replaceEnter() {
    const spanElement = document.getElementById("product_content");
    const text = spanElement.innerText || spanElement.textContent;  // 텍스트 콘텐츠를 가져옴
    const formattedText = text.replace(/\n/g, '<br>');  // 줄바꿈 문자를 <br> 태그로 변환
    spanElement.innerHTML = formattedText;  // 변환된 HTML을 삽입
}

function imgDelete(button) {
    event.preventDefault();
    let file_url = button.value;
    console.log(file_url);

    fetch(`/img/delete/${encodeURIComponent(file_url)}`, {
        method: 'POST',
    })
        .then(response => {
            if (response.ok) {
                console.log("파일 삭제 완료");
                alert("이미지 삭제 완료");

                // 이미지 박스를 DOM에서 제거
                let imageBox = button.closest('.imagebox'); // 버튼의 가장 가까운 부모 .imagebox 찾기
                if (imageBox) {
                    imageBox.remove(); // 이미지 박스 삭제
                }
            } else {
                console.log("파일 삭제 실패");
            }
        })
        .catch(error => {
            console.error("삭제 요청 중 오류 발생:", error);
        });
}