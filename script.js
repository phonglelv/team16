document.addEventListener("DOMContentLoaded", function() {
    // Dữ liệu sản phẩm
    const products = [
        { name: "Xe đạp ", price: 250000 },
        // Thêm sản phẩm khác nếu cần
    ];

    // Hiển thị sản phẩm trong giỏ hàng
    const cartItemsContainer = document.getElementById("cart-items");
    const cartTotalElement = document.getElementById("cart-total");

    function displayCart() {
        cartItemsContainer.innerHTML = "";
        let total = 0;

        products.forEach(product => {
            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${product.name}</td>
                <td>${product.price} VND</td>
                <td><input type="number" value="1" min="1" class="quantity-input"></td>
                <td class="product-total">0 VND</td>
                <td><button onclick="removeItem(this)">Xóa</button></td>
            `;

            cartItemsContainer.appendChild(row);

            const quantityInput = row.querySelector(".quantity-input");
            quantityInput.addEventListener("input", updateTotal);
        });

        cartTotalElement.textContent = total + " VND";
    }

    displayCart();

    // Cập nhật tổng cộng khi số lượng sản phẩm thay đổi
    function updateTotal() {
        let total = 0;
        const rows = cartItemsContainer.querySelectorAll("tr");

        rows.forEach(row => {
            const price = parseInt(row.children[1].textContent);
            const quantity = parseInt(row.querySelector(".quantity-input").value);
            const productTotal = price * quantity;

            row.children[3].textContent = productTotal + " VND";
            total += productTotal;
        });

        cartTotalElement.textContent = total + " VND";
    }

    // Xóa sản phẩm khỏi giỏ hàng
    window.removeItem = function(button) {
        const row = button.closest("tr");
        row.remove();
        updateTotal();
    };

    // Thanh toán
    window.checkout = function() {
        alert('Đã thanh toán! Cám ơn bạn đã mua hàng.');
        // Thêm mã xử lý thanh toán tại đây (có thể gửi dữ liệu đơn hàng đến backend)
    };
});
