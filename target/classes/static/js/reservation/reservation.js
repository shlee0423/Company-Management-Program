const today = new Date().toISOString().split('T')[0];
const now = new Date().toLocaleTimeString('it-IT').slice(0, 5);
document.getElementById('startDate').value = today;
document.getElementById('endDate').value = today;
document.getElementById('startTime').value = now;
document.getElementById('endTime').value = now;



function validateForm() {
    const applicant = document.getElementById('applicant');
    const startDate = document.getElementById('startDate');
    const endDate = document.getElementById('endDate');
    const startTime = document.getElementById('startTime');
    const endTime = document.getElementById('endTime');
    const purpose = document.getElementById('purpose');
    const TotalStartDate = document.getElementById('TotalStartDate');
    const TotalEndDate = document.getElementById('TotalEndDate');

    let isValid = true;

    if (applicant.value.trim() === '') {
        applicant.classList.add('error');
        alert('신청자를 입력해주세요.');
        isValid = false;
    } else {
        applicant.classList.remove('error');
    }

    if (startDate.value.trim() === '') {
        startDate.classList.add('error');
        alert('시작부분에 년월일을 입력해주세요');
        isValid = false;
    } else {
        startDate.classList.remove('error');
    }

    if (endDate.value.trim() === '') {
        endDate.classList.add('error');
        alert('끝난부분에 년월일을 입력해주세요');
        isValid = false;
    } else {
        endDate.classList.remove('error');
    }

    if (startTime.value.trim() === '') {
        startTime.classList.add('error');
        alert('시작시간을 입력해주세요');
        isValid = false;
    } else {
        startTime.classList.remove('error');
    }

    if (endTime.value.trim() === '') {
        endTime.classList.add('error');
        alert('끝난시간을 입력해주세요');
        isValid = false;
    } else {
        endTime.classList.remove('error');
    }

    if (purpose.value.trim() === '') {
        purpose.classList.add('error');
        alert('사용 목적을 입력해주세요.');
        isValid = false;
    } else {
        purpose.classList.remove('error');
    }

    TotalStartDate.value = startDate.value + 'T' + startTime.value;
    TotalEndDate.value = endDate.value + 'T' + endTime.value;

    if(isValid === false){
        return false;
    }else{
        alert('확인되었습니다');
        return true;
    }
}

function confirmCancel() {
    if (confirm('정말 취소하시겠습니까?')) {
        location.href='/product/manage_product';
        // document.getElementById('rentalForm').reset();
        // resetFormColors();
        // location.href="/product/manage_product";
    }
}

function resetFormColors() {
    const elements = document.querySelectorAll('.error');
    elements.forEach(element => {
        element.classList.remove('error');
    });
}