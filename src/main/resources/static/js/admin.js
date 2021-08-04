$(document).ready(function () {
    updateShops();
    updateUsers();
    updateItems();
    updateCountries();
    updateCities();
    updateAddresses();
    updateCategories();

    $('.logout').on('click', function (event) {
        logout();
    })
});


function updateShops() {
    console.log("updateShops");
    let shopTableBody = $("#shop_table_body")

    shopTableBody.children().remove();

    fetch("/api/admin/allshops")
        .then((response) => {
            response.json().then(data => data.forEach(function (item, i, data) {
                let TableRow = createTableShop(item);
                shopTableBody.append(TableRow);
            }));
        }).catch(error => {
        console.log(error);
    });
}

function createTableShop(shop) {

    return `<tr id="shop_table_row">

            <td>${shop.id}</td>
            <td>${shop.name}</td>
            <td>${shop.email}</td>
            <td>${shop.phone}</td>
            <td>${shop.location}</td>
            <td>${shop.rating}</td>
            <td>${shop.username}</td>
            
            <td>
            <a href="/seller/api/${shop.id}/settings" class="btn btn-info shopItemsButton">Список товаров</a>
            </td>
            
            <td>
            <a href="/seller/api/${shop.id}/settings" class="btn btn-info shopEditButton">Редактировать</a>
            </td>
            <td>
            <a  href="/api/shop/${shop.id}" class="btn btn-danger shopDeleteButton">Удалить</a>
            </td>
        </tr>`;
}

function updateUsers() {
    console.log("updateUsers");
    let userTableBody = $("#user_table_body")

    userTableBody.children().remove();

    fetch("/api/admin/allusers")
        .then((response) => {
            response.json().then(data => data.forEach(function (item, i, data) {
                let TableRow = createTableUser(item);
                userTableBody.append(TableRow);
            }));
        }).catch(error => {
        console.log(error);
    });
}

function createTableUser(user) {
    let userRole = "";
    user.roles.forEach(x => userRole += " " + x.name)

    return `<tr id="user_table_row">
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.phone}</td>
            <td>${user.age}</td>
            <td>${user.gender}</td>
            <td>${userRole}</td>
            
            <td>
            <a href="/api/user/${user.id}" class="btn btn-info userEditButton">Edit</a>
            </td>
            <td>
            <a  href="/api/user/${user.id}" class="btn btn-danger userDeleteButton">Delete</a>
            </td>
        </tr>`;
}

function updateItems() {
    console.log("updateItems");
    let itemTableBody = $("#item_table_body")

    itemTableBody.children().remove();

    fetch("/api/admin/allitems")
        .then((response) => {
            response.json().then(data => data.forEach(function (item, i, data) {
                let TableRow = createTableItem(item);
                itemTableBody.append(TableRow);
            }));
        }).catch(error => {
        console.log(error);
    });
}

function createTableItem(item) {

    return `<tr id="item_table_row">
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td>${item.count}</td>
            <td>${item.rating}</td>
            <td>${item.categories}</td>
            <td>${item.description}</td>
            <td>${item.moderated}</td>
            <td>${item.moderateAccept}</td>
            <td>${item.moderatedRejectReason}</td>
                     
            <td>
            <a href="/api/item/${item.id}" class="btn btn-info itemEditButton">Edit</a>
            </td>
            <td>
            <a  href="/api/item/${item.id}" class="btn btn-danger itemDeleteButton">Delete</a>
            </td>
        </tr>`;
}

function updateCountries() {
    console.log("updateCountries");
    let countryTableBody = $("#country_table_body")

    countryTableBody.children().remove();

    fetch("/api/admin/allcountries")
        .then((response) => {
            response.json().then(data => data.forEach(function (item, i, data) {
                let TableRow = createTableCountry(item);
                countryTableBody.append(TableRow);
            }));
        }).catch(error => {
        console.log(error);
    });
}

function createTableCountry(country) {

    return `<tr id="country_table_row">
            <td>${country.id}</td>
            <td>${country.name}</td>
                     
            <td>
            <a href="/api/country/${country.id}" class="btn btn-info countryEditButton">Edit</a>
            </td>
            <td>
            <a  href="/api/country/${country.id}" class="btn btn-danger countryDeleteButton">Delete</a>
            </td>
        </tr>`;
}

function updateCities() {
    console.log("updateCities");
    let cityTableBody = $("#city_table_body")

    cityTableBody.children().remove();

    fetch("/api/admin/allcities")
        .then((response) => {
            response.json().then(data => data.forEach(function (item, i, data) {
                let TableRow = createTableCity(item);
                cityTableBody.append(TableRow);
            }));
        }).catch(error => {
        console.log(error);
    });
}

function createTableCity(city) {

    return `<tr id="city_table_row">
            <td>${city.id}</td>
            <td>${city.name}</td>
            <td>${city.countries}</td>
                     
            <td>
            <a href="/api/city/${city.id}" class="btn btn-info cityEditButton">Edit</a>
            </td>
            <td>
            <a  href="/api/city/${city.id}" class="btn btn-danger cityDeleteButton">Delete</a>
            </td>
        </tr>`;
}

function updateAddresses() {
    console.log("updateAddresses");
    let addressTableBody = $("#address_table_body")

    addressTableBody.children().remove();

    fetch("/api/admin/alladdresses")
        .then((response) => {
            response.json().then(data => data.forEach(function (item, i, data) {
                let TableRow = createTableAddress(item);
                addressTableBody.append(TableRow);
            }));
        }).catch(error => {
        console.log(error);
    });
}

function createTableAddress(address) {

    return `<tr id="address_table_row">
            <td>${address.id}</td>
            <td>${address.cityIndex}</td>
            <td>${address.country}</td>
            <td>${address.city}</td>
            <td>${address.street}</td>
            <td>${address.house}</td>       
                     
            <td>
            <a href="/api/address/${address.id}" class="btn btn-info addressEditButton">Edit</a>
            </td>
 
             <td>
            <a href="/api/address/${address.id}" class="btn btn-danger addressDeleteButton">Delete</a>
            </td>           

        </tr>`;
}

function updateCategories() {
    console.log("updateCategories");
    let categoryTableBody = $("#category_table_body")

    categoryTableBody.children().remove();

    fetch("/api/admin/allcategories")
        .then((response) => {
            response.json().then(data => data.forEach(function (item, i, data) {
                let TableRow = createTableCategory(item);
                categoryTableBody.append(TableRow);
            }));
        }).catch(error => {
        console.log(error);
    });
}

function createTableCategory(category) {

    return `<tr id="category_table_row">
            <td>${category.id}</td>
            <td>${category.name}</td>

            <td>
            <a href="/api/category/${category.id}" class="btn btn-info categoryEditButton">Edit</a>
            </td>
            <td>
            <a href="/api/category/${category.id}" class="btn btn-danger categoryDeleteButton">Delete</a>
            </td>
        </tr>`;
}

function logout() {
    document.location.replace("/logout");
}

document.addEventListener('click', function (event) {
    event.preventDefault()

    //delete address
    if ($(event.target).hasClass('addressDeleteButton')) {

            let href = $(event.target).attr("href");

            fetch(href, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json;charset=utf-8"
                }
            }).then((response) => { if (response.ok)
            {updateAddresses(); updateUsers();} else {alert('Невозможно удалить адрес')}
            });

    }

    //delete city
    if ($(event.target).hasClass('cityDeleteButton')) {

            let href = $(event.target).attr("href");

            fetch(href, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json;charset=utf-8"
                }
            }).then((response) => {if (response.ok)
            {updateCities(); updateAddresses();} else {alert('Невозможно удалить город')}
            });

    }

    //delete country
    if ($(event.target).hasClass('countryDeleteButton')) {

            let href = $(event.target).attr("href");

            fetch(href, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json;charset=utf-8"
                }
            }).then((response) => {if (response.ok)
            {updateCountries(); updateCities();} else {alert('Невозможно удалить страну')}
            });

    }

    //delete item
    if ($(event.target).hasClass('itemDeleteButton')) {

            let href = $(event.target).attr("href");

            fetch(href, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json;charset=utf-8"
                }
            }).then((response) => {if (response.ok)
            {updateItems()} else {alert('Невозможно удалить товар')}
            });

    }

    //delete category
    if ($(event.target).hasClass('categoryDeleteButton')) {

            let href = $(event.target).attr("href");

            fetch(href, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json;charset=utf-8"
                }
            }).then((response) => {if (response.ok)
            {updateCategories(); updateItems()} else {alert('Невозможно удалить товарную категорию')}
            });

    }

    //delete shop
    if ($(event.target).hasClass('shopDeleteButton')) {

            let href = $(event.target).attr("href");

            fetch(href, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json;charset=utf-8"
                }
            }).then((response) => {if (response.ok)
            {updateShops()} else {alert('Невозможно удалить магазин')}
            });

    }

    //delete user
    if ($(event.target).hasClass('userDeleteButton')) {

            let href = $(event.target).attr("href");

            fetch(href, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json;charset=utf-8"
                }
            }).then((response) => {if (response.ok)
            {updateUsers(); updateShops()}  else {alert('Невозможно удалить пользователя')}
            });

    }

});

