window.addEventListener("load", function(){
    document.querySelectorAll(".page").forEach(page => {
        if (page.nextElementSibling) {
            page.onclick = () => page.style.animationName = "flippage";
            page.onanimationend = () => page.nextElementSibling.style.zIndex = "0";
        }
    });
});