
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
            })
            .catch(error => console.error('Error : ', error));
    });
});