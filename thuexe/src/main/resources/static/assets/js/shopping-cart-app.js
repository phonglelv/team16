const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function ($scope, $http, $location) {

    // Quan ly Gio hang

    $scope.cart = {
        items: [],
        updateQuantity(item) {
            // Check if the quantity is greater than 0 and less than or equal to 5
            if (item.qty > 0 && item.qty <= 100) {
                this.saveToLocalStorage();
            } else {
                // If not within the allowed range, set a default value of 1
                item.qty = 1;
                this.saveToLocalStorage();
                alert('Số lượng sản phẩm trong giỏ hàng nhỏ hơn hoặc bằng 100');
            }
        },
        // Them san pham vao gio hang
        add(id) {
            var item = this.items.find(item => item.id == id);

            if (item) { // kiểm tra có item có trong mặt hàng hay không
                if (item.qty < 100) {
                    item.qty++; // có thì tăng số lượng
                    this.saveToLocalStorage(); // Lưu vào
                    alert("Bạn đã thêm giỏ hàng thành công");
                } else {
                    alert("Số lượng sản phẩm trong giỏ hàng đã đạt tối đa (100)!");
                }
            } else { // nếu không thì tải sản phẩm từ server về thông qua API
                // nếu không thì tải sản phẩm từ server thông qua API
        $http.get(`/rest/products/${id}`).then(resp => {
            resp.data.qty = 1;
            this.items.push(resp.data);
            this.saveToLocalStorage();
            alert("Bạn đã thêm giỏ hàng thành công");
        });
            }
        },

        //Xoas san pham khoi gio hang
        remove(id) {
            var index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },

        // Xoa sach cac mat hang trong gio
        clear() {
            this.items = []
            this.saveToLocalStorage();
        },

        // tinh thanh tien cua 1 san pham
        amt_of(item) { },

        // Tinh tong so luong cac mat hang trong gio
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },

        // Tong so tien cac mat hang trong gio
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },

        //Luu gio hang vao loca storage
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },

        // Doc gio hang tu local storage
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }
    }
    $scope.cart.loadFromLocalStorage();

    $scope.order = {
        createDate: new Date(),
        address: "",
        paymentMethod: "",
        customer: { username: $("#username").text() },
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: { id: item.id },
                    price: item.price,
                    quantity: item.qty,
                    status: "Chờ xác nhận"
                };
            });
        },
        purchase() {
            if ($scope.cart.items.length === 0) {
                // Display an error message that the cart is empty
                alert('Giỏ hàng bị rỗng vui lòng thêm giỏ hàng lại!!!!');
                location.href("/cart/view"); // Redirect back to the cart page
                return;
            }

            // Check if any item quantity in the cart exceeds 5
            if ($scope.cart.items.some(item => item.qty > 10)) {
                alert('Số lượng sản phẩm trong giỏ hàng không được vượt quá 10!');
                return;
            }

            if (!this.address) {
                // Display an error message that the shipping address is required
                alert('Bạn không thể thanh toán bằng địa chỉ rỗng! Vui lòng nhập địa chỉ!!!!');
                return;
            }
            if (!this.paymentMethod) {
                // Display an error message that the payment method needs to be selected
                alert('Vui lòng chọn loại thanh toán!');
                return;
            }

            var order = angular.copy(this);
            $http.post("/rest/orders", order)
                .then(resp => {
                    alert('Đơn hàng đặt thành công chờ nhân viên sắp xếp');
                    $scope.cart.clear();
                    location.href = "/order/detail/" + resp.data.id;
                })
                .catch(error => {
                    alert("Lỗi");
                    console.log(error);
                });
        }
    };

})
