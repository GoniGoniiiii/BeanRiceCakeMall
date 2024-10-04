
document.querySelectorAll('.sort-option').forEach(filter => {
    filter.addEventListener('click', function (event) {
        event.preventDefault();
        const sort = this.getAttribute('data-sort');

        console.log(sort);
        fetch(`/?sort=${sort}`, {
            headers: {
                'X-Requested-With': 'XMLHttpRequest'
            }
        })
            .then(response => response.text())
            .then(html => {
                document.querySelector('#allProductList').innerHTML = html;
                formatPrices();
            })
            .catch(error => console.error('Error : ', error));
    });
});


function formatPrices(){
    const priceElements=document.querySelectorAll(".product-price");

    priceElements.forEach(element =>{
        const price=parseInt(element.textContent.replace(/[^0-9]/g,''));
        if(!isNaN(price)){
            element.textContent=price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원';
        }
    });
}
window.onload=formatPrices;