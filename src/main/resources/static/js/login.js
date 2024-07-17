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
//
// async function submitForm(event) {
//     event.preventDefault(); // 폼 제출 기본 동작을 막습니다.
//
//     const form = document.getElementById('loginForm');
//     const formData = new FormData(form);
//
//     try {
//         const response = await fetch('/login', {
//             method: 'POST',
//             body: formData
//         });
//
//         if (!response.ok) {
//             throw new Error('서버 응답이 실패했습니다.');
//         }
//
//         const token = response.headers.get("Authorization");
//         if (token) {
//             localStorage.setItem('token', token);
//         }
//
//
//     } catch (error) {
//         console.error('에러 발생:', error);
//         alert('로그인 중 문제가 발생했습니다. 나중에 다시 시도해주세요.');
//     }
// }


// document.getElementById('login_button').addEventListener('click', async function (event) {
//
//     let username = document.getElementById('user_id').value;
//     let password = document.getElementById('user_pw').value;
//
//     console.log(username, password);
//
//     try {
//         // 로그인 요청
//         let response = await fetch('/login', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify({
//                 username: username,
//                 password: password
//             })
//         });
//
//         if (!response.ok) {
//             throw new Error('로그인 실패');
//         }
//
//         console.log(response);
//
//         // 응답 헤더에서 JWT 토큰을 추출하여 로컬 스토리지에 저장
//         // const auth = response.headers.get("Authorization");
//         // if (auth) {
//         //     const token = auth.split(' ')[1];
//         //     localStorage.setItem('token', token);
//         //     console.log(token);
//         //     alert('로그인 성공, 토큰 저장됨');
//         //     window.location.href = '/';
//         // } else {
//         //     throw new Error('Authorization 인증 정보가 없습니다');
//         // // }
//         // window.location.href = '/';
//
//     } catch (error) {
//         console.error('Error: ' + error);
//         alert('로그인 실패: ' + error.message);
//     }
// });

// $(document).ready(function() {
//     $('#login_button').on('click', function() {
//         let username = $('#user_id').val();
//         let password = $('#user_pw').val();
//
//         fetch('/login', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json' ,
//                 'Accept': 'application/json'
//             },
//             body: JSON.stringify({
//                 username: username,
//                 password: password
//             }),
//             credentials : 'include'
//         })
//             .then(response => {
//                 if (!response.ok) {
//                     throw new Error('로그인 실패: ' + response.statusText);
//                 }
//                 const auth = response.headers.get("Authorization");
//                 if (auth) {
//                     const token = auth.split(' ')[1];
//                     localStorage.setItem('token', token);
//                     alert('로그인 성공, 토큰 저장됨');
//                 } else {
//                     throw new Error('Authorization 인증 정보가 없습니다');
//                 }
//             })
//             .catch(error => {
//                 alert('로그인 실패: ' + error.message);
//             });
//     });
// });


// async function login() {
//     let username = document.getElementById('user_id').value;
//     let password = document.getElementById('user_pw').value;
//
//     console.log(username, password);
//
//     try {
//         // 로그인 요청
//         let response = await fetch('http://localhost:8282/login', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify({
//                 username: username,
//                 password: password
//             })
//         });
//
//         if (!response.ok) {
//             throw new Error('로그인 실패');
//         }
//
//         // 응답 헤더에서 JWT 토큰을 추출하여 로컬 스토리지에 저장
//         const auth = response.headers.get("Authorization");
//         if (auth) {
//             const token = auth.split(' ')[1];
//             localStorage.setItem('token', token);
//             console.log(token);
//             alert('로그인 성공, 토큰 저장됨');
//             window.location.href = '/';
//         } else {
//             throw new Error('Authorization 인증 정보가 없습니다');
//         }
//     } catch (error) {
//         console.error('Error: ' + error);
//         alert('로그인 실패: ' + error.message);
//     }
// }
//
// document.getElementById('login_button').addEventListener('click', function() {
//     login();
// });