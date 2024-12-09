const employeeId = document.getElementById('employeeId');
console.log("아이디" + employeeId.value);
// 날짜 포맷 함수
function formatDate(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // 1월은 0이므로 +1
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');

    return `${year}년 ${month}월 ${day}일 ${hours}시 ${minutes}분`;
}
function getCsrfToken() {
    return document.querySelector('input[name="_csrf"]').value;
}

// Fetch 데이터를 가져오는 함수
function fetchData(url, options = {}) {
    return fetch(url, options)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP 에러: ${response.status}`);
            }
            return response.json();
        })
        .catch(error => {
            console.error("Fetch 오류: ", error);
            throw error; // 오류를 호출자에게 전달
        });
}

// 수정 버튼 클릭시
$("#editBtn").on("click", function () {
    const id = $("#checkId").val();
    console.log("아이디 " + id);

    fetchData(`/schedule/${id}`)
        .then(data => {
            console.log("데이타: " + JSON.stringify(data));
            $("#updateId").val(id);
            $("#updateTitle").val(data.title);
            $("#updateStart").val(data.start);
            $("#updateEnd").val(data.end ? data.end : data.start);
            $("#updateColor").val(data.backgroundColor);
            $("#updateText").val(data.text);
            $("#checkModal").modal("hide");
            $("#updateModal").modal("show");
        })
        .catch(error => {
            console.error("조회 오류:", error);
        });
});

// 수정 모달에서 확인 버튼 클릭시
$("#updateBtn").on("click", function () {
    const startDate = new Date($("#updateStart").val());
    const endDate = new Date($("#updateEnd").val());

    const options = { timeZone: "Asia/Seoul", hour12: false };
    const start = startDate.toLocaleString("sv-SE", options).replace(" ", "T");
    const end = endDate.toString() === "Thu Jan 01 1970 09:00:00 GMT+0900 (GMT+09:00)" ? null : endDate.toLocaleString("sv-SE", options).replace(" ", "T");

    const id = $("#updateId").val();
    const o = {
        no: +id,
        title: $("#updateTitle").val(),
        start: start,
        end: end,
        backgroundColor: $("#updateColor").val(),
        text: $("#updateText").val()
    };

    fetch(`/schedule/${id}`, {
        method: "PUT",
        headers: {
            'X-CSRF-TOKEN': getCsrfToken(),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(o)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('에러에러');
            }
            alert('수정되었습니다');
            location.reload();
        }).catch(error => {
            console.error("업데이트 오류: " + error);
        });

    $("#checkModal").modal("show");
});

// 삭제 버튼 클릭시
document.getElementById('deleteBtn').onclick = function () {
    const no = $("#checkId").val();

    fetch(`/schedule/${no}`, {
        method: "DELETE",
        headers: {
            'X-CSRF-TOKEN': getCsrfToken(),
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("삭제 오류");
            }
            alert('삭제되었습니다');
            location.reload();
        })
        .catch(error => {
            console.error("삭제 오류: " + error);
        });
};

// 일정 불러오기 및 FullCalendar 설정
fetchData(`/schedule/${employeeId.value}`)
    .then(schedules => {
        $(function () {
            const calendarEl = $('#calendar')[0];
            const calendar = new FullCalendar.Calendar(calendarEl, {
                height: '700px',
                expandRows: true,
                customButtons: {
                    myCustomButton: {
                        text: "일정 추가",
                        click: function () {
                            $("#insertStart").val("");
                            $("#insertModal").modal("show");
                        }
                    }
                },
                headerToolbar: {
                    left: 'myCustomButton',
                    center: 'title',
                    right: 'prev today next'
                },
                initialView: 'dayGridMonth',
                editable: false,
                nowIndicator: true,
                dayMaxEvents: true,
                locale: 'ko',
                dateClick: function (obj) {
                    $("#insertStart").val(`${obj.dateStr}T12:00`);
                    $("#insertModal").modal("show");
                    console.log(obj.dateStr);
                },
                eventClick: function (obj) {
                    const eventData = obj.event;
                    const no = eventData.extendedProps.no;

                    fetchData(`/schedule/calendar/${no}`)
                        .then(data => {
                            $("#checkId").val(data.no);
                            $("#checkTitle").text(data.title);
                            $("#checkStart").text(formatDate(data.start));
                            $("#checkEnd").text(data.end ? formatDate(data.end) : "");
                            $("#checkText").text(data.text);
                            $("#checkModal").modal("show");
                        })
                        .catch(error => {
                            console.error("조회 오류:", error);
                        });
                },
                eventAdd: function (obj) {
                    const o = {
                        title: obj.event.title,
                        start: new Date(obj.event.start).toLocaleString("sv-SE", { timeZone: "Asia/Seoul", hour12: false }).replace(" ", "T"),
                        end: obj.event.end ? new Date(obj.event.end).toLocaleString("sv-SE", { timeZone: "Asia/Seoul", hour12: false }).replace(" ", "T") : null,
                        backgroundColor: obj.event.backgroundColor,
                        text: obj.event.extendedProps.text,
                        employeeId: employeeId.value
                    };
                    console.log(o)
                    fetch(`/schedule/calendar`, {
                        method: "POST",
                        headers: {
                            'X-CSRF-TOKEN': getCsrfToken(),
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(o)
                    })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('에러에러');
                            }
                            location.reload();
                            return response.json();
                        })
                        .catch(error => {
                            console.error('에러:', error);
                        });
                },
                eventChange: function (obj) {
                    console.log(obj);
                },
                eventRemove: function (obj) {
                    console.log(obj);
                },
                events: schedules
            });

            // 일정 추가 버튼 클릭시
            $("#insertBtn").on("click", function () {
                const eventData = {
                    title: $("#insertTitle").val(),
                    start: $("#insertStart").val(),
                    end: $("#insertEnd").val(),
                    color: $("#insertColor").val(),
                    text: $("#insertText").val()
                };

                if (eventData.title === "" || eventData.start === "" || eventData.end === "") {
                    alert("입력하지 않은 값이 있습니다.");
                } else if ($("#insertStart").val() > $("#insertEnd").val()) {
                    alert("시간을 잘못입력 하셨습니다.");
                } else {
                    calendar.addEvent(eventData);
                    $("#insertModal").modal("hide");
                    $("#insertTitle").val("");
                    $("#insertStart").val("");
                    $("#insertEnd").val("");
                    $("#insertColor").val("");
                }
            });

            calendar.render();
        });
    })
    .catch(error => {
        console.error("일정 불러오기 오류:", error);
    });

