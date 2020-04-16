/* Base address for the Pokemon endpoints. Add the endpoint name and parameters onto this */
const ENDPOINT_BASE_URL = "https://sporadic.nz/pokesignment/";

(($) => {

    // expand and collapse the menu on a smaller screen
    $("nav img")[0].addEventListener("click", () => {
        $("#actions_nav")[0].classList.toggle("expand")
    });



})(document.querySelectorAll.bind(document));