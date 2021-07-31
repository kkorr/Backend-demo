async function startAdmin() {
    const response = await fetch("/api/cart")
    const data = await response.json();
    console.log(data)
    if(document.getElementById("bodyMainTable")) {
        data.forEach(user=> insertCartItemRow(user))
    }
}
startAdmin()

function insertCartItemRow() {

}