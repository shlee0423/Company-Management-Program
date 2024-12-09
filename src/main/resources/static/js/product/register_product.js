const submitBtn = document.getElementById('submitBtn');
const cancelBtn = document.getElementById('cancelBtn');

submitBtn.onclick = (e) => {
    let valid = true;

    // 비어있는 input 검사
    for (let input of inputs) {
        if (input.value.trim() === '') {
            let label = input.closest('label') || input.closest('.object-margin-design');
            let labelText = label.querySelector('span') ? label.querySelector('span').textContent : '모든 필드를 입력하세요.';

            alert(labelText + '을(를) 입력하세요.');
            input.focus();
            valid = false;
            e.preventDefault();
            break;
        }
    }

    // 모든 입력이 유효할 경우 확인 메시지 표시
    if (valid) {
        const confirmed = confirm('등록하시겠습니까?');
        if (confirmed) {
            form.submit();
        }
    }
}

cancelBtn.onclick = () => {
    const confirmed = confirm('취소하시겠습니까?');
    if(confirmed){
        location.href='/product/manage_product';
    }
};



// 확인 버튼 클릭 시
document.querySelector('form').addEventListener('submit', function (event) {
    const form = event.target;
    const inputs = form.querySelectorAll('input, select');

    let valid = true;

    // 비어있는 input 검사
    for (let input of inputs) {
        if (input.value.trim() === '') {
            let label = input.closest('label') || input.closest('.object-margin-design');
            let labelText = label.querySelector('span') ? label.querySelector('span').textContent : '모든 필드를 입력하세요.';

            alert(labelText + '을(를) 입력하세요.');
            input.focus();
            valid = false;
            event.preventDefault();
            break;
        }
    }
});


// 구매일 날짜 jquery
$.datepicker.setDefaults({
    dateFormat: 'yy-mm-dd',
    prevText: '이전 달',
    nextText: '다음 달',
    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    showMonthAfterYear: true,
    yearSuffix: '년'
});

$(function () {
    $('.datepicker').datepicker();
});
