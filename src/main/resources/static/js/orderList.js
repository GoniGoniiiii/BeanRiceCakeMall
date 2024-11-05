function formatPrices(){
    const priceElements=document.querySelectorAll(".order-price");

    priceElements.forEach(element =>{
        const price=parseInt(element.textContent.replace(/[^0-9]/g,''));
        if(!isNaN(price)){
            element.textContent=price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }
    });
}
window.onload=formatPrices;