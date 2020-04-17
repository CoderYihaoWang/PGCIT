/* Base address for the Pokemon endpoints. Add the endpoint name and parameters onto this */
const ENDPOINT_BASE_URL = "https://trex-sandwich.com/pokesignment/";

// the <script> tag has been added "defer", so no need to wrap the code in an event listener
// an immediately invoked function is used to keep the global name space clean
(async ($) => {

    // part 1
    $("nav img").addEventListener("click", () => {
        $("#actions_nav").classList.toggle("expand")
    });

    // part 2
    const showPokemonOfTheDay = async () => {
        const pokemonOfTheDay = await (await fetch(ENDPOINT_BASE_URL + "pokemon?random=random")).json();
        $("#pokemon_of_the_day_col").innerHTML =
            `<h3>Pokemon of the day</h3>`
            + `<img alt="pokemon of the day" src="${ENDPOINT_BASE_URL}img/${pokemonOfTheDay.image}">`
            + `<h4>${pokemonOfTheDay.name}</h4>`
            + `<p>${pokemonOfTheDay.description}</p>`;
        $("#pokemon_of_the_day_col img").addEventListener("click", await showPokemonOfTheDay);
    };
    $("#show_pokemon_of_the_day").addEventListener("click", await showPokemonOfTheDay);
    await showPokemonOfTheDay();



})(document.querySelector.bind(document));