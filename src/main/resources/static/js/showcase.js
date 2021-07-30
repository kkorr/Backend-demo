$(document).ready(function(){
    $('#popular_product_btn').on('click', function (e) {
        e.preventDefault();
        toggleNavBar($(this));
        $('#main_title').html('Популярные товары:');
    })
    $('#all_product_btn').on('click', function (e) {
        e.preventDefault();
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
function getAllProduct() {

}