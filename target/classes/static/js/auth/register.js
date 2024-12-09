// 빈/*칸에// 빈 칸에 대한 이벤트 처리*/

// function validateForm() {
//     const fieldIds = ['employeeId', 'employeePassword', 'employeePassword2', 'employeeName','employeeBirthDate','employeePhoneNumber','employeeEmail','employeeAddress','employeeGender','employeeDept','employeeProfileImage'];
//
//     let isValid = true;
//
//     for (let i = 0; i < fieldIds.length; i++) {
//         const fieldValue = document.getElementById(fieldIds[i]).value.trim();
//
//         switch (fieldIds[i]) {
//             case 'employeeId':
//                 if (fieldValue === '') {
//                     alert('아이디를 입력해주세요.');
//                     isValid = false;
//                 }
//                 break;
//             case 'employeePassword':
//                 if (fieldValue === '') {
//                     alert('비밀번호를 입력해주세요.');
//                     isValid = false;
//                 }
//                 break;
//             case 'employeePassword2':
//                 if (fieldValue === '') {
//                     alert('비밀번호를 재입력해주세요.');
//                     isValid = false;
//                 }
//                 break;
//             case 'employeeName':
//                 if (fieldValue === '') {
//                     alert('이름을 입력해주세요.');
//                     isValid = false;
//                 }
//                 break;
//             case 'employeeBirthDate':
//                 if (fieldValue === '') {
//                     alert('생년월일을 입력해주세요.');
//                     isValid = false;
//                 }
//                 break;
//             case 'employeePhoneNumber':
//                 if (fieldValue === '') {
//                     alert('휴대폰 번호를 입력해주세요.');
//                     isValid = false;
//                 }
//                 break;
//             case 'employeeEmail':
//                 if (fieldValue === '') {
//                     alert('메일을 입력해주세요.');
//                     isValid = false;
//                 }
//                 break;
//             case 'employeeAddress':
//                 if (fieldValue === '') {
//                     alert('주소를 입력해주세요.');
//                     isValid = false;
//                 }
//                 break;
//             case 'employeeGender':
//                 if (fieldValue === '') {
//                     alert('성별을 입력해주세요.');
//                     isValid = false;
//                 }
//                 break;
//             case 'employeeDept':
//                 if (fieldValue === '') {
//                     alert('부서를 입력해주세요.');
//                     isValid = false;
//                 }
//                 break;
//             case 'employeeProfileImage':
//                 if (fieldValue === '') {
//                     alert('프로필 이미지를 입력해주세요.');
//                     isValid = false;
//                 }
//                 break;
//         }
//         if (!isValid) {
//             break;
//         }
//     }
//     const id = document.getElementById('employeeId').value;
//     if(id.length < 4 && id.length > 12) {
//         alert("아이디를 4자리 이상 12자리 미만으로 작성해주세요");
//         isValid = false;
//     }
//     const password = document.getElementById('employeePassword').value;
//         const confirmPassword = document.getElementById('employeePassword2').value;
//         if (password !== confirmPassword) {
//             alert('비밀번호가 일치하지 않습니다.');
//             isValid = false;
//         }
//
//     return isValid;
// }



// birthdate 형식 처리
const employeeBirthDateInput = document.getElementById('employeeBirthDate');

employeeBirthDateInput.addEventListener('keyup', function() {
    let inputValue = this.value.replace(/\D/g, '');

    if (inputValue.length > 4) {
        inputValue = inputValue.substring(0, 4) + '-' + inputValue.substring(4, 6) + '-' + inputValue.substring(6, 8);
    } else if (inputValue.length > 6) {
        inputValue = inputValue.substring(0, 6) + '-' + inputValue.substring(6, 8);
    }
    this.value = inputValue;
});

// hidden phone 처리
const registerBtn = document.getElementById('registerBtn');
const phone = document.getElementById('phone').querySelector('input');
const phoneHead = document.getElementsByName('prefix')[0];
const phoneBody = document.getElementsByName('infix')[0];
const phoneTail = document.getElementsByName('postfix')[0];

const email = document.getElementsByName('employeeEmail')[0];
const emailHead = document.getElementsByName('emailFirst')[0];
const emailTail = document.getElementsByName('emailSecond')[0];
registerBtn.onclick = () => {
    // phone
    phone.value = `${phoneHead.value}-${phoneBody.value}-${phoneTail.value}`;

    // email
    email.value = `${emailHead.value}@${emailTail.value}`;
    // 완성되면 폼 이벤트 트루면 돌아가도록 하기
    document.forms.item(0).submit();

    // href

}


// 폼 열렸을 때 이벤트 처리
// 이메일 선택하기
//
document.addEventListener("DOMContentLoaded", () => {
    const emailDomainSelect = document.querySelector('select[name="emailDomain"]');
    const emailSecondInput = document.querySelector('input[name="emailSecond"]');
    emailDomainSelect.addEventListener("change", () => {
        emailSecondInput.value = emailDomainSelect.value;
    });

    const infixInput = document.querySelector('input[name="infix"]');
    infixInput.addEventListener("input", () => {
        infixInput.value = infixInput.value.replace(/\D/g, '').substring(0, 4);
    });

    const postfixInput = document.querySelector('input[name="postfix"]');
    postfixInput.addEventListener("input", () => {
        postfixInput.value = postfixInput.value.replace(/\D/g, '').substring(0, 4);
    });


    // 아이디 중복 검사
    const checkDuplicateBtn = document.getElementById('checkDuplicateBtn');
    const employeeIdInput = document.getElementById('employeeId');
    const idAvailabilitySpan = document.getElementById('idAvailability');

    checkDuplicateBtn.addEventListener('click', () => {
        const employeeId = employeeIdInput.value.trim();
        if (employeeId === '') {
            alert('아이디를 입력하세요.');
            return;
        }

        fetch(`/auth/register/${employeeId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('네트워크 상태가 좋지 않습니다.');
                }
                return response.json();
            })
            .then(data => {
                if (data === true) {
                    console.log("중복");
                    idAvailabilitySpan.textContent = '이미 사용 중인 아이디입니다.';
                } else {
                    console.log("중복아님");
                    idAvailabilitySpan.textContent = '사용 가능한 아이디입니다.';
                }
            })
            .catch(error => {
                console.error('에러 발생:', error);
                idAvailabilitySpan.textContent = '아이디 중복 확인 중 오류가 발생했습니다.';
            });
    });
});


// 휴대폰 인증
IMP.init("imp24111301");
const certificationBtn = document.getElementById('join-admin-button');
const impUid = document.getElementById('imp-uid');
// 본인 인증
certificationBtn.onclick = () => {
    console.log('클릭됨');
    IMP.certification(
        {
            // param
            pg: "inicis_unified.MIIiasTest", //본인인증 설정이 2개이상 되어 있는 경우 필수
        },
        function (rsp) {
            // callback
            console.log(rsp);
            if (rsp.success) {
                // 인증 성공 시 로직
                impUid.value = rsp["imp_uid"];
                certificationBtn.textContent = "본인 인증 완료";
                certificationBtn.disabled = true;


                const name = rsp.name;
                console.log(name);
            } else {
                // 인증 실패 시 로직
                alert("본인 인증 완료 못함");
            }
        },
    );
}

// 우편번호 찾기
findAddressBtn = document.getElementById('findAddress');
employeeAddressInput = document.getElementsByName('employeeAddress')[0];
findAddressBtn.onclick = () => {
    new daum.Postcode({
        oncomplete: function (data) {
            employeeAddressInput.value = data.address;

        }
    }).open();
}

// 성별 찾기
const genderInput = document.getElementsByName('employeeGender')[0];
const femaleBtn = document.getElementById('female');
const maleBtn = document.getElementById('male');
femaleBtn.addEventListener('click', () => {
    genderInput.value = '여성';
    femaleBtn.classList.add('selected');
    maleBtn.classList.remove('selected');
});

maleBtn.addEventListener('click', () => {
    genderInput.value = '남성';
    maleBtn.classList.add('selected');
    femaleBtn.classList.remove('selected');
});


// 부서 선택
const deptSelector = document.getElementsByName('employeeDept')[0];
deptSelector.addEventListener('change', () => {

})


// 이미지 미리보기
// document.getElementById('file').addEventListener('change', function(event) {
//     const file = event.target.files[0];
//     const preview = document.getElementById('image-preview');
//     preview.innerHTML = '';  // 기존 미리보기 초기화
//
//     if (file && file.type.match('image.*')) {
//         const reader = new FileReader();
//         reader.onload = function(e) {
//             const img = document.createElement('img');
//             img.src = e.target.result;
//             preview.appendChild(img);
//         }
//         reader.readAsDataURL(file);
//     } else {
//         preview.textContent = '유효한 이미지 파일을 선택하세요.';
//     }
// });



