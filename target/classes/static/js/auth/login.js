window.onload = function() {
    var urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('error')) {
        alert('로그인 실패: 아이디 또는 비밀번호를 확인하세요.');
    }
}