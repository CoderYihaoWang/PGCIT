/* Base address for the Pokemon endpoints. Add the endpoint name and parameters onto this */
const ENDPOINT_BASE_URL = "https://sporadic.nz/pokesignment/";

// the <script> tag has been added "defer", so no need to wrap the code in an event listener
// an immediately invoked function is used to keep the global name space clean
(async ($) => {

    // part 1
    $("nav img")[0].addEventListener("click", () => {
        $("nav nav:last-of-type")[0].classList.toggle("expand")
    });

    // part 2
    const pokemonOfTheDay = await (await fetch(ENDPOINT_BASE_URL + "pokemon?random=random")).json();
    console.log(pokemonOfTheDay);
    $("#pokemon_of_the_day_col")[0].innerHTML +=
        `<img alt="pokemon of the day" src="${ENDPOINT_BASE_URL}/img/${pokemonOfTheDay.image}">`
        + `<h4>${pokemonOfTheDay.name}</h4>`
        + `<p>${pokemonOfTheDay.description}</p>`;

})(document.querySelectorAll.bind(document));