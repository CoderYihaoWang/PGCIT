(function pizzaOrderApp() {
    new Customer("Cameron").orders("Waikato")("Cheese")
    new Customer("Yu-Cheng").orders("Auckland")("Crazy")
    new Customer("Yihao").orders("Wellington")("Meat & Meat")
})()

function orders(store) {
    const that = this
    const factory = {}

    switch (store) {
        case "Waikato":
            factory.dough = new Ingredient("Thick crust dough")
            factory.cheese = new Ingredient("Reggiano cheese")
            factory.veggies = []
            break
        case "Auckland":
            factory.dough = new Ingredient("Thin pan dough")
            factory.cheese = new Ingredient("Mozzarella cheese")
            factory.veggies = []
            break
        case "Wellington":
            factory.dough = new Ingredient("Square dough")
            factory.cheese = new Ingredient("Feta cheese")
            factory.veggies = [new Ingredient("Tomato"), new Ingredient("Potato")]
        // add more stores here 

    }

    return function(type) {
        const pizza = {
            bake: () => console.log("Bake for 25 minutes at 350"),
            cut: () => console.log("Cutting the pizza into diagonal slices"),
            box: () => console.log("Place pizza in official PizzaStore box")
        }

        switch (type) {
            case "Cheese":
                pizza.prepare = () => {
                    console.log(`Preparing ${store} Style ${type} Pizza`)
                    console.log(factory.dough.toString())
                    console.log(factory.cheese.toString())
                    for (const v of factory.veggies)
                        console.log(v.toString())
                    pizza.bake();
                    pizza.cut();
                    pizza.box();
                }
                break
            case "Crazy": 
                pizza.prepare = () => {
                    console.log(`Preparing ${store} Style ${type} Pizza`)
                    console.log(factory.dough.toString())
                    console.log(factory.cheese.toString())
                    for (const v of factory.veggies)
                        console.log(v.toString())
                    console.log("Super HOT pepper is added")
                    pizza.bake();
                    pizza.cut();
                    pizza.box();
                }
                break
            case "Meat & Meat":
                pizza.prepare = () => {
                    console.log(`Preparing ${store} Style ${type} Pizza`)
                    console.log(factory.dough.toString())
                    console.log(factory.cheese.toString())
                    for (const v of factory.veggies)
                        console.log(v.toString())
                    console.log("So much meat is added")
                    pizza.bake();
                    pizza.cut();
                    pizza.box();
                }
                break
            // add more pizza types here

        }

        pizza.prepare()
        console.log("-----------------------------")
        console.log(`${that} ordered a ${store} Style ${type} Pizza`)
        console.log("\n")
    }
}

function Customer(name) {
    this.orders = orders
    this.toString = () => name
}

function Ingredient(name) {
    this.toString = () => name + " is ready"
}