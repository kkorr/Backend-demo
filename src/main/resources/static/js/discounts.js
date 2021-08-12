async function getDiscounts() {
    const response = await fetch("/api/discounts")
    const data = await response.json();
    if(document.getElementById("discounts")) {
        data.forEach(discount=> insertDiscountCard(discount))
    }
}
getDiscounts();

async function insertDiscountCard(discount) {
    let d = {
        id: discount.id,
        minOrder: discount.minOrder,
        percentage: discount.percentage,
        fixedDiscount: discount.fixedDiscount,
        shopId: discount.shopId,
        userId: discount.userId
    }

    let shop = await getShop(d.shopId);
    let s = {
        name: shop.name,
        description: shop.description,
        logo: shop.logo
    }

    let disc;
    if (d.fixedDiscount === 0) {
        disc = d.percentage + "% скидка";
    } else {
        disc = "Скидка в " + d.fixedDiscount + " рублей";
    }
    document.querySelector('#discounts').insertAdjacentHTML('beforeend', `
        <div class="card border-light m-3" id="discountCard${d.id}" style="max-width: 20rem;">
            <div class="card-header">
                <img class="card-img-top img-fluid p-1" src="${s.logo}" alt="Card image cap">
            </div>
            <div class="card-body border-light">
                <h5 class="card-title">${s.name}</h5>
                <p class="card-text">${disc} при покупки на сумму свыше ${d.minOrder} рублей</p>
            </div>
        </div>
        `);
}

async function getShop(id) {
    const response = await fetch("http://localhost:8888/api/shop/"+id)
    const data = await response.json();
    return data;
}

// Modal for discount creation
let modal = null;
function showModal() {
    if(modalEditWrap != null) {
        modalEditWrap.remove();
    }
    modal=document.createElement('div');
    modal.innerHTML=`
    <div class="modalForm">
        <form method="put" onsubmit='createDiscount("${discount}, ${shops})'>
        <div class="modal fade align-middle" id="editF" tabindex="-1">
                <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Предоставить скидку</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body text-center fw-bold">
                            <div class="d-flex justify-content-center">
                                <div class="form-group w-50 mb-3 text-center align-items-center">
                                    <label for="id" class="form-label">Минимальная сумма заказа</label>
                                    <input type="number" class="form-control input-sm" id="orderModal" name="id" value="${id}" readonly/>
                                </div>
                            </div>
                            <ch
                            <div class="d-flex justify-content-center">
                                <div class="form-group w-50 mb-3">
                                    <label for="first_name" class="form-label">First Name</label>
                                    <input type="text" class="form-control" id="first_nameEdit" name="first_name" value="${first_name}" />
                                </div>
                            </div>
                            <div class="d-flex justify-content-center">
                                <div class="form-group w-50 mb-3">
                                    <label for="last_name" class="form-label">Last Name</label>
                                    <input type="text" class="form-control" id="last_nameEdit" name="last_name" value="${last_name}" />
                                </div>
                            </div>
                            <div class="d-flex justify-content-center">
                                <div class="form-group w-50 mb-3">
                                    <label for="roles" class="form-label">Магазины</label>
                                        <select class="form-select" multiple size="2" id="shopsModal" name="shopsModal" required>
                                            <option id="shop${shopId}"></option>
                                        </select>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <input type="submit" class="btn btn-primary" value="Edit" />
                        </div>
                    </div>
                </div>
        </div>
    </form>
    </div>`
    document.body.append(modal)
    let modal = new bootstrap.Modal(modal.querySelector('.modal'));
    modal.show();
};