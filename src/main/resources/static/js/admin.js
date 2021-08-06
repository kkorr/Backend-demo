$(document).ready(function () {
    updateShops();
    updateUsers();
    updateItems();
    updateCountries();
    updateCities();
    updateAddresses();
    updateCategories();

    //add new shop
    $('.shopInsertModal .shopInsertButton').on('click', function (event) {
        let shop = {
            name:$(".shopInsertModal #name").val(),
            email:$(".shopInsertModal #email").val(),
            phone:$(".shopInsertModal #phone").val(),
            description:$(".shopInsertModal #description").val(),
            location:$(".shopInsertModal #ins_shop_location").val(),
            username:$(".shopInsertModal #ins_shop_username").val()
        }
        console.log(shop)

        fetch("api/shop/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(shop)
        }) .then(() => {$('.shopInsertModal #shopInsertModal').modal('hide');$('input').val(''); updateShops()})
    })

    //save shop
    $('.shopEditModal .shopSaveButton').on('click', function (event) {
        let shop = {
            id:$(".shopEditModal #id").val(),
            name:$(".shopEditModal #name").val(),
            email:$(".shopEditModal #email").val(),
            phone:$(".shopEditModal #phone").val(),
            description:$(".shopEditModal #description").val(),
            location:$(".shopEditModal #edit_shop_location").val(),
            username:$(".shopEditModal #edit_shop_username").val()
        }

        console.log(shop)
        fetch("api/shop/save", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(shop)
        }) .then(() => {$('.shopEditModal #shopEditModal').modal('hide'); $('input').val('');
            updateShops();})
    })


    //add new item
    $('.itemInsertModal .itemInsertButton').on('click', function (event) {
        let item = {
            name:$(".itemInsertModal #name").val(),
            price:$(".itemInsertModal #price").val(),
            count:$(".itemInsertModal #count").val(),
            categories:getCategory("#ins_item_categories"),
            description:$(".itemInsertModal #description").val(),
            discount:$(".itemInsertModal #discount").val(),
            shopId:$(".itemInsertModal #ins_item_shops").val()
        }

        console.log(item)

        fetch("api/item/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(item)
        }) .then(() => {$('.itemInsertModal #itemInsertModal').modal('hide');$('input').val(''); updateItems()})
    })

    //save item
    $('.itemEditModal .itemSaveButton').on('click', function (event) {
        let item = {
            id:$(".itemEditModal #id").val(),
            name:$(".itemEditModal #name").val(),
            price:$(".itemEditModal #price").val(),
            count:$(".itemEditModal #count").val(),
            categories:getCategory("#edit_item_categories"),
            description:$(".itemEditModal #description").val(),
            discount:$(".itemEditModal #discount").val(),
            shopId:$(".itemEditModal #edit_item_shops").val(),
            images:$(".itemEditModal #edit_item_images").val(),
            reviews:$(".itemEditModal #edit_item_reviews").val()
        }

        console.log(item)
        fetch("api/item/save", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(item)
        }) .then(() => {$('.itemEditModal #itemEditModal').modal('hide'); $('input').val('');
            updateItems();})
    })


    //add new address
    $('.addressInsertModal .addressInsertButton').on('click', function (event) {
        let address = {
            city:$(".addressInsertModal #ins_address_cities").val(),
            cityIndex: $(".addressInsertModal #index").val(),
            street: $(".addressInsertModal #street").val(),
            house: $(".addressInsertModal #house").val(),
        }

        console.log(address)

        fetch("api/address/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(address)
        }) .then(() => {$('.addressInsertModal #addressInsertModal').modal('hide');$('input').val(''); updateAddresses()})
    })

    //save address
    $('.addressEditModal .addressSaveButton').on('click', function (event) {
        let address = {
            id: $(".addressEditModal #id").val(),
            city:$(".addressEditModal #edit_address_cities").val(),
            cityIndex: $(".addressEditModal #index").val(),
            street: $(".addressEditModal #street").val(),
            house: $(".addressEditModal #house").val(),
        }

        fetch("api/address/save", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(address)
        }) .then(() => {$('.addressEditModal #addressEditModal').modal('hide'); $('input').val('');
            updateAddresses(); updateUsers()})
    })

    //add new city
    $('.cityInsertModal .cityInsertButton').on('click', function (event) {
        let city = {
            name: $(".cityInsertModal #name").val(),
            country:$(".cityInsertModal #ins_city_countries").val()
        }

        console.log(city)

        fetch("api/city/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(city)
        }) .then(() => {$('.cityInsertModal #cityInsertModal').modal('hide');$('input').val(''); updateCities()})
    })

    //save city
    $('.cityEditModal .citySaveButton').on('click', function (event) {
        let city = {
            id: $(".cityEditModal #id").val(),
            name: $(".cityEditModal #name").val(),
            country:$(".cityEditModal #edit_city_countries").val()
        }

        fetch("api/city/save", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(city)
        }) .then(() => {$('.cityEditModal #cityEditModal').modal('hide'); $('input').val('');
            updateCities(); updateAddresses();})
    })


    //add new country
    $('.countryInsertModal .countryInsertButton').on('click', function (event) {
        let country = {
            name: $(".countryInsertModal #name").val(),
        }

        fetch("api/country/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(country)
        }) .then(() => {$('.countryInsertModal #countryInsertModal').modal('hide');$('input').val(''); updateCountries()})
    })

    //save country
    $('.countryEditModal .countrySaveButton').on('click', function (event) {
        let country = {
            id: $(".countryEditModal #id").val(),
            name: $(".countryEditModal #name").val()
        }

        fetch("api/country/save", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(country)
        }) .then(() => {$('.countryEditModal #countryEditModal').modal('hide'); $('input').val('');
            updateCountries(); updateCities(); updateAddresses(); updateShops()})
    })

    //add new category
    $('.categoryInsertModal .catInsertButton').on('click', function (event) {
        let category = {
            name: $(".categoryInsertModal #name").val(),
        }

        fetch("api/category/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(category)
        }) .then(() => {$('.categoryInsertModal #catInsertModal').modal('hide');$('input').val(''); updateCategories()})
    })

    //save category
    $('.categoryEditModal .catSaveButton').on('click', function (event) {
        let category = {
            id: $(".categoryEditModal #id").val(),
            name: $(".categoryEditModal #name").val()
        }

        fetch("api/category/save", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(category)
        }) .then(() => {$('.categoryEditModal #catEditModal').modal('hide'); $('input').val(''); updateCategories(); updateItems()})
    })

    //add new user
    $('.userInsertModal .userInsertButton').on('click', function (event) {
        let user = {
            email: $(".userInsertModal #email").val(),
            username: $(".userInsertModal #username").val(),
            password: $(".userInsertModal #password").val(),
            gender: $(".userInsertModal #ins_user_gender").val(),
            birthday: $(".userInsertModal #birthday").val(),

            phone: $(".userInsertModal #phone").val(),
            firstName: $(".userInsertModal #firstName").val(),
            lastName: $(".userInsertModal #lastName").val(),
            roles:getRole("#ins_user_roles")
        }

        console.log(user)

        fetch("api/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(user)
        }) .then(() => {$('.userInsertModal #userInsertModal').modal('hide');$('input').val(''); updateUsers()})
    })

    //save user
    $('.userEditModal .userSaveButton').on('click', function (event) {
        let user = {
            id: $(".userEditModal #id").val(),
            email: $(".userEditModal #email").val(),
            username: $(".userEditModal #username").val(),
            password: $(".userEditModal #password").val(),
            gender: $(".userEditModal #edit_user_gender").val(),
            birthday: $(".userEditModal #birthday").val(),

            phone: $(".userEditModal #phone").val(),
            firstName: $(".userEditModal #firstName").val(),
            lastName: $(".userEditModal #lastName").val(),
            roles:getRole("#edit_user_roles")
        }

        console.log(user)

        fetch("api/save-user", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(user)
        }) .then(() => {$('.userEditModal #userEditM').modal('hide'); $('input').val(''); updateUsers()})
    })

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
            <a href="/api/admin/shop/${shop.id}/allitems" class="btn btn-info shopItemsButton">Список товаров</a>
            </td>
            
            <td>
            <a href="/api/shop/${shop.id}" class="btn btn-info shopEditButton">Редактировать</a>
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
            <a  href="/api/${user.id}" class="btn btn-danger userDeleteButton">Delete</a>
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

function createTableItemShop(item) {

    return `<tr id="item_table_row">
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td>${item.count}</td>
            <td>${item.rating}</td>
            <td>${item.categories}</td>
            <td>${item.moderated}</td>
            <td>${item.moderateAccept}</td>
            <td>${item.moderatedRejectReason}</td>                     
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
            <td>${city.country}</td>
                     
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

function getRole(address) {
    let data = [];
    $(address).find("option:selected").each(function () {
        data.push({id: $(this).val(),name: $(this).attr("name")
                /* , authority: $(this).attr("name")*/
        })
    });
    return data;
}

function getCountry(address) {
    let data = [];
    $(address).find("option:selected").each(function () {
        return $(this).attr("name");
    });
}

function getCategory(address) {
    let data = [];
    $(address).find("option:selected").each(function () {
         data.push($(this).attr("name"))
    });
    return data;
}




document.addEventListener('click', function (event) {
    event.preventDefault()

    //open shopItemListModal modal form
    if ($(event.target).hasClass('shopItemsButton')) {
        let itemShopTableBody = $("#item_by_shop_table_body")
        itemShopTableBody.children().remove();

        let href = $(event.target).attr("href");
        console.log(href);


        fetch(href)
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    let TableRow = createTableItemShop(item);
                    itemShopTableBody.append(TableRow);
                    console.log(TableRow);
                }));
            }).catch(error => {
            console.log(error);
        });


        $(".shopItemListModal #shopItemListModal").modal();
    }

    //open shopInsertModal modal form
    if ($(event.target).hasClass('addNewShopBtn')) {

        $(".shopInsertModal #ins_shop_location").children().remove();
        $(".shopInsertModal #ins_shop_username").children().remove();

        fetch("/api/admin/allcountries")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#ins_shop_location")
                        .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });

        fetch("/api/admin/allusers")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#ins_shop_username")
                        .append("<option name=\""+item.username+"\" value=\""+item.username+"\" "
                            +" label=\""+item.username+"\" "
                            +">"+item.username+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });


        $(".shopInsertModal #shopInsertModal").modal();
    }

    //open shopEdit modal form
    if ($(event.target).hasClass('shopEditButton')) {

        $(".shopEditModal #edit_shop_location").children().remove();
        $(".shopEditModal #edit_shop_username").children().remove();

        fetch("/api/admin/allcountries")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#edit_shop_location")
                        .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });

        fetch("/api/admin/allusers")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#edit_shop_username")
                        .append("<option name=\""+item.username+"\" value=\""+item.username+"\" "
                            +" label=\""+item.username+"\" "
                            +">"+item.username+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });


        let href = $(event.target).attr("href");
        console.log(href);

        $.get(href, function (shop) {
            $('.shopEditModal #id').val(shop.id);
            $('.shopEditModal #name').val(shop.name);
            $('.shopEditModal #email').val(shop.email);
            $('.shopEditModal #phone').val(shop.phone);
            $('.shopEditModal #description').val(shop.description);
            $('.shopEditModal #edit_shop_location').val(shop.location);
            $('.shopEditModal #edit_shop_username').val(shop.username);
        });

        $(".shopEditModal #shopEditModal").modal();
    }


    //open itemInsertModal modal form
    if ($(event.target).hasClass('addNewItemBtn')) {

        $(".itemInsertModal #ins_item_categories").children().remove();
        $(".itemInsertModal #ins_item_shops").children().remove();

        fetch("/api/admin/allcategories")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#ins_item_categories")
                        .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });

        fetch("/api/admin/allshops")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#ins_item_shops")
                        .append("<option name=\""+item.name+"\" value=\""+item.id+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });



        $(".itemInsertModal #itemInsertModal").modal();
    }

    //open itemEdit modal form
    if ($(event.target).hasClass('itemEditButton')) {

        $(".itemEditModal #edit_item_categories").children().remove();
        $(".itemEditModal #ins_item_shops").children().remove();
/*        $(".itemEditModal #ins_item_images").children().remove();
        $(".itemEditModal #ins_item_reviews").children().remove();*/

        fetch("/api/admin/allcategories")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#edit_item_categories")
                        .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });

        fetch("/api/admin/allshops")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#edit_item_shops")
                        .append("<option name=\""+item.name+"\" value=\""+item.id+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });


        let href = $(event.target).attr("href");
        console.log(href);

        $.get(href, function (item) {
            $('.itemEditModal #id').val(item.id);
            $('.itemEditModal #name').val(item.name);
            $('.itemEditModal #price').val(item.price);
            $('.itemEditModal #count').val(item.count);
            $('.itemEditModal #edit_item_categories').val(item.categories);
            $('.itemEditModal #description').val(item.description);
            $('.itemEditModal #discount').val(item.discount);
            $('.itemEditModal #edit_item_shops').val(item.shop);
            $('.itemEditModal #edit_item_images').val(item.images);
            $('.itemEditModal #edit_item_reviews').val(item.reviews);

        });

        $(".itemEditModal #itemEditModal").modal();
    }

    //open addressInsertModal modal form
    if ($(event.target).hasClass('addNewAddressBtn')) {

        $(".addressInsertModal #ins_address_cities").children().remove();

        fetch("/api/admin/allcities")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#ins_address_cities")
                        .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });


        $(".addressInsertModal #addressInsertModal").modal();
    }

    //open addressEdit modal form
    if ($(event.target).hasClass('addressEditButton')) {

        $(".addressEditModal #edit_address_cities").children().remove();

        fetch("/api/admin/allcities")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#edit_address_cities")
                        .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });

        let href = $(event.target).attr("href");
        console.log(href);

        $.get(href, function (address) {
            $('.addressEditModal #id').val(address.id);
            $('.addressEditModal #edit_address_cities').val(address.city);
            $('.addressEditModal #index').val(address.cityIndex);
            $('.addressEditModal #street').val(address.street);
            $('.addressEditModal #house').val(address.house);
        });

        $(".addressEditModal #addressEditModal").modal();
    }


    //open cityInsertModal modal form
    if ($(event.target).hasClass('addNewCityBtn')) {

        $(".cityInsertModal #ins_city_countries").children().remove();

        fetch("/api/admin/allcountries")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#ins_city_countries")
                        .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });


        $(".cityInsertModal #cityInsertModal").modal();
    }

    //open cityEdit modal form
    if ($(event.target).hasClass('cityEditButton')) {

        $(".cityEditModal #edit_city_countries").children().remove();

        fetch("/api/admin/allcountries")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#edit_city_countries")
                        .append("<option name=\""+item.name+"\" value=\""+item.name+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });

        let href = $(event.target).attr("href");
        console.log(href);

        $.get(href, function (city) {
            $('.cityEditModal #id').val(city.id);
            $('.cityEditModal #name').val(city.name);
            $('.cityEditModal #edit_city_countries').val(city.country);
        });

        $(".cityEditModal #cityEditModal").modal();
    }


    //open countryInsertModal modal form
    if ($(event.target).hasClass('addNewCountryBtn')) {
        $(".countryInsertModal #countryInsertModal").modal();
    }

    //open countryEdit modal form
    if ($(event.target).hasClass('countryEditButton')) {

        let href = $(event.target).attr("href");
        console.log(href);

        $.get(href, function (country) {
            $('.countryEditModal #id').val(country.id);
            $('.countryEditModal #name').val(country.name);
        });

        $(".countryEditModal #countryEditModal").modal();
    }

    //open categoryInsertModal modal form
    if ($(event.target).hasClass('addNewCatBtn')) {
        $(".categoryInsertModal #catInsertModal").modal();
    }

    //open categoryEdit modal form
    if ($(event.target).hasClass('categoryEditButton')) {

        let href = $(event.target).attr("href");
        console.log(href);

        $.get(href, function (category) {
            $('.categoryEditModal #id').val(category.id);
            $('.categoryEditModal #name').val(category.name);
        });

        $(".categoryEditModal #catEditModal").modal();
    }

    //open insertUser modal form
    if ($(event.target).hasClass('addNewUserBtn')) {
        $(".userInsertModal #ins_user_roles").children().remove();

        fetch("/api/admin/allroles")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#ins_user_roles")
                        .append("<option name=\""+item.name+"\" value=\""+item.id+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });

        $(".userInsertModal #userInsertModal").modal();
    }

    //open editUser modal form
    if ($(event.target).hasClass('userEditButton')) {
        $(".userEditModal #edit_user_roles").children().remove();

        fetch("/api/admin/allroles")
            .then((response) => {
                response.json().then(data => data.forEach(function (item, i, data) {
                    $("#edit_user_roles")
                        .append("<option name=\""+item.name+"\" value=\""+item.id+"\" "
                            +" label=\""+item.name+"\" "
                            +">"+item.name+"</option>");
                }));
            }).catch(error => {
            console.log(error);
        });

        let href = $(event.target).attr("href");
        console.log(href);

        $.get(href, function (user) {

            console.log(user.id);
            $('.userEditModal #id').val(user.id);
            $('.userEditModal #email').val(user.email);
            $('.userEditModal #username').val(user.username);
            $('.userEditModal #password').val(user.password);
            $('.userEditModal #gender').val(user.gender).prop('selected', true);
            $('.userEditModal #birthday').val(user.birthday);

            $('.userEditModal #phone').val(user.phone);
            $('.userEditModal #firstName').val(user.firstName);
            $('.userEditModal #lastName').val(user.lastName);


            const user_roles = user.roles;

            $('.userEditModal #edit_user_roles option').each(function (index, item) {

                var option = $(item);

                console.log('get roles ' + option.val());

                var a = 0;
                for (let i = 0; i < user_roles.length; i++) {
                    if (option.val() == user_roles[i].id) {
                        a = 1;
                    }
                }

                if (a == 1) {
                    console.log('option selected ' + option.val());
                    option.prop('selected', true);
                } else {
                    console.log('option not selected ' + option.val());
                    option.prop('selected', false);
                }
            });

        });


        $(".userEditModal #userEditM").modal();
    }

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
            {updateUsers(); updateShops(); updateAddresses()}  else {alert('Невозможно удалить пользователя')}
            });

    }


});

