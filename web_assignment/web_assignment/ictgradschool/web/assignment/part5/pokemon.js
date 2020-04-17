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
            + `<p>${pokemonOfTheDay.description}</p>`
            + `<div class="button" id="show_pokemon_of_the_day_menu">Show Details</div>`;
        $("#pokemon_of_the_day_col img").addEventListener("click", await showPokemonOfTheDay);
        $("#show_pokemon_of_the_day_menu").onclick =
            async () => await showDetail(pokemonOfTheDay.name);
    };

    await showPokemonOfTheDay();

    // part 3 & 4
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
                    skillsPanel += `<p>${skill}</p>`;
                return skillsPanel;
            };

        panel_container.innerHTML =
              `<div class="flanking">${await getWeakPanel()}</div>`
            + `<div id="detail"><h2>${pokemon.name}</h2>`
                + `<img src="${ENDPOINT_BASE_URL}img/${pokemon.image}" alt="${pokemonName}">`
                + `<p>${pokemon.description}</p>`
                + `<div class="attributes">`
                    + `<div class="attributes_list">${await getClassesPanel()}</div>`
                    + `<div class="attributes_list">${getSkillsPanel()}</div>`
                +`</div>`
            + `</div>`
            + `<div class="flanking">${await getStrongPanel()}</div>`;

        panel_container.classList.add("details_expanded");
    };

    window.showPokemons = async () => {
        const pokemons = await (await fetch(ENDPOINT_BASE_URL + "pokemon")).json()
            , panel_container = $("#panel_container");
        panel_container.innerHTML = "";
        panel_container.classList.remove("details_expanded");
        for (const pokemon of pokemons)
            panel_container.innerHTML += await panel(pokemon);
    };

    await showPokemons();

    // part 5
    $("#show_pokemon_list").onclick = $("#show_pokemon_list_menu").onclick = showPokemons;

    const randomName = async () =>
        (await (await fetch(ENDPOINT_BASE_URL + "pokemon?random=random")).json()).name;

    $("#load_random_pokemon").onclick = $("#load_random_pokemon_menu").onclick =
        async () => await showDetail(await randomName());

    $("#show_pokemon_of_the_day").onclick = $("#show_pokemon_of_the_day_menu").onclick =
        async () => await showDetail($("#pokemon_of_the_day_col h4").innerText);

})(window);