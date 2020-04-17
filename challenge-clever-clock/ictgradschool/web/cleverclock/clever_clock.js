window.addEventListener("load", function () {
    // Clock Task
    const $ = document.getElementById.bind(document)
        , pad = (number) => number.toString().padStart(2, '0')
        , hour = (date) => pad(date.getHours() === 12 ? 12 : date.getHours() % 12)
        , minute = (date) => pad(date.getMinutes())
        , semiColon = (date) => date.getSeconds() % 2 ? ' ' : ':'
        , AMOrPM = (date) => date.getHours() < 12 ? 'AM' : 'PM'
        , week = (date) =>
            ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'][date.getDay()]
        , month = (date) =>
            ['January', 'February', 'March', 'April', 'May', 'June',
                'July', 'August', 'September', 'October', 'November', 'December'][date.getMonth()]
        , time = (date) => hour(date) + semiColon(date) + minute(date) + ' ' + AMOrPM(date)
        , day = (date) => `${week(date)}, ${date.getDate()} ${month(date)} ${date.getFullYear()}`
        , showTime = () => $('clock_face').innerText = time(new Date())
        , showDate = () => $('date_face').innerText = day(new Date())
        , showClock = () => showTime() && showDate() && showClock
        , clockTask = () => setInterval(showClock(), 1000);

    clockTask();

    // Timer Task
    const AUTO_CLEAR_TIMEOUT = 30000
        , timer_face = $('timer_face')
        , run = $('run')
        , rst = $('rst')
        , timer = {time: 0, interval: null, timeout: null}
        , enable = (button) => {
            button.classList.add('button_enabled');
            button.classList.remove('button_disabled');
        }
        , disable = (button) => button.classList.add('button_disabled')
        , getTime = (time) => `${Math.floor(time / 10)}.${time % 10}`
        , updateTime = () => timer_face.innerText = getTime(++timer.time)
        , resetTime = () => (timer_face.innerText = getTime(timer.time = 0)) && disable(rst)
        , startTime = () => timer.interval = setInterval(updateTime, 100)
        , stopTime = () => timer.interval = clearInterval(timer.interval)
        , startCountdown = () => timer.timeout = setTimeout(resetTime, AUTO_CLEAR_TIMEOUT)
        , stopCountdown = () => timer.timeout = clearTimeout(timer.timeout);

    function start() {
        startTime();
        stopCountdown();
        disable(rst);
        run.onclick = stop;
        run.innerText = 'Stop';
    }

    function stop() {
        stopTime();
        startCountdown();
        enable(rst);
        run.onclick = start;
        run.innerText = 'Start';
    }

    function reset() {
        if (timer.time && !timer.interval) {
            stopCountdown();
            resetTime();
        }
    }

    function timerTask() {
        enable(run);
        disable(rst);
        run.onclick = start;
        rst.onclick = reset;
    }

    timerTask();

});