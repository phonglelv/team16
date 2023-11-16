document.addEventListener('DOMContentLoaded', function () {
    const removeFromCartButtons = document.querySelectorAll('.remove-from-cart');
    const cartItems = document.getElementById('cart-items');
    const cartTotal = document.getElementById('cart-total');
    const checkoutButton = document.getElementById('checkout-btn');

    let total = 0;

    removeFromCartButtons.forEach(button => {
        button.addEventListener('click', function () {
            const product = this.parentElement;
            const productName = product.querySelector('h2').innerText;
            const productPrice = parseFloat(product.querySelector('p').innerText.replace('$', ''));

            product.remove();

            total -= productPrice;
            cartTotal.innerText = `Tổng giá trị: $${total.toFixed(2)}`;
        });
    });

    checkoutButton.addEventListener('click', function () {
        alert('Cảm ơn bạn đã mua hàng!'); // Thêm xử lý thanh toán thực tế ở đây
    });
});
/*!
* Start Bootstrap - Shop Item v5.0.6 (https://startbootstrap.com/template/shop-item)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-item/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project