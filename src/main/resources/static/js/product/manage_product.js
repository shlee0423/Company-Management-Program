const tableListDeleteBtn = document.getElementById('table-list-delete-btn');
const csrfTokenInput = document.querySelector('input[name="_csrf"]');
const productList = document.getElementsByClassName('product');
const allSelectBtn = document.querySelector('.table-list-head input[type="checkbox"]');
// 전체선택
console.log(productList.length);
allSelectBtn.addEventListener('click', () => {
    console.log('클릭');
    [...productList].forEach(product => {
        const checkBoxInput = product.querySelector('input[type="checkbox"]');
        checkBoxInput.checked = allSelectBtn.checked;
        checkBoxInput.addEventListener('click', () => {
            // 다른 체크박스가 해제되면 전체 선택 체크박스 해제
            if (!checkBoxInput.checked) {
                allSelectBtn.checked = false;
            }
        });
    });
});



// 선택 삭제 버튼 눌렀을 때
tableListDeleteBtn.addEventListener('click', () => {
    const items = collect_product_selected_items();
    if(items.length <= 0){
        alert('비품을 하나이상 선택해주세요');
        return;
    }

    console.log("나와라: " + JSON.stringify(items, null, 2));
    delete_product_items(items);
});

// 선택되어있는 비품들을 수집
function collect_product_selected_items(){
    const items = []; // 비품 번호들을 가지는 리스트
    // 모든 비품들을 순회한다
    [...productList].forEach(product => {
        const checkBoxInput = product.querySelector('input[type="checkbox"]');
        // 비품이 선택되어있다면
        console.log(checkBoxInput.checked);
        if(checkBoxInput.checked) {
            const productNo = +product.querySelector('.product-no').textContent;
            items.push({
                productNo
            });
            console.log("넘버" + productNo);
        }
    });
    console.log("아이템" + JSON.stringify(items));
    return items;
}

// 선택되어 있는 비품 아이템을 삭제
function delete_product_items(items){
    fetch(`/product/manage_product`,{
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrfTokenInput.value
        },
        body: JSON.stringify(items)
    }).then(response => {
        if(response.ok) {
            alert('비품을 삭제하였습니다');
            location.reload(); // 화면 새로고침
        }
    });
}