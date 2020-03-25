window.addEventListener("load", function(){
    const $ = document.getElementById.bind(document);
    for (let i = 0; i <= 10; ++i) {
        $("page" + i).onclick = (event) => {
            event.target.style.animationName = "flippage";
        }
    }
});