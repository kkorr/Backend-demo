$(document).ready(function(){
    const SHOP_ID = $("shop_id").attr("value");
    $('#popular_product_btn').on('click', function (e) {
        e.preventDefault();
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
        toggleNavBar($(this));
        $('#main_title').html('Описание магазина:');
    })

});

function toggleNavBar(e) {
    $('.active').removeClass('active').addClass('link-dark');
    $(e).addClass('active').removeClass('link-dark');
}
async function getAllItems(id) {
    let url = "/showcase/" + id + "/items";
    let response = await fetch(url);
    if(response.ok) {
        let json = await response.json();
        console.log(json);
    }
}