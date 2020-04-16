window.addEventListener("load", function () {
    // helper functions
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
                'July', 'August', 'September', 'October', 'November', 'December'
            ][date.getMonth()]
        , time = (date) => hour(date) + semiColon(date) + minute(date) + ' ' + AMOrPM(date)
        , day = (date) => `${week(date)}, ${date.getDay()} ${month(date)} ${date.getFullYear()}`
        , showTime = () => $('clock_face').innerText = time(new Date())
        , showDate = () => $('date_face').innerText = day(new Date())




    // Clock Task
    showTime(); showDate();
    setInterval(() => {showTime(); showDate()}, 1000);

    // Timer Task


});