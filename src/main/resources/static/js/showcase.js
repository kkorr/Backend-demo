
$(document).ready(function(){
    /*id магазина*/
    const SHOP_ID = $('#shop_id').attr("value");

    getAllItems(SHOP_ID);

    $('#popular_product_btn').on('click', function (e) {
        e.preventDefault();
        getPopularItems(SHOP_ID);
        toggleNavBar($(this));
        $('#main_title').html('Популярные товары:');
    })
    $('#all_product_btn').on('click', function (e) {
        e.preventDefault();
        getAllItems(SHOP_ID);
        toggleNavBar($(this));
        $('#main_title').html('Все товары:');
    })
    $('#market_description_btn').on('click', function (e) {
        e.preventDefault();
        getShopDescription(SHOP_ID);
        toggleNavBar($(this));
        $('#main_title').html('Описание магазина:');
    })

});

/*переключает стили кнопок в navBAR*/
function toggleNavBar(e) {
    $('.active').removeClass('active').addClass('link-dark');
    $(e).addClass('active').removeClass('link-dark');
}
/*обновляет контент на все товары по id магазина*/
async function getAllItems(id) {
    let url = "/showcase/" + id + "/items";
    let response = await fetch(url);
    if(response.ok) {
        let json = await response.json();
        let insert_content = "";
        for(let i = 0; i < json.length; i++) {
            insert_content += '<div class="card" style="width: 18rem; margin-right: 15px; margin-top: 20px;">\n' +
                '                        <img src="' + json[i].images[0] + '" class="card-img-top" alt="...">\n' +
                '                        <div class="card-body">\n' +
                '                            <h5 class="card-title">' + json[i].name + '</h5>\n' +
                '                            <p class="card-text">Описание товара:</p>\n' +
                '                            <p class="card-text">' + json[i].description + '</p>\n' +
                '                            <a href="' + json[i].id + '" class="btn btn-danger">Страница товара</a>\n' +
                '                        </div>\n' +
                '               </div>'
        }
        $('#content_container').html("");
        $('#content_container').html(insert_content);
    }
}

/*обновляет контент на популярные товары*/
async function getPopularItems(id) {
    let url = "/showcase/" + id + "/popular";
    let response = await fetch(url);
    if(response.ok) {
        let json = await response.json();
        let insert_content = "";
        for(let i = 0; i < json.length; i++) {
            insert_content += '<div class="card" style="width: 18rem; margin-right: 15px; margin-top: 20px;">\n' +
                '                        <img src="' + json[i].images[0] + '" class="card-img-top" alt="...">\n' +
                '                        <div class="card-body">\n' +
                '                            <h5 class="card-title">' + json[i].name + '</h5>\n' +
                '                            <p class="card-text">Описание товара:</p>\n' +
                '                            <p class="card-text">' + json[i].description + '</p>\n' +
                '                            <a href="' + json[i].id + '" class="btn btn-danger">Страница товара</a>\n' +
                '                        </div>\n' +
                '               </div>'
        }
        $('#content_container').html("");
        $('#content_container').html(insert_content);
    }
}

/*обновляет контент на популярные товары*/
async function getShopDescription(id) {
    let url = "/showcase/" + id + "/description";
    let response = await fetch(url);
    if(response.ok) {
        let json = await response.json();
        let insert_content = '<div><p><h3>' + json.name + '</h3></p> <p>Email: ' + json.email + '</p> <p>Телефон: ' + json.phone + '</p> <p>Город: ' + json.location + '</p> <p>' + json.description + '</p></div>';

        $('#content_container').html("");
        $('#content_container').html(insert_content);
    }
}