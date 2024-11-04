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

const fileDOM = document.querySelector('.file');
const fileDOM2 = document.querySelector('.file2');
const mainPreview = document.querySelector('.preview-box');
const preview = document.querySelector('.preview-box2');

fileDOM.addEventListener('change', () => {
    mainPreview.innerHTML = '';
    const file = fileDOM.files[0];
    const reader = new FileReader();

    reader.onload = ({target}) => {
        const img = document.createElement('img');
        img.src = target.result;
        img.style.width = '100px';
        img.style.height = '100px';
        img.style.marginRight = '10px';
        img.style.marginBottom = '5px';

        mainPreview.appendChild(img);
    }
    reader.readAsDataURL(file);
})

fileDOM2.addEventListener('change', () => {
    preview.innerHTML = '';
    const files = fileDOM2.files;

    if (files.length > 10) {
        alert('최대 10개까지 이미지를 선택하실 수 있습니다!');
        return;
    }

    const fileCount = Math.min(files.length, 10);

    for (let i = 0; i < fileCount; i++) {
        const file = files[i];
        const reader = new FileReader();

        reader.onload = ({ target }) => {
            const img = document.createElement('img');
            img.src = target.result;
            img.style.width = '100px';
            img.style.height = '100px';
            img.style.marginRight = '10px';
            img.style.marginBottom = '5px';

            preview.appendChild(img);
        }
        reader.readAsDataURL(file);
    }
});