function toggleSearchInput() {
    var searchInput = document.getElementById("search-input");
    var searchIcon = document.getElementById("search-icon");

    if (searchInput.style.display === "none" || searchInput.style.display === "") {
        searchInput.style.display = "block";
        searchIcon.style.display = "none";
    } else {
        searchInput.style.display = "none";
        searchIcon.style.display = "block";
    }
}
const menuToggle = document.querySelector('.menu-toggle');
const menuContainer = document.querySelector('.menu-container');

//Listen click hamburger button
menuToggle.addEventListener('click', () => {
    menuContainer.classList.toggle('open');  // Mở/đóng div chứa menu
    menuToggle.classList.toggle('open');  // Thêm hoặc xóa lớp 'open' từ button hamburger
});

//fetch data
fetch('http://localhost:8080/general/products')
    .then(response => response.json())
    .then(data => {
        const productList = document.getElementById('product-list');
        data.forEach(product => {
            const productElement = document.createElement('div');
            productElement.classList.add('product');
            productElement.innerHTML = `
                <img src="${product.imageUrl}" alt="${product.productName}">
                <h3>${product.productName}</h3>
                <p>${product.price} USD</p>
            `;
            productList.appendChild(productElement);
        });
    })
    .catch(error => {
        console.error('Error fetching products:', error);
    });
