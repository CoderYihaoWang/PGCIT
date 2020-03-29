(function pizzaOrderApp() {

    new Customer("Cameron").from("Waikato").orders("Cheese")

    new Customer("Yu-Cheng").from("Auckland").orders("Crazy")

    new Customer("Yihao").from("Wellington").orders("Meat & Meat")
                         .from("Waikato").orders("Cheese")
                         .from("Auckland").orders("Meat & Meat")
    
    // add more orders here

})()

function from(store) {
    const customer = this
    let dough, cheese, veggies

    switch (store) {
        case "Waikato":
            dough = new Ingredient("Thick crust dough")
            cheese = new Ingredient("Reggiano cheese")
            veggies = []
            break
        case "Auckland":
            dough = new Ingredient("Thin pan dough")
            cheese = new Ingredient("Mozzarella cheese")
            veggies = []
            break
        case "Wellington":
            dough = new Ingredient("Square dough")
            cheese = new Ingredient("Feta cheese")
            veggies = [new Ingredient("Tomato"), new Ingredient("Potato")]
            break
        // add more stores here 

    }

    return { orders: function(type) {
                const bake = () => console.log("Bake for 25 minutes at 350")
                    , cut = () => console.log("Cutting the pizza into diagonal slices")
                    , box = () => console.log("Place pizza in official PizzaStore box")

                switch (type) {
                    case "Cheese":
                        console.log(`Preparing ${store} Style ${type} Pizza`)
                        console.log(dough.toString())
                        console.log(cheese.toString())
                        veggies.forEach(v => console.log(v.toString()))
                        bake();
                        cut();
                        box();
                        break
                    case "Crazy": 
                        console.log(`Preparing ${store} Style ${type} Pizza`)
                        console.log(dough.toString())
                        console.log(cheese.toString())
                        veggies.forEach(v => console.log(v.toString()))
                        console.log("Super HOT pepper is added")
                        bake();
                        cut();
                        box();
                        break
                    case "Meat & Meat":
                        console.log(`Preparing ${store} Style ${type} Pizza`)
                        console.log(dough.toString())
                        console.log(cheese.toString())
                        veggies.forEach(v => console.log(v.toString()))
                        console.log("So much meat is added")
                        bake();
                        cut();
                        box();
                        break
                    // add more pizza types here

                }

                console.log("-----------------------------")
                console.log(`${customer} ordered a ${store} Style ${type} Pizza`)
                console.log("\n")

                return customer
            }
    }
}

function Customer(name) {
    this.from = from
    this.toString = () => name
}

function Ingredient(name) {
    this.toString = () => name + " is ready"
}