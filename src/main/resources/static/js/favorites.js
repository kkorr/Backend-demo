async function getFavoriteItems() {
    const response = await fetch("/api/favorites/items")
    const data = await response.json();
    console.log(data)
    if(document.getElementById("favoriteItems")) {
        data.forEach(item=> insertFavoriteItemRow(item))
    }

}
async function getFavoriteShops() {
    const response = await fetch("/api/favorites/shops")
    const data = await response.json();
    console.log(data)
    if(document.getElementById("favoriteShops")) {
        data.forEach(shop=> insertFavoriteShopRow(shop))
    }
}
getFavoriteItems();
getFavoriteShops();

document.getElementById("cartFromFavoritesButton").addEventListener('click',
    addToCartFromFavorites());

function insertFavoriteItemRow(item){
    let i = {
        id: item.id,
        name: item.name,
        price: item.price,
        count: item.count,
        categories: item.categories,
        images: item.images,
        description: item.description,
        shopId: item.shopId
    };
    document.querySelector('#favoriteItems').insertAdjacentHTML('beforeend', `
    <div class="container" id="favoriteItem${i.id}" name="favoriteItemRow">
        <div class="row">
            <h5>${i.name}</h5>
        </div>
        <div class="row">
            <div class="col-1">
                <input type="checkbox" id="checkBox${i.id}"> 
            </div>
            <div class="col-3">
              <img src="${i.images[0]}" class="img-fluid"/>
            </div>
            <div class="col-3">
              <p>Цена:</p>
              <p>${i.price}</p>
            </div>
            <div class="col-4">
                <p>${i.description}</p>
            </div>
            <div class="col-1">
                <a href="#" onclick="collapseFavoriteItem(${i.id}); deleteFavoriteItem(${i.id})"><i class="fas fa-minus-circle" style="color: red" onclick=""></i></a>
            </div>
        </div>
        <div class="row m-1">&nbsp;</div>
    </div>
    `)

}
function insertFavoriteShopRow(shop) {
    let s = {
        id: shop.id,
        name: shop.name,
        logo: shop.logo,
        description: shop.description,
    };
    document.querySelector('#favoriteShops').insertAdjacentHTML('beforeend', `
    <div class="container" id="favoriteShop${s.id}" name="favoriteShopRow">
        <div class="row">
            <h5>${s.name}</h5>
        </div>
        <div class="row">
            <div class="col-1">
                <input type="checkbox" id="checkBox${s.id}"> 
            </div>
            <div class="col-4">
              <img src="${s.logo}" class="img-fluid"/>
            </div>
            <div class="col-6">
                <p>${s.description}</p>
            </div>
            <div class="col-1">
                <a href="#" onclick="collapseFavoriteShop(${s.id}); deleteFavoriteShop(${s.id})"><i class="fas fa-minus-circle" style="color: red" onclick=""></i></a>
            </div>
        </div>
        <div class="row m-1">&nbsp;</div>
    </div>
    `)
}

function collapseFavoriteShop(id){
    let idHTML = "favoriteShop"+id;
    document.getElementById(idHTML).remove();
}
async function deleteFavoriteShop(id){
    let url = new URL("http://localhost:8888/api/favorites/shops/delete/"+id);

    const response = await fetch(url, {
        headers: { "Content-Type": "application/json; charset=utf-8" },
        method: 'DELETE'
    })
}
function collapseFavoriteItem(id){
    let idHTML = "favoriteItem"+id;
    document.getElementById(idHTML).remove();
}
async function deleteFavoriteItem(id){
    let url = new URL("http://localhost:8888/api/favorites/items/delete/"+id);

    const response = await fetch(url, {
        headers: { "Content-Type": "application/json; charset=utf-8" },
        method: 'DELETE'
    })
}

async function addToCartFromFavorites() {
    let quant = document.getElementById("itemQuant").value;
    let url = new URL("http://localhost:8888/api/cart/add")
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json; charset=utf-8",
        },
        body: JSON.stringify({
            quantity: quant,
            item: {
                id: itemId
            },
            shop: {
                id: shopId
            },
        })
    })
}