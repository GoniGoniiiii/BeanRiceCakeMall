function convertNewlineToBr() {
    var content = document.getElementById("product_content").value; // 텍스트 에어리어에서 입력된 내용 가져오기
    var convertedContent = content.replace(/\n/g, "<br>"); // \n을 <br>로 변환하기

    document.getElementById("product_content").value = convertedContent; // 변환된 내용을 다시 텍스트 에어리어에 설정하기
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