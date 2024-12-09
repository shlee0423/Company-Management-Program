const form = document.forms.namedItem('insert-form');
const submitBtn = document.getElementById('submitBtn');

submitBtn.onclick = () => {
    form.submit();
}

$(function() {
    // DateTime Picker 기본 설정
    $.datetimepicker.setLocale('kr');

    // 오늘 날짜를 YYYY-MM-DD 형식으로 가져오기
    let today = new Date();
    let todayStr = today.toISOString().split('T')[0];

    // 공통 기본 옵션 설정
    const defaultOptions = {
        format: 'Y-m-d H:i',
        scrollMonth: false,
        scrollInput: false,
        step: 30, // 30분 단위 설정
        minDate: todayStr // 오늘날짜 이전은 선택불가
    };

    $.extend($.datetimepicker.defaults, defaultOptions);

    // 개별 DateTime Picker 설정
    let startDate = $('.start-date');
    let endDate = $('.end-date');

    startDate.datetimepicker({
        onShow: function() {
            this.setOptions({
                // maxDate: endDate.val() || false,
                minDate: todayStr // 오늘날짜 이전은 선택불가
            });
        },
        onClose: function(ct, $i) {
            if (!endDate.val()) endDate.val($i.val());
        }
    });

    endDate.datetimepicker({
        onShow: function() {
            this.setOptions({
                minDate: startDate.val() || todayStr
            });
        },
        onClose: function(ct, $i) {
            if (!startDate.val()) startDate.val($i.val());
        }
    });
    startDate.on('change', function() {
        const startVal = startDate.val();
        endDate.datetimepicker('setOptions', {
            minDate: startVal || todayStr
        });

        // 종료 날짜가 시작 날짜보다 이전일 경우 종료 날짜를 시작 날짜로 업데이트
        if (endDate.val() < startVal) {
            endDate.val(startVal);
        }
    });
});