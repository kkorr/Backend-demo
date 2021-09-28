function saveShop() {
    console.log("adding shop")

    $.ajax('http://localhost:8888/api/shop/add', {
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            name: document.getElementById('nameAddShopSave').value,
            email: document.getElementById('emailAddShopSave').value,
            phone: document.getElementById('phoneAddShopSave').value,
            description: document.getElementById('descriptionAddShopSave').value,
            location: document.getElementById('locationAddShopSave').value,
            logo: {
                picture: getLogoArray(),
                url: getUrl()
            }
        })
    })

    function getLogoArray() {
            let image = $('#imageAddShopSave').prop('files')[0];
            const reader = new FileReader();
            let fileByteArray = [];
            reader.readAsArrayBuffer(image);
            reader.onloadend = (evt) => {
                if (evt.target.readyState == FileReader.DONE) {
                    var arrayBuffer = evt.target.result,
                        array = new Uint8Array(arrayBuffer);
                    for (var i = 0; i < array.length; i++) {
                        if (array[i] <= 127) {
                            fileByteArray.push(array[i]);
                        } else {
                            fileByteArray.push(array[i] - 256);
                        }

                    }
                }
                return fileByteArray;
            }
    }

    function getUrl() {
        let image =$('#imageAddShopSave').prop('files')[0];
        return URL.createObjectURL(image).toString();
    }
}
