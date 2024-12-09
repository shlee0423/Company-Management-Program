// 승인 클릭 시 승인 Status 업데이트

function getCsrfToken() {
    return document.querySelector('input[name="_csrf"]').value;
}

function reservation_update(item, status){

    if (status == null) {
        const confirmed = confirm('승인하시겠습니까?');
        if (confirmed) {
            fetch("/reservation/list", {
                method: "POST",
                headers: {
                    'X-CSRF-TOKEN': getCsrfToken(),
                    "Content-Type": "application/json",
                    // "X-CSRF-TOKEN": csrfTokenInput.value
                },
                body: JSON.stringify(item)
            }).then(response => {
                if (response.ok) {
                    alert('승인되었습니다.');
                    location.reload(); // 화면 새로고침
                }
            });
        }
    }
}

// 거부 클릭 시 칼럼 삭제
function reservation_delete(item){
    const confirmed = confirm('거부하시겠습니까?');
    if(confirmed){
        fetch("/reservation/list",{
            method: "DELETE",
            headers: {
                'X-CSRF-TOKEN': getCsrfToken(),
                "Content-Type": "application/json",
                // "X-CSRF-TOKEN": csrfTokenInput.value
            },
            body: JSON.stringify(item)
        }).then(response => {
            if(response.ok){
                console.log(item)
                alert('거부되었습니다.');
                location.reload(); // 화면 새로고침
            }
        });
    }
}

