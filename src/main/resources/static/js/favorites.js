async function getFavoriteItems() {
    const response = await fetch("/api/favorites")
    const data = await response.json();
    console.log(data)
    if(document.getElementById("favoriteItems")) {
        data.forEach(item=> insertFavoriteItemRow(item))
    }
}
getFavoriteItems();

function insertFavoriteItemRow(item){
    let i = {
        id: item.id,
        name: item.name,
        price: item.price,
        count: item.count,
        categories: item.categories,
        images: item.images,
        description: item.description,
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

function collapseFavoriteItem(id){
    let idHTML = "favoriteItem"+id;
    document.getElementById(idHTML).remove();
}
async function deleteFavoriteItem(id){

}
function addToCartFromFavorites() {

}