import {Client} from "https://ga.jspm.io/npm:@stomp/stompjs@7.0.0/esm6/index.js"

const chatMiddelBox = document.querySelector('.chat-middel-box');

if(chatMiddelBox != null){
    // 로그인된 유저임. 나중에 제거 ?
    const userId = document.querySelector('.user').id;
    const messageInput = document.querySelector('.message-input');
    const roomNo = messageInput.id; // 방번호
    const section = document.querySelector(".chat-middle");

    const client = new Client({
        brokerURL: 'ws://localhost:8080/portfolio',
        onConnect: () => {
            // 구독
            client.subscribe(`/sub/${roomNo}`, message => {
                // const senderId = JSON.parse(message.body);
                const messageBody = message.body;

                // 송신자 이름과 메시지 분리
                const senderStartIndex = messageBody.indexOf('[') + 1;
                const senderEndIndex = messageBody.indexOf(']');
                const senderId = messageBody.substring(senderStartIndex, senderEndIndex);
                const chatMessage = messageBody.substring(senderEndIndex + 1).trim();

                // 로그 출력
                console.log("Sender ID:", senderId);
                console.log("Message:", chatMessage);
                console.log("Room No:", roomNo);
                console.log("User ID:", userId);
                // console.log("asdf", message.body)
                // console.log(roomNo)
                // console.log(userId)
                if (senderId === userId) {
                    section.insertAdjacentHTML('beforeend', `
                        <div class="chat-item chat-item-box">
                            <div class="avatar">
                               <img src="" alt="">
                            </div>
                            <div class="chat-title">${senderId}</div>
                        </div>
                        <div class="chat-body"><span>${chatMessage}</span></div>
                    `)
                } else {
                    section.insertAdjacentHTML('beforeend', `
                        <div class="chat-item chat-item-box">
                            <div class="avatar">
                               <img src="" alt="">
                            </div>
                            <div class="chat-title">${senderId}</div>
                        </div>
                        <div class="chat-head"><span>${chatMessage}</span></div>
                `)
                }
            });
            client.publish({
                destination: `/pub/${roomNo}/${userId}`,
                body: JSON.stringify({
                    // employee: {employeeName: userId},
                    chatMessage: messageInput.value,
                    chatStatus: "ENTER"
                })
            });
            // 발행
            messageInput.addEventListener('keydown', e => {
                if (e.key === "Enter"){
                    client.publish({
                        destination: `/pub/${roomNo}/${userId}`,
                        body: JSON.stringify({
                            // employee: {employeeName: userId},
                            chatMessage: messageInput.value,
                            chatStatus: "SEND"
                        })
                    });
                    messageInput.value = '';
                }
            })
        },
    });

    client.activate();

    messageInput.onkeydown = (e) => {
        const roomNo = messageInput.id;
        // 여기 유저 id 가져오는 거 수정해야함
        if (e.key === 'Enter'){
            console.log('시이작')
            const chat = {
                // roomNo: +roomNo,
                employee: {employeeId: userId},
                chatMessage: messageInput.value,
                chatStatus: "SEND"
            };
            console.log(JSON.stringify(chat))
            fetch(`/room/${roomNo}/chat`, {
                method: 'POST',
                headers: {
                    'Content-Type':'application/json'
                },
                body: JSON.stringify(chat)
            }).then(response => {
                if(response.status !== 201){
                    throw new Error('error' + response.status);
                }
            })
        }
    }
}
