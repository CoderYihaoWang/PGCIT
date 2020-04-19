/* Base address for the Pokemon endpoints. Add the endpoint name and parameters onto this */
const ENDPOINT_BASE_URL = "https://trex-sandwich.com/pokesignment/";

// the <script> tag has been added "defer", so no need to wrap the code in an event listener
// an immediately invoked function is used to keep the global name space clean
(async (window) => {

    // jQuery style short cut to querySelector
    const $ = document.querySelector.bind(document);

    // if supplied a name, retrieve the pokemon json, otherwise get a random pokemon json
    async function getPokemon(name) {
        return name
            ? await (await fetch(ENDPOINT_BASE_URL + "pokemon?pokemon=" + name)).json()
            : await (await fetch(ENDPOINT_BASE_URL + "pokemon?random=random")).json();
    }

    // turns a pokemon name to a HTML formatted string representing a panel for that pokemon
    async function panel(name) {
        const pokemon = await getPokemon(name);
        return `<div class="panel" onclick="showDetail(\`${name}\`)">`
                + `<img src=${ENDPOINT_BASE_URL}img/${pokemon.image} alt="${name}" title="see details">`
                + `<h4><span class="pokemon_name">${name}</span></h4>`
            + `</div>`;
    }

    // when supplied a name, update the pokemon of the day column to show the corresponding pokemon
    // otherwise show a random pokemon in that column
    async function showPokemonOfTheDay(name) {
        const currentPokemonOfTheDay = $("#pokemon_of_the_day_col h4");
        let pokemonOfTheDay;
        if (name)
            pokemonOfTheDay = await getPokemon(name);
        else
            do  // avoiding the randomly selected pokemon to be the same as before
                pokemonOfTheDay = await getPokemon();
            while (currentPokemonOfTheDay && pokemonOfTheDay.name === currentPokemonOfTheDay.innerText);

        $("#pokemon_of_the_day_col").innerHTML =
            `<h2>Pokemon of the day</h2>`
            + `<img alt="pokemon of the day" title="change pokemon of the day" `
                + `src="${ENDPOINT_BASE_URL}img/${pokemonOfTheDay.image}">`
            + `<h4><span class="pokemon_name">${pokemonOfTheDay.name}</span></h4>`
            + `<p>${pokemonOfTheDay.description}</p>`
            + `<div class="button" id="show_pokemon_of_the_day_menu">Show Details</div>`;

        $("#pokemon_of_the_day_col img")
            .addEventListener("click", await (showPokemonOfTheDay.bind(null, null)));
        $("#show_pokemon_of_the_day_menu")
            .addEventListener("click", async () => await showDetail(pokemonOfTheDay.name));

        sessionStorage.setItem("yihao_web_assignment_pokemon_of_the_day", pokemonOfTheDay.name);
        sessionStorage.setItem("yihao_web_assignment_last_updated", Date.now().toString());
    }

    // update the center column the show the detail page of a the pokemon passed in
    async function showDetail(pokemonName) {
        const pokemon = await getPokemon(pokemonName)
            , weak = pokemon.opponents.weak_against
            , strong = pokemon.opponents.strong_against
            , classes = pokemon.classes
            , skills = pokemon.signature_skills
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
            }
            , classPill = async (clazz) => {
                const color = await (await fetch(ENDPOINT_BASE_URL + "keyword?keyword=" + clazz)).json();
                return `<p><span class="pill" style="`
                    + `color: ${color.foreground};background-color: ${color.background}">`
                    + `${clazz}</span></p>`;
            }
            , getClassesPanel = async () => {
                let classesPanel = "<h3>Class List</h3>";
                for (const clazz of classes)
                    classesPanel += await classPill(clazz);
                return classesPanel;
            }
            , getSkillsPanel = () => {
                let skillsPanel = "<h3>Signature Moves</h3>";
                for (const skill of skills)
                    skillsPanel += `<p><span class="pill">${skill}</span></p>`;
                return skillsPanel;
            };

        panel_container.innerHTML =
              `<div class="flanking">${await getWeakPanel()}</div>`
            + `<div id="detail"><h2 class="pokemon_name">${pokemon.name}</h2>`
                + `<img src="${ENDPOINT_BASE_URL}img/${pokemon.image}" alt="${pokemonName}">`
                + `<p>${pokemon.description}</p>`
                + `<div class="attributes">`
                    + `<div class="attributes_list">${await getClassesPanel()}</div>`
                    + `<div class="attributes_list">${getSkillsPanel()}</div>`
                +`</div>`
            + `</div>`
            + `<div class="flanking">${await getStrongPanel()}</div>`;

        panel_container.classList.add("details_expanded");

        sessionStorage.setItem("yihao_web_assignment_detailed", pokemonName);
    }

    // show the pokemon list page in the center column
    async function showPokemons() {
        const pokemons = await (await fetch(ENDPOINT_BASE_URL + "pokemon")).json()
            , panel_container = $("#panel_container");

        panel_container.innerHTML = "";
        panel_container.classList.remove("details_expanded");
        for (const pokemon of pokemons)
            panel_container.innerHTML += await panel(pokemon);

        sessionStorage.removeItem("yihao_web_assignment_detailed");
    }

    // things to be done when the window finishes loading:

    // animation for menu expansion
    $("nav img").addEventListener("click", () => $("#actions_nav").classList.toggle("expand"));

    // bind events to buttons
    $("#show_pokemon_list").onclick =
        $("#show_pokemon_list_menu").onclick =
            showPokemons;

    $("#show_pokemon_of_the_day").onclick =
        $("#show_pokemon_of_the_day_menu").onclick =
            async () => await showDetail($("#pokemon_of_the_day_col h4").innerText);

    $("#load_random_pokemon").onclick =
        $("#load_random_pokemon_menu").onclick =
            async () => await showDetail(await (async () => {
                const current = sessionStorage.getItem("yihao_web_assignment_detailed");
                let name;
                do
                    name = (await getPokemon()).name;
                while (current && name === current);
                return name;
            })());

    // display the pokemon of the day column, check the session first
    const pokemonOfTheDay = sessionStorage.getItem("yihao_web_assignment_pokemon_of_the_day");
    if (pokemonOfTheDay
        && Date.now() - sessionStorage.getItem("yihao_web_assignment_last_updated") < 4 * 60 * 60 * 1000)
        await showPokemonOfTheDay(pokemonOfTheDay);
    else
        await showPokemonOfTheDay();

    // display the center column, check the session first
    const currentPokemon = sessionStorage.getItem("yihao_web_assignment_detailed");
    if (currentPokemon)
        await showDetail(currentPokemon);
    else
        await showPokemons();

    // expose the showDetail function to the global environment, to be used in "onclick" attribute
    window.showDetail = showDetail;

})(window);