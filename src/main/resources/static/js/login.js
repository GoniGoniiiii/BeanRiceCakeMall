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

//페이지 로드 시 저장된 아이디가 있다면 입력 필드에 표시
document.addEventListener('DOMContentLoaded', function () {
    var savedUserId = localStorage.getItem('savedUserId');
    console.log(savedUserId);
    if (savedUserId) {
        document.getElementById('user_id').value = savedUserId;
    }
});

document.getElementById('login_button').addEventListener('click', async function () {

    let username = document.getElementById('user_id').value;
    let password = document.getElementById('user_pw').value;

    console.log(username, password);

    try {
        // 로그인 요청
        let response = await fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        });

        if (!response.ok) {
            throw new Error('로그인 실패');
        }

        // 응답 헤더에서 JWT 토큰을 추출하여 로컬 스토리지에 저장
        const auth = response.headers.get("Authorization");
        if (auth) {
            const token = auth.split(' ')[1];
            localStorage.setItem('token', token);
            console.log(token);
            alert('로그인 성공, 토큰 저장됨');
            window.location.href = '/';
        } else {
            throw new Error('Authorization 인증 정보가 없습니다');
        }
    } catch (error) {
        console.error('Error: ' + error);
        alert('로그인 실패: ' + error.message);
    }
});