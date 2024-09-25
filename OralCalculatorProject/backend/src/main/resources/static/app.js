const startBtn = document.getElementById('start-btn');
const spokenText = document.getElementById('spoken-text');
const result = document.getElementById('result');

const recognition = new (window.SpeechRecognition || window.webkitSpeechRecognition)();
recognition.lang = 'en-US';
recognition.interimResults = false;

startBtn.addEventListener('click', () => {
    recognition.start();
});

recognition.onresult = (event) => {
    const speechResult = event.results[0][0].transcript;
    spokenText.textContent = `You said: ${speechResult}`;

    // Send the spoken text to the backend
    fetch('http://localhost:8080/api/calculate', {
        method: 'POST',
        headers: {
            'Content-Type': 'text/plain'
        },
        body: speechResult
    })
    .then(response => response.text())
    .then(data => {
        result.textContent = data;
    })
    .catch(error => {
        console.error('Error:', error);
        result.textContent = 'Error calculating the result';
    });
};
