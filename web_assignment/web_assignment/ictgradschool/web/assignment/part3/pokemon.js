/* Base address for the Pokemon endpoints. Add the endpoint name and parameters onto this */
const ENDPOINT_BASE_URL = "https://trex-sandwich.com/pokesignment/";

// the <script> tag has been added "defer", so no need to wrap the code in an event listener
// an immediately invoked function is used to keep the global name space clean
(async (window) => {

    const $ = document.querySelector.bind(document);

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

    // part 3
    const getPokemon = async (pokemonName) =>
        await (await fetch(ENDPOINT_BASE_URL + "pokemon?pokemon=" + pokemonName)).json();

    const panel = async (pokemonName) => {
        const pokemon = await getPokemon(pokemonName);
        return `<div class="panel" onclick="showDetail(\`${pokemonName}\`)">`
            + `<img src=${ENDPOINT_BASE_URL}img/${pokemon.image}>`
            + `<h4>${pokemonName}</h4>`
            + `</div>`;
    };

    window.showDetail = async (pokemonName) => {
        const pokemon = await getPokemon(pokemonName)
            , weak = pokemon.opponents.weak_against
            , strong = pokemon.opponents.strong_against
            , panel_container = $("#panel_container")
            , getWeakPanel = async () => {
                let weakPanel = "<h3>Weak Against</h3>";
                for (const oppo of weak)
                    weakPanel += await panel(oppo);
                return weakPanel;
            }
            , getStrongPanel = async () => {
                let strongPanel = "<h3>Strong Against</h3>";
                for (const oppo of strong)
                    strongPanel += await panel(oppo);
                return strongPanel;
            };

        panel_container.innerHTML =
              `<div class="flanking">${await getWeakPanel()}</div>`
            + `<div id="detail"><h2>${pokemon.name}</h2>`
            + `<img src="${ENDPOINT_BASE_URL}img/${pokemon.image}" alt="${pokemonName}">`
            + `<p>${pokemon.description}</p>`
            + `</div>`
            + `<div class="flanking">${await getStrongPanel()}</div>`;

        panel_container.classList.add("details_expanded");
    };

    const pokemons = await (await fetch(ENDPOINT_BASE_URL + "pokemon")).json();
    for (const pokemon of pokemons)
        $("#panel_container").innerHTML += await panel(pokemon);



})(window);