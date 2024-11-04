function replaceEnter() {
    const spanElement = document.getElementById("product_content");
    const text = spanElement.innerText || spanElement.textContent;  // 텍스트 콘텐츠를 가져옴
    const formattedText = text.replace(/\n/g, '<br>');  // 줄바꿈 문자를 <br> 태그로 변환
    spanElement.innerHTML = formattedText;  // 변환된 HTML을 삽입
}

document.querySelectorAll('.sort-option').forEach(filter=>{
    filter.addEventListener('click',function(event){
       event.preventDefault();
       const sort=this.getAttribute('data-sort');
       const categoryElement=document.getElementById("category_num");
       const keywordElement=document.getElementById("keyword");

       let fetchUrl='';

       if (keyword&&keyword.value){
           //keyword가 있을때
           const keyword=keywordElement.value;
           console.log("검색 키워드 :"+keyword);
           fetchUrl=`/product/search?keyword=${encodeURIComponent(keyword)}&sort=${encodeURIComponent(sort)}`;
       }else if(categoryElement&& categoryElement.value){
           const categoryNum=categoryElement.value;
           console.log("카테고리 번호 :  "+categoryNum);
           fetchUrl=`/productList/${categoryNum}?sort=${encodeURIComponent(sort)}`;
       }else{
           console.error("검색 키워드나 카테고리가 없습니다!");
           return;
       }

       fetch(fetchUrl,{
           headers:{
               'X-Requested-With': 'XMLHttpRequest',
           }
       }).then(response=>response.text())
           .then(html => {
               document.querySelector("#productList").innerHTML=html;
               formatPrices();
           })
           .catch(error=>console.error('Error : ', error));
    });
});

function formatPrices() {
    const priceElements = document.querySelectorAll(".product-price");

    priceElements.forEach(element => {
        const price = parseInt(element.textContent.replace(/[^0-9]/g, ''));
        if (!isNaN(price)) {
            element.textContent = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }
    });
}

window.onload = formatPrices;