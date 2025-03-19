// Hàm để toggle menu khi nhấn vào hamburger
const menuToggle = document.getElementById('menuToggle');
const menuList = document.getElementById('menuList');

menuToggle.addEventListener('click', () => {
    menuList.classList.toggle('show');
});

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
