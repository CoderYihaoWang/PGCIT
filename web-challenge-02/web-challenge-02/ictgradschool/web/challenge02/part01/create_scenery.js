window.addEventListener("load", function(){
    const $ = document.querySelectorAll.bind(document);
    const PATH = `../images/dolphins/`;

    $("input[name=radio]").forEach(btn => {
        btn.onclick = () => {
            const picture = $("input[name=radio]:checked")[0].id.split("-")[1];
            $("#background")[0].src = PATH + picture + (picture.slice(-1) === '6' ? ".gif" : ".jpg");
        }
    });

    $("input[type=checkbox]").forEach(box => {
        box.checked = true;
        box.onclick = () => {
            const id = "#" + box.id.split("-")[1];
            $(id)[0].hidden = !box.checked;
        }
    });

    $("input[type=range]").forEach(range => {
        range.onclick = () => {
            const x = $("#horizontal-control")[0].value + "%";
            const y = $("#vertical-control")[0].value + "%";
            const size = $("#size-control")[0].value / 100 + 1;
            $(".dolphin").forEach(img => img.style.transform = `translate(${x}, ${y}) scale(${size})`);
        }
    })

});