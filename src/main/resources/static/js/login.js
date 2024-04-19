//아이디 저장 함수
function savedUserId() {
    var userId = document.getElementById('user_id').value;
    console.log(userId);
    if (userId) {
        localStorage.setItem('savedUserId', userId);
        alert('아이디가 저장되었습니다.');
    } else {
        alert('아이디를 입력해주세요.');
        event.preventDefault();
    }
}

//페이지 로드 시 저장된 아이디가 있다면 입력 필드에 표신
document.addEventListener('DOMContentLoaded', function () {
    var savedUserId = localStorage.getItem('savedUserId');
    console.log(savedUserId);
    if (savedUserId) {
        document.getElementById('user_id').value = savedUserId;
    }
});